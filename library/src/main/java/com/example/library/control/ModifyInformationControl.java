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
// import java.util.List;

@RestController
public class ModifyInformationControl {
    @RequestMapping(value = "/ModifyInformation", method = RequestMethod.GET)
    public ModelAndView getModifyInformation(ModelAndView mv) {

        return mv;
    }

    @RequestMapping(value = "/ModifyInformation", method = RequestMethod.POST)
    public ModelAndView postModifyInformation(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        // 页眉Logo按钮
        if (request.getParameter("mainpage") != null) {
            response.sendRedirect("MainPage");
            return mv;
        }

        else if (request.getParameter("logout") != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", "");
            session.setAttribute("identity", "");
            response.sendRedirect("MainPage");
            return mv;
        }

        if (request.getParameter("ConfirmToModify") != null) {
            String email = request.getParameter("Email");
            String name = request.getParameter("Name");

            HttpSession session = request.getSession();
            String readerID = session.getAttribute("username").toString();
            Reader.E_mailModify(readerID, email);
            Reader.NameModify(readerID, name);

            response.sendRedirect("ReaderPage");
            return mv;
        }

        return mv;
    }
}