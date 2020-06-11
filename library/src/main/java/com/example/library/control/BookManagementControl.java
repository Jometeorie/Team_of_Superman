package com.example.library.control;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.example.library.database.src.team.library.demo.*;

// import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class BookManagementControl {
    @RequestMapping(value = "/BookManagement", method = RequestMethod.GET)
    public ModelAndView getBookManagement(ModelAndView mv, String search_name) {

        List<BookInfo> book = new Book().SearchBook(search_name);
        mv.addObject("book", book);
        mv.addObject("coverPath", "cover/");
        mv.addObject("barCodePath", "BarCode/");

        mv.setViewName("/BookManagement");
        return mv;
    }
    public ModelAndView getBookManagement(ModelAndView mv) {
        String search_name = new String("");
        return getBookManagement(mv, search_name);
    } 


    // 修改书籍信息
    @RequestMapping(value = "BookManagement/Modify/{bookID}/{bookName}/{author}/{location}/{price}/{category}")
    @ResponseBody
    public ModelAndView changemessage(@PathVariable("bookID") String bookID, @PathVariable ("bookName") String bookName, 
    @PathVariable("author") String author, @PathVariable("location") String location, @PathVariable("price") BigDecimal price,
    @PathVariable("category") String category, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        Book.Editauthor(author,bookID);
        Book.EditBook_Name(bookName,bookID);
        Book.EditLocation(location,bookID);
        Book.EditCategory(category,bookID);
        Book.EditPrice(price,bookID);
        return new ModelAndView("redirect:/BookManagement");
    }

    // 删除书籍信息
    @RequestMapping(value = "BookManagement/Delete/{bookID}/{bookName}")
    @ResponseBody
    public ModelAndView deletemessage(@PathVariable("bookID") String bookID, @PathVariable("bookName") String bookName, 
    HttpServletRequest request, HttpServletResponse response)  throws IOException {
       HttpSession session = request.getSession();
        String Libr_id=session.getAttribute("username").toString();
        Book.DeleteBook(Libr_id,bookID,bookName);
       return new ModelAndView("redirect:/BookManagement");
    }

    @RequestMapping(value = "/BookManagement", method = RequestMethod.POST)
    public ModelAndView postBookManagement(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        // 页眉Logo按钮
        if (request.getParameter("mainpage") != null) {
            response.sendRedirect("MainPage");
            return mv;
        }
        return mv;
    }
}