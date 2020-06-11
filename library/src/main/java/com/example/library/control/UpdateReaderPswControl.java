/*
读者设置新密码界面
127.0.0.1:8888/UpdateReaderPsw
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
public class UpdateReaderPswControl {
    @RequestMapping(value = "/UpdateReaderPsw", method = RequestMethod.GET)
    public ModelAndView getUpdateReaderPsw(ModelAndView mv) {
        mv.setViewName("/UpdateReaderPsw");
        return mv;
    }

    @RequestMapping(value = "/UpdateReaderPsw", method = RequestMethod.POST)
    public ModelAndView postUpdateReaderPsw(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException{
        mv.setViewName("/UpdateReaderPsw");
        if (request.getParameter("UpdatePsw") != null) {
            String username = request.getSession().getAttribute("username").toString();
            String password = request.getParameter("new_password");

            boolean isUpdate = new Reader().PasswordModify(username, password);
            if (isUpdate) {
                System.out.println("Successed update!");
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect("ReaderLogin");
                // 前端判断是否成功的依据，0为成功，非0为失败
                mv.addObject("value_judg", 0);
            }
            else {
                System.out.println("Failed!");
                mv.addObject("value_judg", 1);
            }
            mv.addObject("isUpdate", isUpdate);
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
