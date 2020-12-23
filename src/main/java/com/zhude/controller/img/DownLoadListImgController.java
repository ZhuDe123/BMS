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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@WebServlet(name = "DownLoadListImgController", urlPatterns = "/DownLoadListImg")
public class DownLoadListImgController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] imgIds = request.getParameterValues("imgId");

        ImgFileDao dao = new ImgFileDaoImpl();

        /*将要下载的文件打包成zip再下载*/
        ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());

        byte[] bytes = new byte[1024];
        InputStream in = null;
        for (String imgId : imgIds) {
            ImgFile imgFile = dao.select(new ObjectId(imgId));
            in = imgFile.getInputStream();
            zos.putNextEntry(new ZipEntry(imgFile.getFilename()));
            int size = 0;
            while ((size = in.read(bytes)) != -1) {
                zos.write(bytes, 0, size);
            }
            in.close();
        }
        zos.flush();
        zos.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
