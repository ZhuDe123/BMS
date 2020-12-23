package com.zhude.controller.user;

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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserManagerLoginController", urlPatterns = "/manager/userManagerLogin")
public class UserManagerLoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String inputVCode = request.getParameter("inputVCode");

        String codes = (String) request.getSession().getAttribute("codes");

        if (!inputVCode.isEmpty() && inputVCode.equalsIgnoreCase(codes)) {
            UserManagerService service = new UserManagerServiceImpl();
            UserManager empManager = service.login(username, password);
            //验证总管理员
            if (empManager != null) {
                HttpSession session = request.getSession();
                session.setAttribute("empManager", empManager);

                response.sendRedirect(request.getContextPath() + "/manager/safe/showAllUser");
            } else {
                //验证管理员
                UserService userService = new UserServiceImpl();
                User user = userService.login(username, password);

                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);

                    response.sendRedirect(request.getContextPath() + "/Init");
                }else {
                    response.sendRedirect(request.getContextPath() + "/user/login.jsp");
                }
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/user/login.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
