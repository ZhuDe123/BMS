package com.zhude.controller.img;

import com.zhude.dao.ImgFileDao;
import com.zhude.dao.impl.ImgFileDaoImpl;
import com.zhude.entity.ImgFile;
import com.zhude.entity.ImgPage;
import org.bson.types.ObjectId;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UpdateImgController", urlPatterns = "/UpdateImg")
@MultipartConfig(maxFileSize = 1024*1024*100,maxRequestSize = 1024*1024*200)
//maxFileSize单个文件最大大小
//maxRequestSize一次请求中，多个文件一共可以多打
public class UpdateImgController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置乱码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        ImgFileDao dao = new ImgFileDaoImpl();

        //获取请求数据
        String id = request.getParameter("id");
        String filename = request.getParameter("filename");
        String contentType = request.getParameter("contentType");
        //part代表文件
        Part part = request.getPart("image");//request.getPart(文件组件的名字);
        if (part.getSubmittedFileName() == null || part.getSubmittedFileName().length() == 0) {
            //没有更改
            ImgFile imgFile = dao.select(new ObjectId(id));
            //下载原来的图片
//            String uploadPath = request.getServletContext().getRealPath("/WEB-INF/upload");

            byte[] bytes = new byte[20480];
            File file = new File(imgFile.getFilename());
//            FileOutputStream outputStream = new FileOutputStream(uploadPath + "\\" + imgFile.getFilename());
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
//            in = new FileInputStream(uploadPath + "\\" + imgFile.getFilename());
            in = new FileInputStream(file);
            imgFile.setInputStream(in);

            //更新
            imgFile.setFilename(filename);
            imgFile.setContentType(contentType);

            dao.update(imgFile);
            //关闭流并删除文件
            in.close();
            file.delete();
        }else {
            //更改图片
            InputStream in = part.getInputStream();
            long length = part.getSize();
            ImgFile imgFile = new ImgFile(new ObjectId(id), filename, contentType, in, length);

            dao.update(imgFile);
            //关闭流
            in.close();
        }
        HttpSession session = request.getSession();
        ImgPage page = (ImgPage) session.getAttribute("imgPage");
        response.sendRedirect(request.getContextPath() + "/PageController?contentType=" + contentType + "&pageIndex=" + page.getTotalPages());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
