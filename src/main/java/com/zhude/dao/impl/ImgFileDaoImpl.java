package com.zhude.dao.impl;


import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.zhude.dao.ImgFileDao;
import com.zhude.entity.ImgFile;
import com.zhude.entity.ImgPage;
import org.bson.types.ObjectId;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ImgFileDaoImpl implements ImgFileDao {


    private static final GridFS gridFS;
    private static final MongoClient mongoClient;

    static {
        mongoClient = new MongoClient("localhost", 27017);
//        mongoClient = new MongoClient( "192.168.8.116" , 27017 );
        // 连接到数据库

        DB test = mongoClient.getDB("primary_test");
        gridFS = new GridFS(test);
    }

    @Override
    public List<ImgFile> selectAll(ImgPage imgPage) {

        List<ImgFile> list = new ArrayList<>();
        //添加查询条件 文件类型 模糊查询 图片名
        BasicDBObject query = new BasicDBObject();

        query.put("metadata", new BasicDBObject("contentType", imgPage.getContentType()));
        String name = imgPage.getFilename();
        if (name != null && name.length() > 0) {
            Pattern pattern = Pattern.compile("^.*" + name + ".*$", Pattern.CASE_INSENSITIVE);
            query.put("filename", pattern);
        }
        //获取集合
        List<GridFSDBFile> gridFSDBFiles = gridFS.find(query);

        imgPage.setTotalCounts(gridFSDBFiles.size());

        List<GridFSDBFile> limit;
        //分页 第一页
        if (imgPage.getPageIndex() == 1 || imgPage.getTotalPages() < imgPage.getPageIndex()) {
            if (imgPage.getTotalCounts() < imgPage.getPageSize()) {
                limit = gridFSDBFiles;
            } else {
                limit = gridFSDBFiles.subList(0, imgPage.getPageSize());
            }
        } else {
            int cha = imgPage.getTotalCounts() - imgPage.getStartRows();
            if (cha >= imgPage.getPageSize()) {
                limit = gridFSDBFiles.subList(imgPage.getStartRows(), imgPage.getStartRows() + imgPage.getPageSize());
            } else {
                limit = gridFSDBFiles.subList(imgPage.getStartRows(), imgPage.getTotalCounts());
            }
        }
        addList(limit, list);

        return list;
    }

    @Override
    public ImgFile select(ObjectId id) {
        BasicDBObject query = new BasicDBObject();
        query.put("_id", id);
        GridFSDBFile img = gridFS.findOne(query);

        return GridFileToImgFile(img);
    }

    @Override
    public int insert(ImgFile imgFile) {

        GridFSInputFile file = gridFS.createFile(imgFile.getInputStream());
        file.setFilename(imgFile.getFilename());
        file.setMetaData(new BasicDBObject("contentType", imgFile.getContentType()));
        file.save();

        return 0;
    }

    @Override
    public int update(ImgFile imgFile) {

        if (imgFile.getId() != null) {
            gridFS.remove(imgFile.getId());
        }
        GridFSInputFile file = gridFS.createFile(imgFile.getInputStream());
        file.setId(imgFile.getId());
        file.setFilename(imgFile.getFilename());
        file.setMetaData(new BasicDBObject("contentType", imgFile.getContentType()));
        file.save();
        return 0;
    }

    @Override
    public int delete(ObjectId id) {
        gridFS.remove(id);
        return 0;
    }

    public void addList(List<GridFSDBFile> limit, List<ImgFile> list) {
        for (GridFSDBFile img : limit) {
            ImgFile imgFile = GridFileToImgFile(img);
            list.add(imgFile);
        }
    }

    public ImgFile GridFileToImgFile(GridFSDBFile img) {
        ImgFile imgFile = new ImgFile();
        imgFile.setId((ObjectId) img.get("_id"));
        imgFile.setContentType((String) img.getMetaData().get("contentType"));
        imgFile.setFilename(img.getFilename());
        imgFile.setLength(img.getLength());
        imgFile.setInputStream(img.getInputStream());
        return imgFile;
    }

//    public static void main(String[] args) {
//        File file = new File("D:\\0桌面\\麦穗\\过程照片\\IMG_20190516_084100\\IMG_20190516_084100_segementation");
//        ImgFileDaoImpl dao = new ImgFileDaoImpl();
//
//        MongoClient client = new MongoClient("localhost");
//        DB primary_test = client.getDB("primary_test");
//
////        primary_test.dropDatabase();
//        try {
//            f(primary_test, file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("success");
//    }

}
