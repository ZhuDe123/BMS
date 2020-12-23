package com.zhude.controller.img;

import com.zhude.dao.impl.ImgFileDaoImpl;
import org.bson.types.ObjectId;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "DeleteImgListController", urlPatterns = "/DeleteImgList")
public class DeleteImgListController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取表单提交的id值
        String[] imgId = request.getParameterValues("imgId");
        ImgFileDaoImpl dao = new ImgFileDaoImpl();

        for (String id : imgId) {
            dao.delete(new ObjectId(id));
        }
        response.sendRedirect(request.getContextPath() + "/PageController");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
