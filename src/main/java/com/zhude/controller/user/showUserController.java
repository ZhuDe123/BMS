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

@WebServlet(name = "showUserController", urlPatterns = "/manager/safe/showUserController")
public class showUserController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String pageIndex = request.getParameter("pageIndex");

        UserService service = new UserServiceImpl();
        User emp = service.showUser(Integer.parseInt(id));

        Page page = (Page) request.getAttribute("page");
        if (page != null) {
            page.setPageIndex(Integer.parseInt(pageIndex));
        }else {
            page = new Page(Integer.parseInt(pageIndex));
        }

        request.setAttribute("emp", emp);
        request.setAttribute("page", page);
        String uerMessage = request.getParameter("userMessage");
        if (uerMessage != null && uerMessage.length() > 0) {
            request.getRequestDispatcher("/user/userMessage.jsp?id="+id+"&pageIndex=" + pageIndex).forward(request, response);

        }else {
            request.getRequestDispatcher("/user/userUpdate.jsp?id="+id+"&pageIndex=" + pageIndex).forward(request, response);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
