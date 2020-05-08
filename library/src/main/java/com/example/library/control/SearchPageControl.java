/*
书籍搜索界面
127.0.0.1:8888/SearchPage
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
public class SearchPageControl
{
    @RequestMapping(value = "/SearchPage", method = RequestMethod.GET)
    public ModelAndView getSearch(ModelAndView mv, String search_name) {
        List<BookInfo> book = new Book().SearchBook(search_name);
        mv.addObject("book", book);
        mv.addObject("coverPath", "cover/");
        mv.addObject("barCodePath", "BarCode/");
        mv.setViewName("/SearchPage");
        return mv;
    }
    public ModelAndView getSearch(ModelAndView mv) {
        String search_name = new String("");
        return getSearch(mv, search_name);
    } 


    @RequestMapping(value = "/SearchPage", method = RequestMethod.POST) 
    public ModelAndView postSearch(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        // 书籍搜索按钮
        if (request.getParameter("search_button") != null) {
            String search_name = request.getParameter("search");
            return getSearch(mv, search_name);
        }

        // 页眉Logo按钮
        else if (request.getParameter("mainpage") != null) {
            response.sendRedirect("MainPage");
        }

        // reservation按钮
        else if (request.getParameter("reservation") != null) {
            response.sendRedirect("MainPage");
        }

        return mv;
    }

    // 处理读者预约一本书的响应
    @RequestMapping(value = "SearchPage/{bookID}/{bookName}")
    @ResponseBody
    public ModelAndView getReserve(@PathVariable ("bookID") String bookID, @PathVariable ("bookName") String bookName, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        long beginTime = System.currentTimeMillis();
        // EndTime，一小时后自动删除记录
        long endTime = beginTime + 30*60*1000;
        Date beginDate = new Date(beginTime);
        Date endDate = new Date(endTime);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String reserveBeginTime = df.format(beginDate);
        String reserveEndTime = df.format(endDate);

        HttpSession session = request.getSession();
        String readerID = session.getAttribute("username").toString();

        Book.reservebook(Book.getUUID(), bookID.split("\\.")[0], bookName, reserveBeginTime, 
                                                reserveEndTime, readerID);

        return new ModelAndView("redirect:/SearchPage");
    }

}