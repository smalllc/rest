package com.smallcc.rest.controller;

import com.smallcc.rest.service.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 帮助前段中转数据
 * @Copyright: Copyright (c) 2018
 * @Company: www.linkw.com
 * @author: smallcc
 * @ProjectName:JsonController.java
 * @Date: 2018/8/23
 * @Time:18:26
**/
@RestController
@CrossOrigin
public class JsonController {
    @Autowired
    private JsonService jsonService;
    @GetMapping(value = "/json")
    public String getJsonData(@RequestParam( value = "symbol" ,required = false,defaultValue = "") String symbol){
        return jsonService.getJson(symbol);
    }
    @GetMapping(value = "/price/json")
    public String getNewJson(@RequestParam( value = "symbol" ,required = false,defaultValue = "") String symbol){return jsonService.getNewJson(symbol);}
    @GetMapping(value = "/latest")
    public String getLatest(){
        return jsonService.getLatest();
    }
    @GetMapping(value = "/rate/json")
    public String getRate(){
        return jsonService.getRateJson();
    }
    @GetMapping(value = "/rate/get_rate")
    public String getRateTwo(){
        return jsonService.getRateJsonData();
    }

}
