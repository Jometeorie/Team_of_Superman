/*
图书管理员忘记密码界面
127.0.0.1:8888/ForgotLibrarianPassword
*/
package com.example.library.control;

import com.example.library.database.src.team.library.demo.Librarian;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class ForgotLibrarianPasswordControl {
    @RequestMapping(value = "/ForgotLibrarianPassword", method = RequestMethod.GET)
    public ModelAndView getForgotLibrarianPassword(ModelAndView mv) {
        mv.setViewName("/ForgotLibrarianPassword");
        return mv;
    }

    @RequestMapping(value = "/ForgotLibrarianPassword", method = RequestMethod.POST)
    public ModelAndView postForgotLibrarianPassword(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        if (request.getParameter("ForgotLibrarianPassword") != null) {
            mv.setViewName("/ForgotLibrarianPassword");
            String username = request.getParameter("username");
            String email = request.getParameter("email");

            // 判断是否成功
            boolean isLibr =new Librarian().isLibrarian(username);
            if (isLibr) {
                System.out.println("Successed!");
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                // 前端判断是否成功的依据，0为成功，非0为失败
                mv.addObject("value_judg", 0);
                mv.addObject("value_judg2", 1);
            }
            else {
                System.out.println("Failed!");
                mv.addObject("value_judg", 1);
                mv.addObject("value_judg2", 0);
            }
            mv.addObject("isReader", isLibr);
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