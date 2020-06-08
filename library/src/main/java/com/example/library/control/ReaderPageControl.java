/*
读者主界面
127.0.0.1:8888/ReaderPage
*/
package com.example.library.control;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.example.library.database.src.team.library.demo.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import java.math.BigDecimal;

@RestController
public class ReaderPageControl
{
    @RequestMapping(value = "/ReaderPage", method = RequestMethod.GET)
    public ModelAndView test(ModelAndView mv, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String readerID=session.getAttribute("username").toString();
        // 读者要缴纳的罚金
        BigDecimal payMoney =new Reader().getfine(readerID);
        mv.addObject("payMoney",payMoney);
        mv.setViewName("/ReaderPage");
        return mv;
    }

    @RequestMapping(value = "/ReaderPage", method = RequestMethod.POST)
    public ModelAndView postMainPage(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        // 页眉Logo按钮
        if (request.getParameter("mainpage") != null) {
            response.sendRedirect("MainPage");
            return mv;
        }
        if (request.getParameter("search_button") != null) {
            ModelAndView model = new ModelAndView("redirect:/SearchPage");
            String  searchName = request.getParameter("search");
            model.addObject("search_name", searchName);  
            return model;
        }
        return mv;
    }

}