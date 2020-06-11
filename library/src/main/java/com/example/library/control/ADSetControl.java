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
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;

@RestController
public  class ADSetControl
{
    @RequestMapping(value = "/ADSet", method = RequestMethod.GET)
    public ModelAndView getADSet(ModelAndView mv) {
        mv.addObject("penalty", Librarian.getFineperday());
        mv.addObject("period", Librarian.getDays());
        mv.addObject("depositPaid", Librarian.getDeposit());
        mv.setViewName("/ADSet");
        return mv;
    }

    @RequestMapping(value = "/ADSet", method = RequestMethod.POST)
    public ModelAndView postADSet(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        if (request.getParameter("ADSetChange") != null) {
            String penalty = request.getParameter("penaltytxt");
            BigDecimal fine = new BigDecimal (penalty);
            Librarian.changefine(fine);
            String period = request.getParameter("periodtxt");
            int days =Integer.parseInt(period);
            Librarian.changedays(days);
            String depositPaidString = request.getParameter("depositPaidtxt");Librarian.getDeposit();
            BigDecimal depositPaid = new BigDecimal (depositPaidString);
            Librarian.changedeposit(depositPaid);
            mv.addObject("penalty", penalty);
            mv.addObject("period", period);
            mv.addObject("depositPaid", depositPaidString);
            mv.setViewName("/ADSet");
        }
            // 页眉Logo按钮
        else if (request.getParameter("mainpage") != null) {
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
        return mv;
    }
}