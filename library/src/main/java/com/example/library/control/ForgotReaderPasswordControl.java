/*
图书管理员忘记密码界面
127.0.0.1:8888/AdminLogin
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
public class ForgotReaderPasswordControl {
    @RequestMapping(value = "/ForgotReaderPassword", method = RequestMethod.GET)
    public ModelAndView getAddBook(ModelAndView mv) {
        mv.setViewName("/ForgotReaderPassword");
        return mv;
    }

    @RequestMapping(value = "/ForgotReaderPassword", method = RequestMethod.POST)
    public ModelAndView postAddBook(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        if (request.getParameter("ForgotReaderPassword") != null) {
            mv.setViewName("/ForgotReaderPassword");
            String username = request.getParameter("username");
            String email = request.getParameter("email");

            // 判断是否成功
            boolean isReader =new Reader().ReaderEmail(username, email);
            if (isReader) {
                System.out.println("Successed!");
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect("");   //修改密码页面（还没写）
                // 前端判断是否成功的依据，0为成功，非0为失败
                mv.addObject("value_judg", 0);
            }
            else {
                System.out.println("Failed!");
                mv.addObject("value_judg", 1);
            }
            mv.addObject("isReader", isReader);
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
