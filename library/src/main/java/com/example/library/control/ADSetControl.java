/*
超管修改罚金等内容
127.0.0.1:8888/ADSet
*/
package com.example.library.control;
import com.example.library.database.src.team.library.demo.Book;
import com.example.library.database.src.team.library.demo.DatabaseController;
import com.example.library.database.src.team.library.demo.Librarian;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;

@RestController
public  class ADSetControl
{
    @RequestMapping(value = "/ADSet", method = RequestMethod.GET)
    public ModelAndView getADSet(ModelAndView mv) {
        mv.setViewName("/ADSet");
        return mv;
    }

    @RequestMapping(value = "/ADSet", method = RequestMethod.POST)
    public ModelAndView postADSet(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        if (request.getParameter("ADSet") != null) {

            String penalty = request.getParameter("penalty");
            BigDecimal fine = new BigDecimal (penalty);
            Librarian.changefine(fine);
            String period = request.getParameter("period");
            int days =Integer.parseInt(period);
            Librarian.changedays(days);
            String depositPaid = request.getParameter("depositPaid");
        }
            // 页眉Logo按钮
        else if (request.getParameter("mainpage") != null) {
            response.sendRedirect("MainPage");
            return mv;
        }
        return mv;
    }
}