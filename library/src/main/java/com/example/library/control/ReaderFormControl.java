package com.example.library.control;

import com.example.library.database.src.team.library.demo.Book;
import com.example.library.database.src.team.library.demo.CheckoutInfo;
import com.example.library.database.src.team.library.demo.ResvInfo;
import com.example.library.database.src.team.library.demo.ReturnInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
public class ReaderFormControl {
    @RequestMapping(value = "/ReaderForm", method = RequestMethod.GET)
    public ModelAndView getReaderForm(ModelAndView mv , HttpServletRequest request) {
        HttpSession session = request.getSession();
        String reader_id=session.getAttribute("username").toString();

        List<ResvInfo> appointment=  Book.showResvtoreader(reader_id);
        mv.addObject("appointment",appointment);
        List<CheckoutInfo> lend =    Book.showcheckouttoreader(reader_id);
        mv.addObject("lend",lend);
        List<ReturnInfo> returnform = Book.showReturntoreader(reader_id);
        mv.addObject("return",returnform);




        mv.setViewName("/ReaderForm");
        return mv;
    }

    @RequestMapping(value = "/ReaderForm", method = RequestMethod.POST)
    public ModelAndView postBookManagement(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        // 页眉Logo按钮
        if (request.getParameter("mainpage") != null) {
            response.sendRedirect("MainPage");
            return mv;
        }

        return mv;
    }
}
