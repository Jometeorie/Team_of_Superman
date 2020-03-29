package com.example.library.control;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.example.library.database.src.team.library.demo.*;

import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginPageControl
{
    @RequestMapping(value = "/LoginPage", method = RequestMethod.GET)
    public ModelAndView getView(ModelAndView mv) {
        mv.setViewName("/LoginPage");
        return mv;
    }

    @RequestMapping(value = "/LoginPage", method = RequestMethod.POST)
    public ModelAndView postLogin(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {
        String identify = request.getParameter("identify");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (identify.equals("librarian")) {
            boolean isLogin =new Librarian().LibrLogin(username,password);
            if (isLogin) {
                System.out.println("Successed login librarian!");
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
            }
            else {
                System.out.println("Failed login librarian!");
            }
            mv.addObject("isLogin", isLogin);
        }

        if (identify.equals("reader")) {
            boolean isLogin =new Reader().ReaderLogin(username,password);
            if (isLogin) {
                System.out.println("Successed login librarian!");
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
            }
            else {
                System.out.println("Failed login librarian!");
            }
            mv.addObject("isLogin", isLogin);
        }

        return mv;
    }
}