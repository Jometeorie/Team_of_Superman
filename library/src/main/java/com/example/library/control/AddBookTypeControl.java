/*
超管修改罚金等内容
127.0.0.1:8888/ADSet
*/
package com.example.library.control;
import com.example.library.database.src.team.library.demo.Book;
// import com.example.library.database.src.team.library.demo.DatabaseController;
// import com.example.library.database.src.team.library.demo.Librarian;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
// import java.math.BigDecimal;

@RestController
public  class AddBookTypeControl
{
    @RequestMapping(value = "/AddBookType", method = RequestMethod.GET)
    public ModelAndView getAddBookType(ModelAndView mv) {

        mv.setViewName("/AddBookType");
        return mv;
    }

    @RequestMapping(value = "/AddBookType", method = RequestMethod.POST)
    public ModelAndView postAddBookType(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        if (request.getParameter("ConfirmToAdd") != null) {
            String bookType = request.getParameter("Type");
            int isAdd = Book.addType(bookType);
            mv.addObject("isAdd", isAdd);

            mv.setViewName("/AddBookType");
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