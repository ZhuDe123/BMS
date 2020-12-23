package com.zhude.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhude.dao.UserManagerDao;
import com.zhude.entity.User;
import com.zhude.entity.UserManager;
import com.zhude.service.UserManagerService;
import com.zhude.service.UserService;
import com.zhude.service.impl.UserManagerServiceImpl;
import com.zhude.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "findUserServlet", urlPatterns = "/findUser")
public class findUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fun = request.getParameter("fun");
        if("checkusername".equals(fun)){
            checkusername(request,response);
        }
    }

    protected void checkusername(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        response.setContentType("html/text;charset:utf-8");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");

        UserManagerService managerService = new UserManagerServiceImpl();
        UserManager manager = managerService.findUserName(username);

        UserService service = new UserServiceImpl();
        User user = service.findUserName(username);
        if (user != null || manager != null) {//数据库中存在用户名重复
            out.print("no");
        } else {//不重复可以注册
            out.print("yes");
        }
        out.flush();
        out.close();
    }
}
