package com.zhude.controller.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "IsSearchOrListController", urlPatterns = "/IsSearchOrList")
public class IsSearchOrListController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = (String) request.getAttribute("username");
        String phone = (String) request.getAttribute("phone");
        String pageIndex = request.getParameter("pageIndex");

        System.out.println(pageIndex);
        if ((username != null && username.length() > 0) || (phone != null && username.length() > 0)) {
            request.getRequestDispatcher("/manager/safe/searchUser?username=" + username + "&phone=" + phone + "&pageIndex=" + pageIndex).forward(request, response);

        }else {
//            request.getRequestDispatcher("/manager/safe/showAllUser?pageIndex=" + pageIndex);
            response.sendRedirect(request.getContextPath() + "/manager/safe/showAllUser?pageIndex=" + pageIndex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
