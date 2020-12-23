package com.zhude.controller.user.search;

import com.zhude.service.UserService;
import com.zhude.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "searchUserRemoveController", urlPatterns = "/manager/safe/searchUserRemove")
public class searchUserRemoveController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        UserService service = new UserServiceImpl();
        int remove = service.remove(Integer.parseInt(id));

        String pageIndex = request.getParameter("pageIndex");
        String searchUsername = request.getParameter("searchUsername");
        String searchPhone = request.getParameter("searchPhone");

        if (remove != 0) {
            request.getRequestDispatcher("/manager/safe/searchUser?searchUsername=" + searchUsername + "&searchPhone=" + searchPhone+"&pageIndex="+pageIndex).forward(request, response);
        }else {
            System.out.println("删除失败");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
