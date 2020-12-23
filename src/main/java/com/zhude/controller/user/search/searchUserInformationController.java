package com.zhude.controller.user.search;

import com.zhude.entity.Page;
import com.zhude.entity.User;
import com.zhude.service.UserService;
import com.zhude.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "searchUserInformationController", urlPatterns = "/manager/safe/searchUserInformation")
public class searchUserInformationController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String pageIndex = request.getParameter("pageIndex");

        UserService service = new UserServiceImpl();
        User emp = service.showUser(Integer.parseInt(id));

        Page page = (Page) request.getAttribute("searchPage");
        if (page != null) {
            page.setPageIndex(Integer.parseInt(pageIndex));
        }else {
            page = new Page(Integer.valueOf(pageIndex));
        }

        String searchUsername = request.getParameter("searchUsername");
        String searchPhone = request.getParameter("searchPhone");

        request.setAttribute("searchUsername", searchUsername);
        request.setAttribute("searchPhone", searchPhone);

        request.setAttribute("searchUsers", emp);
        request.setAttribute("searchPage", page);
        String uerMessage = request.getParameter("userMessage");
        if (uerMessage != null && uerMessage.length() > 0) {
            request.getRequestDispatcher("/user/userSearchMessage.jsp?id="+id+"&pageIndex=" + pageIndex).forward(request, response);

        }else {
            request.getRequestDispatcher("/user/userSearchUpdate.jsp?id="+id+"&pageIndex=" + pageIndex).forward(request, response);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
