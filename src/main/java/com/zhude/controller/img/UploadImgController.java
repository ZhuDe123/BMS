package com.zhude.controller.img;

import com.zhude.dao.ImgFileDao;
import com.zhude.dao.impl.ImgFileDaoImpl;
import com.zhude.entity.ImgFile;
import org.bson.types.ObjectId;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet(name = "UploadImgController", urlPatterns = "/UploadImg")
@MultipartConfig(maxFileSize = 1024*1024*100,maxRequestSize = 1024*1024*200)
//maxFileSize单个文件最大大小
//maxRequestSize一次请求中，多个文件一共可以多打
public class UploadImgController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        ImgFileDao dao = new ImgFileDaoImpl();

        //获取请求数据
        String filename = request.getParameter("filename");
        String contentType = request.getParameter("contentType");
        //part代表文件
        Part part = request.getPart("image");//request.getPart(文件组件的名字);
        //更改图片
        InputStream in = part.getInputStream();
        long length = part.getSize();
        ImgFile imgFile = new ImgFile(filename, contentType, in, length);

        dao.insert(imgFile);
        //关闭流
        in.close();

        response.sendRedirect(request.getContextPath() + "/PageController");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
