/*
图书馆管理员查看图书馆收入
127.0.0.1:8888/LibraryRevenue
*/
package com.example.library.control;
import com.example.library.database.src.team.library.demo.Book;
import com.example.library.database.src.team.library.demo.MoneyTakeMonthlyInfo;
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
public class LibraryRevenueControl
{
    @RequestMapping(value = "/LibraryRevenue", method = RequestMethod.GET)
    public ModelAndView getLibraryRevenue(ModelAndView mv) {

        List<MoneyTakeMonthlyInfo> penalty = Book.showMoneyTakeMonthlyInfo(0);
        mv.addObject("penalty", penalty);
        List<MoneyTakeMonthlyInfo> deposit = Book.showMoneyTakeMonthlyInfo(1);
        mv.addObject("deposit",deposit);


            mv.setViewName("/LibraryRevenue");
        return mv;
    }

    @RequestMapping(value = "/LibraryRevenue", method = RequestMethod.POST)
    public ModelAndView postLibraryRevenue(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException {
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

        return mv;
    }
}