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
public class ForgotLibrarianPasswordControl {
    @RequestMapping(value = "/ForgotLibrarianPassword", method = RequestMethod.GET)
    public ModelAndView getAddBook(ModelAndView mv) {
        mv.setViewName("/ForgotLibrarianPassword");
        return mv;
    }
//
//    @RequestMapping(value = "/ForgotLibrarianPassword", method = RequestMethod.POST)
//    public ModelAndView postAddBook(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException {
//    }
}
