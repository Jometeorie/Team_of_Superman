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
public class RegisterPageControl
{
    @RequestMapping(value = "/RegisterPage", method = RequestMethod.GET)
    public ModelAndView getView(ModelAndView mv) {
        mv.setViewName("/RegisterPage");
        return mv;
    }

    @RequestMapping(value = "/RegisterPage", method = RequestMethod.POST)
    public ModelAndView postRegister(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {
        String identify = request.getParameter("identify");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String money = request.getParameter("money");
        String gender = request.getParameter("gender");

        if (identify.equals("supermanager")) {
            String id = String.valueOf(new Random().nextInt(99999999));
            boolean isRegister =new Admin().AdminRegister(id, username, password);
            if (isRegister) {
                System.out.println("Successed register admin!");
            }
            else {
                System.out.println("Failed register admin!");
            }
        }
        return mv;
    }
}