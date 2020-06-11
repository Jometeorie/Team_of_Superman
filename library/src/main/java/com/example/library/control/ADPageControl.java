/*
超管主页
127.0.0.1:8888/ADPage
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
public class ADPageControl
{
    @RequestMapping(value = "/ADPage", method = RequestMethod.GET)
    public ModelAndView getADPage(ModelAndView mv) {
        mv.setViewName("/ADPage");
        return mv;
    }

    @RequestMapping(value = "/ADPage", method = RequestMethod.POST) 
    public void postADPage(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        if (request.getParameter("LibrarianRegister") != null) {
            // 跳转至图书馆管理员注册界面
            response.sendRedirect("LibrarianRegister");
        }

         // 页眉Logo按钮
        else if (request.getParameter("mainpage") != null) {
            response.sendRedirect("MainPage");
        }

        else if (request.getParameter("logout") != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", "");
            session.setAttribute("identity", "");
            response.sendRedirect("MainPage");
        }
    }
}