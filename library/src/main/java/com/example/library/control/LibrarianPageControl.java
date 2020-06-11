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

import javax.servlet.http.HttpSession;

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
    public ModelAndView postLibrarianPage(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        if (request.getParameter("ReaderRegister") != null) {
            // 跳转至读者注册界面
            ModelAndView model = new ModelAndView("redirect:/ReaderRegister");
            return model;
        }

        else if (request.getParameter("AddBooks") != null) {
            // 跳转至添加书籍界面
            ModelAndView model = new ModelAndView("redirect:/AddBook");
            return model;
        }

        else if (request.getParameter("DeleteBooks") != null) {
            // 跳转至删除书籍界面
            ModelAndView model = new ModelAndView("redirect:/BookManagement");
            return model;
        }

        // 页眉Logo按钮
        else if (request.getParameter("mainpage") != null) {
            ModelAndView model = new ModelAndView("redirect:/MainPage");
            return model;
        }

        else if (request.getParameter("logout") != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", "");
            session.setAttribute("identity", "");
            response.sendRedirect("MainPage");
            return mv;
        }

        if (request.getParameter("search_button") != null) {
            ModelAndView model = new ModelAndView("redirect:/BookManagement");
            String  searchName = request.getParameter("search");
            model.addObject("search_name", searchName);  
            return model;
        }

        return mv;
    }
}