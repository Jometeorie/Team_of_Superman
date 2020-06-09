/*
种类排名界面
127.0.0.1:8888/RankingList
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
public class RankingListControl
{
    @RequestMapping(value = "/RankingList", method = RequestMethod.GET)
    public ModelAndView getSearch(ModelAndView mv) {
        List<TypeInfo> typeRank = Book.GetTypeRank();
        mv.addObject("category", typeRank);
        return mv;
    } 


    @RequestMapping(value = "/RankingList", method = RequestMethod.POST) 
    public ModelAndView postSearch(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        // 页眉Logo按钮
        if (request.getParameter("mainpage") != null) {
            response.sendRedirect("MainPage");
        }

        return mv;
    }

}