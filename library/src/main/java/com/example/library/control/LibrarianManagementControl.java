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
    public ModelAndView getLibrarianManagement(ModelAndView mv) {

        return mv;
    }

    @RequestMapping(value = "/LibrarianManagement", method = RequestMethod.POST)
    public ModelAndView postLibrarianManagement(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        // 页眉Logo按钮
        if (request.getParameter("mainpage") != null) {
            response.sendRedirect("MainPage");
            return mv;
        }

        return mv;
    }
}