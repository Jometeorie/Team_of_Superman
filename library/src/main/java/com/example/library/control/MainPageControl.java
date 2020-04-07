package com.example.library.control;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@RestController
public class MainPageControl
{
    @RequestMapping(value = "/MainPage", method = RequestMethod.GET)
    public ModelAndView getMainPage(ModelAndView mv) {
        mv.setViewName("/MainPage");
        return mv;
    }

    @RequestMapping(value = "/MainPage", method = RequestMethod.POST)
    public ModelAndView postMainPage(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        if (request.getParameter("mainpage") != null) {
            response.sendRedirect("MainPage");
            return mv;
        }
        return mv;
    }
}