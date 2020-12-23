package com.zhude.controller.img;

import com.zhude.dao.ImgFileDao;
import com.zhude.dao.impl.ImgFileDaoImpl;
import com.zhude.entity.ImgFile;
import org.bson.types.ObjectId;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@WebServlet(name = "DownLoadImgController", urlPatterns = "/DownLoadImg")
public class DownLoadImgController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String id = request.getParameter("id");

        ImgFileDaoImpl dao = new ImgFileDaoImpl();

        ImgFile imgFile = dao.select(new ObjectId(id));

        //下载原来的图片
        byte[] bytes = new byte[20480];
        File file = new File(imgFile.getFilename());
        FileOutputStream outputStream = new FileOutputStream(file);
        InputStream in = imgFile.getInputStream();
        int read = 0;
        while ((read = in.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
            outputStream.flush();
        }
        in.close();
        outputStream.close();
        //获取下载图片的流
        in = new FileInputStream(file);
        response.setHeader("content-disposition", "attachment;filename="
                + URLEncoder.encode(imgFile.getFilename(), "UTF-8")); //注意：含有中文的文件名 需要给URL编码
        OutputStream out = response.getOutputStream();
        while ((read = in.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        //关闭流并删除文件
        in.close();
        file.delete();

//        response.sendRedirect(request.getContextPath() + "/PageController");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
