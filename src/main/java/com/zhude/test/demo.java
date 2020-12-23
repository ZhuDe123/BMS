package com.zhude.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@WebServlet(name = "demo", urlPatterns = "/demo")
public class demo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String uploadPath = request.getServletContext().getRealPath("/WEB-INF/upload");
        File file = new File(uploadPath + "\\" + "a.txt");
        if (!file.exists()) {
            file.mkdir();//新建文件夹
        }
        System.out.println(file.getAbsolutePath());
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write("asdfasdf".getBytes());
        outputStream.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
