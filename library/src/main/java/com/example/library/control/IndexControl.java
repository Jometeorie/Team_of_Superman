package com.example.library.control;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexControl
{
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView test(ModelAndView mv) {
        mv.setViewName("/MainPage");
        return mv;
    }

    // @RequestMapping(value = "/Mainpage", method = RequestMethod.POST HttpServletRequest request, HttpServletResponse response)

}