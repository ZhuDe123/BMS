package com.zhude.controller.user;

import com.zhude.dao.UserDao;
import com.zhude.entity.User;
import com.zhude.service.UserService;
import com.zhude.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegisterController", urlPatterns = "/manager/Register")
public class RegisterController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("username");
        String sex = request.getParameter("sex");
        String phone =request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = new User(name, password,phone, email, sex);

        UserService service = new UserServiceImpl();
        int i = service.addUser(user);
        if (i != 0) {
            PrintWriter writer = response.getWriter();
            writer.println("alert(注册成功，请返回登录界面登录)");
            response.sendRedirect(request.getContextPath()+"/user/login.jsp");
            writer.flush();
            writer.close();
        }else {
            PrintWriter writer = response.getWriter();
            writer.println("alert(注册失败，请检查后再次注册)");
            response.sendRedirect(request.getContextPath()+"/user/register.jsp");
            writer.flush();
            writer.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
