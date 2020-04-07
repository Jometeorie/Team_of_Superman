/*
添加书籍的相关界面
127.0.0.1:8888/AddBook
*/
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
import java.math.BigDecimal;
import java.io.File;

@RestController
public class AddBookControl
{
    @RequestMapping(value = "/AddBook", method = RequestMethod.GET)
    public ModelAndView getAddBook(ModelAndView mv) {
        mv.setViewName("/AddBook");
        return mv;
    }

    @RequestMapping(value = "/AddBook", method = RequestMethod.POST) 
    public ModelAndView postAddBook(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        // 添加书籍按钮
        if (request.getParameter("AddBook") != null) {
            String bookName = request.getParameter("name");
            // String ISBN = request.getParameter("ISBN");
            String author = request.getParameter("author");
            String place = request.getParameter("place");
            BigDecimal price = new BigDecimal(request.getParameter("price"));
            // String intro = request.getParameter("intro");
            // 判断是否添加书籍成功
            boolean isInsert = Book.InsertBook(Book.getUUID(), bookName, author, place, price);
            if (isInsert) {
                
            }
            File coverPath = new File("cover");
            if ( !coverPath.exists()) {
                coverPath.mkdir();
            }
            return mv;
        }

        // 页眉Logo按钮
        else if (request.getParameter("mainpage") != null) {
            response.sendRedirect("MainPage");
            return mv;
        }
        return mv;
    }
}