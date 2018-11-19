package com.smallcc.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
public class JsonFileController {
    @GetMapping("/getzh")
    @ResponseBody
    public String getZh(){
        return "zh.json";
    }
}
