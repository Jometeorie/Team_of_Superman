package com.example.library.control;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.example.library.database.src.team.library.demo.*;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ReaderRegisterControl
{
    @RequestMapping(value = "/ReaderRegister", method = RequestMethod.GET)
    public ModelAndView getView(ModelAndView mv) {
        mv.setViewName("/ReaderRegister");
        return mv;
    }

    @RequestMapping(value = "/ReaderRegister", method = RequestMethod.POST)
    public ModelAndView postRegister(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String gender = request.getParameter("gender");
        String bond = request.getParameter("bond");

        // String id = String.valueOf(new Random().nextInt(99999999));
        boolean isRegister =new Librarian().ReaderRegister(tel, username, password);
        if (isRegister) {
            System.out.println("Successed register admin!");
        }
        else {
            System.out.println("Failed register admin!");
        }
        mv.addObject("isRegister", isRegister);

        return mv;
    }
}