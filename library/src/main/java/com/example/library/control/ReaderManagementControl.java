package com.example.library.control;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.example.library.database.src.team.library.demo.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@RestController
public class ReaderManagementControl {
    @RequestMapping(value = "/ReaderManagement", method = RequestMethod.GET)
    public ModelAndView getSearch(ModelAndView mv, String search_name) {
        List<ReaderInfo> reader = new Search().SearchReaderByName(search_name);
        mv.addObject("reader", reader);

        mv.setViewName("/ReaderManagement");
        return mv;
    }
    public ModelAndView getSearch(ModelAndView mv) {
        String search_name = new String("");
        return getSearch(mv, search_name);
    } 

    @RequestMapping(value = "/ReaderManagement", method = RequestMethod.POST)
    public ModelAndView postReaderManagement(ModelAndView mv, HttpServletRequest request, HttpServletResponse response)  throws IOException {
        // 管理员搜索按钮
        if (request.getParameter("search_button") != null) {
            String search_name = request.getParameter("search");
            return getSearch(mv, search_name);
        }

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

        // 修改读者信息
        @RequestMapping(value = "ReaderManagement/Modify/{id}/{name}/{e_mail}")
        @ResponseBody
        public ModelAndView changeReader(@PathVariable ("id") String id, @PathVariable("name") String name, 
        @PathVariable ("e_mail") String e_mail, HttpServletRequest request, HttpServletResponse response)  throws IOException {
            Reader.E_mailModify(id,e_mail);
            Reader.NameModify(id,name);
            //Reader.PasswordModify(id,password);
            //Reader.TelephoneModify(id,telephone);
           return new ModelAndView("redirect:/ReaderManagement");
        }
    
        // 删除读者信息
        @RequestMapping(value = "ReaderManagement/Delete/{id}")
        @ResponseBody
        public ModelAndView deleteReader(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response)  throws IOException {
            Librarian.DeleteReader(id);
           return new ModelAndView("redirect:/ReaderManagement");
        }
}