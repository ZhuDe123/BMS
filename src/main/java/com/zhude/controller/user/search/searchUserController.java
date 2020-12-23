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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "searchUserController", urlPatterns = "/manager/safe/searchUser")
public class searchUserController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pageIndex = request.getParameter("pageIndex");
        if (pageIndex == null) {
            pageIndex = "1";
        }

        Page searchPage = (Page) request.getAttribute("searchPage");
        if (searchPage != null) {
            searchPage.setPageIndex(Integer.parseInt(pageIndex));
        }else {
            searchPage = new Page(Integer.parseInt(pageIndex));
        }

        String searchUsername = request.getParameter("searchUsername");
        String searchPhone = request.getParameter("searchPhone");
        if ((searchUsername == null || searchUsername.length() == 0) && (searchPhone == null || searchPhone.length() == 0)) {
            response.sendRedirect(request.getContextPath()+"/manager/safe/showAllUser");
            return;
        }

        request.setAttribute("searchUsername", searchUsername);
        request.setAttribute("searchPhone", searchPhone);


        UserService service = new UserServiceImpl();
        List<User> searchUsers = service.searchUser(searchPage, searchUsername, searchPhone);

        request.setAttribute("searchUsers", searchUsers);
        request.setAttribute("searchPage", searchPage);


        request.getRequestDispatcher("/user/userSearch.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
