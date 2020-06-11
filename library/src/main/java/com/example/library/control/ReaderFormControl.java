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
// import javax.servlet.http.HttpSession;

// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class ReaderFormControl {
    @RequestMapping(value = "/ReaderForm", method = RequestMethod.GET)
    public ModelAndView getReaderForm(ModelAndView mv , HttpServletRequest request) {
        HttpSession session = request.getSession();
        String reader_id=session.getAttribute("username").toString();

        List<ResvInfo> appointment=  Book.showResvtoreader(reader_id);
        mv.addObject("appointment",appointment);
        List<LendInfo> lend =    Book.showLendToReader(reader_id);
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

        else if (request.getParameter("logout") != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", "");
            session.setAttribute("identity", "");
            response.sendRedirect("MainPage");
            return mv;
        }

        return mv;
    }

    //申请还书
    @RequestMapping(value = "R1eturnRequest/{book_id}/{book_name}/{reader_id}/{lend_time}")
    @ResponseBody
    public ModelAndView readerReturn(@PathVariable("book_id") String book_id, 
                                                    @PathVariable("book_name") String book_name, @PathVariable("reader_id") String reader_id, 
                                                    @PathVariable("lend_time") String lend_time, HttpServletRequest request, 
                                                    HttpServletResponse response) throws IOException {
        // HttpSession session = request.getSession();
        String libr_id = Book.getLibrID(book_id);
        String checkout_id = Book.getUUID();
        long nowTime = System.currentTimeMillis();
        Date nowDate = new Date(nowTime);
        // String reader_name = Book.getreadername(reader_id);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String return_time = df.format(nowDate);
        Book.returnBook(checkout_id, libr_id, book_id, book_name, reader_id, return_time);

        return new ModelAndView("redirect:/ReaderForm");
    }
}
