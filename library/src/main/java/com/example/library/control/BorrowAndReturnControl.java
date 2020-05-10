/*
管理员处理借阅和还书请求的界面
127.0.0.1:8888/BorrowAndReturn
*/
package com.example.library.control;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.example.library.database.src.team.library.demo.*;

import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

@RestController
public class BorrowAndReturnControl
{
    @RequestMapping(value = "/BorrowAndReturn", method = RequestMethod.GET)
    public ModelAndView getBorrowAndReturn(ModelAndView mv) {

        List<ResvInfo> appointment  = Book.showResvList();
        // List<ReturnInfo> returns = Book.showReturnList();
        mv.addObject("appointment", appointment);
        // mv.addObject("returns", returns);
        mv.setViewName("/BorrowAndReturn");
        return mv;
    }


    @RequestMapping(value = "/BorrowAndReturn", method = RequestMethod.POST) 
    public ModelAndView postBorrowAndReturn(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        // 页眉Logo按钮
        if (request.getParameter("mainpage") != null) {
            response.sendRedirect("MainPage");
        }

        return mv;
    }

    // 批准借阅请求
    @RequestMapping(value = "BorrowRequest/{resv_id}/{book_id}/{book_name}/{reader_id}/{reader_name}")
    @ResponseBody
    public ModelAndView getReserve(@PathVariable ("resv_id") String resv_id, 
                                                    @PathVariable ("book_id") String book_id, @PathVariable ("book_name") String book_name,
                                                    @PathVariable ("reader_id") String reader_id, @PathVariable ("reader_name") String reader_name, 
                                                    HttpServletRequest request, HttpServletResponse response)  throws IOException {

        String checkout_id = Book.getUUID();
        HttpSession session = request.getSession();
        String libr_id = session.getAttribute("username").toString();
        long nowTime = System.currentTimeMillis();
        Date nowDate = new Date(nowTime);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String end_time = df.format(nowDate);
        CheckoutInfo checkoutInfo = new CheckoutInfo(checkout_id, libr_id, book_id, book_name, reader_id, end_time, reader_name);
        Book.EditResv(resv_id, book_id, true, checkoutInfo);

        return new ModelAndView("redirect:/BorrowAndReturn");
    }

    // 批准还书请求
    @RequestMapping(value = "ReturnRequest/{return_id}/{reader_id}/{return_time}/{book_id}")
    @ResponseBody
    public ModelAndView getReturn(@PathVariable("return_id") String return_id, 
                                                    @PathVariable("reader_id") String reader_id, @PathVariable("return_time") String return_time, 
                                                    @PathVariable("book_id") String book_id, HttpServletRequest request, 
                                                    HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String libr_id = session.getAttribute("username").toString();

        Book.BackBook(return_id, book_id, libr_id, reader_id, return_time);

        return new ModelAndView("redirect:/BorrowAndReturn");
    }
}