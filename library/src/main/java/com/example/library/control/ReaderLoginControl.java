/*
读者登录界面
127.0.0.1:8888/ReaderLogin
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
public class ReaderLoginControl {
    @RequestMapping(value = "/ReaderLogin", method = RequestMethod.GET)
    public ModelAndView getReaderLogin(ModelAndView mv) {
        mv.setViewName("/ReaderLogin");
        return mv;
    }

    @RequestMapping(value = "/ReaderLogin", method = RequestMethod.POST)
    public ModelAndView postReaderLogin(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException{
        mv.setViewName("/ReaderLogin");
        if (request.getParameter("ReaderLogin") != null) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // 判断是否登录成功
            boolean isLogin =new Reader().ReaderLogin(username, password);
            if (isLogin) {
                System.out.println("Successed login reader!");
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("identity", "reader");
                response.sendRedirect("ReaderPage");
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