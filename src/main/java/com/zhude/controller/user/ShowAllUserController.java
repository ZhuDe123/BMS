package com.zhude.controller.user;

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

@WebServlet(name = "ShowAllUserController", urlPatterns = "/manager/safe/showAllUser")
public class ShowAllUserController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pageIndex = request.getParameter("pageIndex");
        if (pageIndex == null) {
            pageIndex = "1";
        }

        Page page = (Page) request.getAttribute("page");
        if (page != null) {
            page.setPageIndex(Integer.parseInt(pageIndex));

        }else {
            page = new Page(Integer.valueOf(pageIndex));
        }

        UserService service = new UserServiceImpl();
        List<User> emps = service.showAllUserByPage(page);

        request.setAttribute("emps", emps);
        request.setAttribute("page", page);

        request.getRequestDispatcher("/user/userlist.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
