/*
图书馆管理员发布公告
127.0.0.1:8888/UpdatePost
*/
package com.example.library.control;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.library.database.src.team.library.demo.Librarian;

import java.io.IOException;

@RestController
public class UpdatePostControl
{
    @RequestMapping(value = "/UpdatePost", method = RequestMethod.GET)
    public ModelAndView getUpdatePost(ModelAndView mv) {
        mv.setViewName("/UpdatePost");
        return mv;
    }

    @RequestMapping(value = "/UpdatePost", method = RequestMethod.POST)
    public ModelAndView postUpdatePost(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        // 页眉Logo按钮
        if (request.getParameter("mainpage") != null) {
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

        if (request.getParameter("UpdatePost") != null) {
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            Librarian.title = title;
            Librarian.content = content;
        }

        return mv;
    }
}