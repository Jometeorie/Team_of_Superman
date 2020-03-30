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
public class AdminLoginControl
{
    @RequestMapping(value = "/AdminLogin", method = RequestMethod.GET)
    public ModelAndView getView(ModelAndView mv) {
        mv.setViewName("/AdminLogin");
        return mv;
    }

    @RequestMapping(value = "/AdminLogin", method = RequestMethod.POST)
    public void postLogin(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isLogin =new Admin().AdminLogin(username,password);
        if (isLogin) {
            System.out.println("Successed login librarian!");
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("ADPage");
        }
        else {
            System.out.println("Failed login librarian!");
        }
        mv.addObject("isLogin", isLogin);
    }
}