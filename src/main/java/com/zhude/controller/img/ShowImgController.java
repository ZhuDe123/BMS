package com.zhude.controller.img;

import com.zhude.dao.ImgFileDao;
import com.zhude.dao.impl.ImgFileDaoImpl;
import com.zhude.entity.ImgFile;
import org.bson.types.Binary;
import org.bson.types.ObjectId;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.List;

@WebServlet(name = "ShowImgController", urlPatterns = "/ShowImg")
public class ShowImgController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/jpg");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());

        String id = request.getParameter("id");
        ImgFileDao dao = new ImgFileDaoImpl();
        ImgFile select = dao.select(new ObjectId(id));
        InputStream in = select.getInputStream();
        BufferedInputStream inputStream = new BufferedInputStream(in);
        byte[] data = new byte[1024];
        int read = 0;
        while ((read = inputStream.read(data)) != -1) {
            out.write(data,0,data.length);
        }
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
