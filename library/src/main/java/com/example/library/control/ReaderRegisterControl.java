/*
读者注册界面
127.0.0.1:8888/ReaderRegister
*/
package com.example.library.control;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.example.library.database.src.team.library.demo.*;

import javax.servlet.http.HttpSession;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ReaderRegisterControl
{
    @RequestMapping(value = "/ReaderRegister", method = RequestMethod.GET)
    public ModelAndView getReaderRegister(ModelAndView mv) {
        mv.setViewName("/ReaderRegister");
        return mv;
    }

    @RequestMapping(value = "/ReaderRegister", method = RequestMethod.POST)
    public ModelAndView postReaderRegister(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getParameter("ReaderRegister") != null) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            // String email = request.getParameter("email");
            // String name = request.getParameter("name");
            String tel = request.getParameter("tel");
            // String gender = request.getParameter("gender");
            // String bond = request.getParameter("bond");
    
            boolean isRegister =new Librarian().ReaderRegister(tel, username, password);
            // 判断是否注册成功
            if (isRegister) {
                System.out.println("Successed register admin!");
            }
            else {
                System.out.println("Failed register admin!");
            } 
            mv.addObject("isRegister", isRegister);
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