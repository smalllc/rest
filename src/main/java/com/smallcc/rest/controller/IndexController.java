package com.smallcc.rest.controller;

import com.smallcc.rest.annotation.LoginRequired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexController {
    @LoginRequired
    @RequestMapping("/index")
    public ModelAndView showIndex(){
        return new ModelAndView("/index");
    }
}
