package com.zhude.filter;

import com.zhude.entity.User;
import com.zhude.entity.UserManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "CheckFilter", value = "/manager/safe/*")
public class CheckFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        UserManager empManager = (UserManager) session.getAttribute("empManager");
        if (empManager != null) {
            chain.doFilter(req, resp);
        } else {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                chain.doFilter(req, resp);
            } else {
                response.sendRedirect(request.getContextPath() + "/user/login.jsp");
            }
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
