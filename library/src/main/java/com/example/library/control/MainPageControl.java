package com.example.library.control;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.example.library.database.src.team.library.demo.*;

@RestController
public class MainPageControl
{
    @RequestMapping(value = "/Mainpage", method = RequestMethod.GET)
    public ModelAndView test(ModelAndView mv) {
        mv.setViewName("/Mainpage");
        return mv;
    }

    // @RequestMapping(value = "/Mainpage", method = RequestMethod.POST HttpServletRequest request, HttpServletResponse response)

}