package com.example.library.control;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
// import com.example.library.database.src.team.library.demo.*;

// import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@RestController
public class ADPageControl
{
    @RequestMapping(value = "/ADPage", method = RequestMethod.GET)
    public ModelAndView getADPage(ModelAndView mv) {
        mv.setViewName("/ADPage");
        return mv;
    }

    @RequestMapping(value = "/ADPage", method = RequestMethod.POST) 
    public void postADPage(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        if (request.getParameter("LibrarianRegister") != null) {
            response.sendRedirect("LibrarianRegister");
        }

        else if (request.getParameter("mainpage") != null) {
            response.sendRedirect("MainPage");
        }
    }
}