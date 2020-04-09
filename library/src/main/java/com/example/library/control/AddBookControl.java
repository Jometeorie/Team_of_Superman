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
import com.example.library.utils.*;

// import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.math.BigDecimal;
// import java.nio.Buffer;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import java.util.List;

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
            String resourcePath = "library/src/main/resources/static/";
            File coverPath = new File(resourcePath + "cover");
            if ( !coverPath.exists()) {
                coverPath.mkdir();
            }

            String bookID = Book.getUUID();
            boolean isUploadFile = AddBookControl.upLoadFile(request, bookID, resourcePath);
            if (isUploadFile) {
                System.out.println("Success Upload File.");
            }
            
            String bookName = request.getParameter("name");
            // String ISBN = request.getParameter("ISBN");
            String author = request.getParameter("author");
            String place = request.getParameter("place");
            BigDecimal price = new BigDecimal(request.getParameter("price"));
            // String intro = request.getParameter("intro");
            // 判断是否添加书籍成功
            boolean isInsert = Book.InsertBook(bookID, bookName, author, place, price);
            if (isInsert) {
                // 添加条形码
                BarCode.generateFile(bookID, "library/src/main/resources/static/BarCode/" + bookID + ".png");
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

    public static boolean upLoadFile(HttpServletRequest request, String bookID, String resourcePath) {
        // 上传文件
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)    
                .getFiles("cover");  
        MultipartFile file = null;    
        BufferedOutputStream stream = null; 
        for (int i = 0; i < files.size(); ++i) {    
            file = files.get(i); 
            
            if (!file.isEmpty()) {    
                try {
                    byte[] bytes = file.getBytes();    
                    stream = new BufferedOutputStream(new FileOutputStream(    
                            new File(resourcePath + "cover/" + bookID + '.' + file.getOriginalFilename().split("\\.")[1])));
                    stream.write(bytes); 
                    stream.close(); 
                } catch (Exception e) {    
                    stream = null;    
                    return false;
                }    
            } else {    
                return false;
            }  
        }    
        return true;
    }
}