package com.example.library.control;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
 
@RestController
public class IndexControl
{
    @RequestMapping(value = "/index")
    public ModelAndView test(ModelAndView mv) {
        mv.setViewName("/index");
        mv.addObject("title", "啦啦啦");
        return mv;
    }
}