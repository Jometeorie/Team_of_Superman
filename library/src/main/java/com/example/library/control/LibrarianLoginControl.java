/*
图书管理员登录界面
127.0.0.1:8888/LibrarianLogin
*/
package com.example.library.control;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.example.library.database.src.team.library.demo.*;

import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@RestController
public class LibrarianLoginControl {
    @RequestMapping(value = "/LibrarianLogin", method = RequestMethod.GET)
    public ModelAndView getLibrarianLogin(ModelAndView mv) {
        mv.setViewName("/LibrarianLogin");
        return mv;
    }

    @RequestMapping(value = "/LibrarianLogin", method = RequestMethod.POST)
    public  ModelAndView postLibrarianLogin(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        if (request.getParameter("LibrarianLogin") != null)  {
            mv.setViewName("/LibrarianLogin");
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // 判断是否登录成功
            boolean isLogin =new Librarian().LibrLogin(username, password);
            if (isLogin) {
                System.out.println("Successed login librarian!");
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("identity", "librarian");
                response.sendRedirect("LibrarianPage");
                // 前端判断登录是否成功的依据，0为成功，非0为失败
                mv.addObject("value_judg", 0);
            }
            else {
                System.out.println("Failed login librarian!");
                mv.addObject("value_judg", 1);
            }
            mv.addObject("isLogin", isLogin);
            return mv;
        }

        // 页眉Logo按钮
        else if (request.getParameter("mainpage") != null) {
            response.sendRedirect("MainPage");
            return mv;
        }

        return mv;
    }
}