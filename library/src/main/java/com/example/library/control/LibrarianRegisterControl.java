/*
图书管理员注册界面
127.0.0.1:8888/LibrarianRegister
*/
package com.example.library.control;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.example.library.database.src.team.library.demo.*;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LibrarianRegisterControl
{
    @RequestMapping(value = "/LibrarianRegister", method = RequestMethod.GET)
    public ModelAndView getLibrarianRegister(ModelAndView mv) {
        mv.setViewName("/LibrarianRegister");
        return mv;
    }

    @RequestMapping(value = "/LibrarianRegister", method = RequestMethod.POST)
    public ModelAndView postLibrarianRegister(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        if (request.getParameter("LibrarianRegister") != null) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            // String email = request.getParameter("email");
            // String name = request.getParameter("name");
            String tel = request.getParameter("tel");
            // String gender = request.getParameter("gender");
            // String bond = request.getParameter("bond");

            // 判断是否注册成功
            boolean isRegister =Admin.LibrRegister(tel, username, password);
            if (isRegister) {
                System.out.println("Successed register admin!");
            }
            else {
                System.out.println("Failed register admin!");
            }
            mv.addObject("isRegister", isRegister);
        }
        // 页眉Logo按钮
        else if (request.getParameter("mainpage") != null) {
            response.sendRedirect("MainPage");
            return mv;
        }
        return mv;
    }
}