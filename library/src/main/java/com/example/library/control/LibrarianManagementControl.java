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
public class LibrarianManagementControl {
    @RequestMapping(value = "/LibrarianManagement", method = RequestMethod.GET)
    public ModelAndView getSearch(ModelAndView mv, String search_name) {
        List<LibrarianInfo> librarian = new Search().SearchLibrByName(search_name);
        mv.addObject("librarian", librarian);

        mv.setViewName("/LibrarianManagement");
        return mv;
    }
    public ModelAndView getSearch(ModelAndView mv) {
        String search_name = new String("");
        return getSearch(mv, search_name);
    } 

    @RequestMapping(value = "/LibrarianManagement", method = RequestMethod.POST)
    public ModelAndView postLibrarianManagement(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        // 管理员搜索按钮
        if (request.getParameter("search_button") != null) {
            String search_name = request.getParameter("search");
            return getSearch(mv, search_name);
        }

        // 页眉Logo按钮
        if (request.getParameter("mainpage") != null) {
            response.sendRedirect("MainPage");
            return mv;
        }

        return mv;
    }
}