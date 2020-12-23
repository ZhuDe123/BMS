package com.zhude.controller.img;

import com.zhude.dao.ImgFileDao;
import com.zhude.dao.impl.ImgFileDaoImpl;
import com.zhude.entity.ImgFile;
import com.zhude.entity.ImgPage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PageController", urlPatterns = "/PageController")
public class PageController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        ImgPage page = (ImgPage) session.getAttribute("imgPage");

        String pageIndex = request.getParameter("pageIndex");
        if (pageIndex != null && pageIndex.length() > 0) {
            page.setPageIndex(Integer.parseInt(pageIndex));
        }

        String contentType = request.getParameter("contentType");
        String filename = request.getParameter("filename");

        //如果是查询
        page.setFilename(filename);
        //不是查询
        if (contentType != null && contentType.length() > 0) {
            page.setContentType(contentType);
        }
//        if (filename != null && filename.length() > 0) {
//            page.setFilename(filename);
//        }


        ImgFileDao dao = new ImgFileDaoImpl();
        List<ImgFile> imgFiles = dao.selectAll(page);

        session.setAttribute("imgFiles", imgFiles);
        session.setAttribute("imgPage", page);
        response.sendRedirect(request.getContextPath() + "/ImgManager/test.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
