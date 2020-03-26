package com.example.library.control;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.example.library.database.src.team.library.demo.*;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// This is just for test.
@RestController
public class IndexControl
{
    @RequestMapping(value = "/index")
    public ModelAndView test(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {
        mv.setViewName("/index");
        // mv.addObject("title", "啦啦啦");

        String id = new String("123");
        String passwd = new String("1234");
        boolean flag =new Reader().ReaderLogin(id,passwd);

        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("username"));
        mv.addObject("title", flag);
        return mv;
    }
}