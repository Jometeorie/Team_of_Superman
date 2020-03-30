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
public class LibrarianLoginControl
{
    @RequestMapping(value = "/LibrarianLogin", method = RequestMethod.GET)
    public ModelAndView getView(ModelAndView mv) {
        mv.setViewName("/LibrarianLogin");
        return mv;
    }

    @RequestMapping(value = "/LibrarianLogin", method = RequestMethod.POST)
    public  void postLogin(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isLogin =new Librarian().LibrLogin(username,password);
        if (isLogin) {
            System.out.println("Successed login librarian!");
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("LibrarianPage");
        }
        else {
            System.out.println("Failed login librarian!");
        }
        mv.addObject("isLogin", isLogin);
    }
}