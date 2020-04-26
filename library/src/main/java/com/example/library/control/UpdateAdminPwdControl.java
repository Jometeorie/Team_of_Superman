/*
图书管理员设置新密码界面
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
public class UpdateAdminPwdControl {
    @RequestMapping(value = "/UpdateAdminPwd", method = RequestMethod.GET)
    public ModelAndView getUpdateLibrarianPsw(ModelAndView mv) {
        mv.setViewName("UpdateAdminPwd");
        return mv;
    }

    @RequestMapping(value = "/UpdateAdminPwd", method = RequestMethod.POST)
    public ModelAndView postUpdateLibrarianPsw(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException{
        mv.setViewName("UpdateAdminPwd");
        if (request.getParameter("UpdatePsw") != null) {
            String username = request.getSession().getAttribute("username").toString();
            String password = request.getParameter("new_password");

            boolean isUpdate = new Librarian().LPasswordModify(username, password);
            if (isUpdate) {
                System.out.println("Successed update!");
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect("LibrarianLogin");
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

        return mv;
    }
}
