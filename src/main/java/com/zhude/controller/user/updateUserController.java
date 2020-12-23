package com.zhude.controller.user;

import com.zhude.entity.User;
import com.zhude.service.UserService;
import com.zhude.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "updateUserController", urlPatterns = "/manager/safe/updateUser")
public class updateUserController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids = request.getParameter("id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String sex = request.getParameter("sex");
        int id = Integer.valueOf(ids);
        User emp = new User(id, username, password, phone, email, sex);
        UserService service = new UserServiceImpl();
        int res = service.modify(emp);
        if (res != 0) {
            request.getRequestDispatcher("/manager/safe/showAllUser").forward(request,response);
        }else {
            System.out.println("更新失败");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
