package com.example.library.control;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.example.library.database.src.team.library.demo.*;

// import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@RestController
public class BookManagementControl {
    @RequestMapping(value = "/BookManagement", method = RequestMethod.GET)
    public ModelAndView getBookManagement(ModelAndView mv) {

        List<BookInfo> book = new Book().SearchBook("");
        mv.addObject("book", book);
        mv.addObject("coverPath", "cover/");
        mv.addObject("barCodePath", "BarCode/");

        mv.setViewName("/BookManagement");
        return mv;
    }

    @RequestMapping(value = "/BookManagement", method = RequestMethod.POST)
    public ModelAndView postBookManagement(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        // 页眉Logo按钮
        if (request.getParameter("mainpage") != null) {
            response.sendRedirect("MainPage");
            return mv;
        }

        return mv;
    }
}