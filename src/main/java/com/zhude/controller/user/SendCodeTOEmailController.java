package com.zhude.controller.user;

import cn.dsna.util.images.ValidateCode;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhude.entity.User;
import com.zhude.entity.UserManager;
import com.zhude.service.UserManagerService;
import com.zhude.service.UserService;
import com.zhude.service.impl.UserManagerServiceImpl;
import com.zhude.service.impl.UserServiceImpl;
import com.zhude.utils.EmailUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SendCodeTOEmailController", urlPatterns = "/SendCodeTOEmail")
public class SendCodeTOEmailController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");

        ValidateCode validateCode = new ValidateCode(200, 30, 4, 10);
        String code = validateCode.getCode();
        String code1 = java.net.URLEncoder.encode(code, "UTF-8");
        boolean sendMail = EmailUtils.sendMail(email, code);

        Cookie emailCode = new Cookie("emailCode", code1);
        emailCode.setMaxAge(60 * 5 * 1000);
        response.addCookie(emailCode);

        if (sendMail) {
            out.print("yes");
        } else {//不重复可以注册
            out.print("no");
        }
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
