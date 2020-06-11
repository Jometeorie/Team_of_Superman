/*
主页面
127.0.0.1:8888/MainPage
*/
package com.example.library.control;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.library.database.src.team.library.demo.Librarian;

import java.io.IOException;

@RestController
public class MainPageControl
{
    @RequestMapping(value = "/MainPage", method = RequestMethod.GET)
    public ModelAndView getMainPage(ModelAndView mv) {
        mv.addObject("title", Librarian.title);
        mv.addObject("content", Librarian.content);
        mv.setViewName("/MainPage");
        return mv;
    }

    @RequestMapping(value = "/MainPage", method = RequestMethod.POST)
    public ModelAndView postMainPage(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        // 页眉Logo按钮
        if (request.getParameter("mainpage") != null) {
            response.sendRedirect("MainPage");
            return mv;
        }

        else if (request.getParameter("logout") != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", "");
            session.setAttribute("identity", "");
            response.sendRedirect("MainPage");
            return mv;
        }

        return mv;
    }
}