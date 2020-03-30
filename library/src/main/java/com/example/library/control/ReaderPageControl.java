package com.example.library.control;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ReaderPageControl
{
    @RequestMapping(value = "/ReaderPage", method = RequestMethod.GET)
    public ModelAndView test(ModelAndView mv) {
        mv.setViewName("/ReaderPage");
        return mv;
    }

    // @RequestMapping(value = "/ReaderPage", method = RequestMethod.POST HttpServletRequest request, HttpServletResponse response)

}