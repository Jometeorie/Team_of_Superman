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
import java.util.List;

@RestController
public class SearchPageControl
{
    @RequestMapping(value = "/SearchPage", method = RequestMethod.GET)
    public ModelAndView getSearch(ModelAndView mv, String search_name) {
        List<BookInfo> book = new Book().SearchBook(search_name);
        mv.addObject("book", book);
        mv.setViewName("/SearchPage");
        return mv;
    }
    public ModelAndView getSearch(ModelAndView mv) {
        String search_name = new String("");
        return getSearch(mv, search_name);
    } 


    @RequestMapping(value = "/SearchPage", method = RequestMethod.POST) 
    public ModelAndView postSearch(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        if (request.getParameter("search_button") != null) {
            String search_name = request.getParameter("search");
            return getSearch(mv, search_name);
        }
        else if (request.getParameter("mainpage") != null) {
            response.sendRedirect("MainPage");
        }
        return mv;
    }
}