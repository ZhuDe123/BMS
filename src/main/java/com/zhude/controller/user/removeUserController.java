package com.zhude.controller.user;

import com.zhude.service.UserService;
import com.zhude.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "removeUserController", urlPatterns = "/manager/safe/removeUserController")
public class removeUserController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String pageIndex = request.getParameter("pageIndex");
        UserService service = new UserServiceImpl();
        int remove = service.remove(Integer.parseInt(id));

        if (remove != 0) {
            request.getRequestDispatcher("/manager/safe/showAllUser?pageIndex="+pageIndex).forward(request,response);

        }else {
            System.out.println("删除失败");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
