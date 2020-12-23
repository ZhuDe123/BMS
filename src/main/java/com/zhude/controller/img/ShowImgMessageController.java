package com.zhude.controller.img;

import com.zhude.dao.ImgFileDao;
import com.zhude.dao.impl.ImgFileDaoImpl;
import com.zhude.entity.ImgFile;
import com.zhude.entity.User;
import com.zhude.service.UserService;
import com.zhude.service.impl.UserServiceImpl;
import org.bson.types.ObjectId;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ShowImgMessageController", urlPatterns = "/ShowImgMessage")
public class ShowImgMessageController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String pageIndex = request.getParameter("pageIndex");

        ImgFileDao dao = new ImgFileDaoImpl();
        ImgFile imgFile = dao.select(new ObjectId(id));

        request.setAttribute("imgFile", imgFile);
        request.getRequestDispatcher("/ImgManager/imgMessage.jsp?id="+id+"&pageIndex=" + pageIndex).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
