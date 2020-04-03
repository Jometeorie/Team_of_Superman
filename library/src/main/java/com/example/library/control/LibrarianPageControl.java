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
public class LibrarianPageControl
{
    @RequestMapping(value = "/LibrarianPage", method = RequestMethod.GET)
    public ModelAndView test(ModelAndView mv) {
        mv.setViewName("/LibrarianPage");
        return mv;
    }
    
    @RequestMapping(value = "/LibrarianPage", method = RequestMethod.POST) 
    public void postRegister(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        if (request.getParameter("ReaderRegister") != null) {
            response.sendRedirect("ReaderRegister");
        }
        else if (request.getParameter("AddBooks") != null) {
            response.sendRedirect("AddBook");
        }
        else if (request.getParameter("DeleteBooks") != null) {
            response.sendRedirect("DeleteBook");
        }
    }
        
    

}