package com.zhude.controller.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "identifyEmailCodeController", urlPatterns = "/identifyEmailCode")
public class identifyEmailCodeController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        String identifyEmailCode = request.getParameter("identifyEmailCode");
        String emailCode = request.getParameter("emailCode");

        if (emailCode == null && emailCode.length() == 0) {
            out.print("noneEmailCode");
            out.flush();
            out.close();
            return;
        }

        if (identifyEmailCode.equalsIgnoreCase(emailCode)) {
            out.print("yes");
        }else {
            out.print("no");
        }
        out.flush();
        out.close();
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
