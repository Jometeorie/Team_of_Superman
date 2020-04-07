/*
图书管理员主界面
127.0.0.1:8888/LibrarianPage
*/
package com.example.library.control;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
// import com.example.library.database.src.team.library.demo.*;

// import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@RestController
public class LibrarianPageControl
{
    @RequestMapping(value = "/LibrarianPage", method = RequestMethod.GET)
    public ModelAndView getLibrarianPage(ModelAndView mv) {
        mv.setViewName("/LibrarianPage");
        return mv;
    }
    
    @RequestMapping(value = "/LibrarianPage", method = RequestMethod.POST) 
    public void postLibrarianPage(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        if (request.getParameter("ReaderRegister") != null) {
            // 跳转至读者注册界面
            response.sendRedirect("ReaderRegister");
        }

        else if (request.getParameter("AddBooks") != null) {
            // 跳转至添加书籍界面
            response.sendRedirect("AddBook");
        }

        else if (request.getParameter("DeleteBooks") != null) {
            // 跳转至删除书籍界面
            response.sendRedirect("DeleteBook");
        }

        // 页眉Logo按钮
        else if (request.getParameter("mainpage") != null) {
            response.sendRedirect("MainPage");
        }
    }
}