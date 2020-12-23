package com.zhude.controller.img;

import com.zhude.dao.ImgFileDao;
import com.zhude.dao.impl.ImgFileDaoImpl;
import com.zhude.entity.ImgFile;
import com.zhude.entity.ImgPage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "InitController", urlPatterns = "/Init")
public class InitController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ImgFileDao dao = new ImgFileDaoImpl();
        HttpSession session = request.getSession();

        ImgPage imgPage = new ImgPage(1);

        List<ImgFile> imgFiles = dao.selectAll(imgPage);


        session.setAttribute("imgFiles", imgFiles);
        session.setAttribute("imgPage", imgPage);
        response.sendRedirect(request.getContextPath()+"/ImgManager/test.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
