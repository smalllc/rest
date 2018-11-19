package com.smallcc.rest.controller;

import com.smallcc.rest.pojo.PicUploadResult;
import com.smallcc.rest.service.PropertyService;
import com.smallcc.rest.utils.HttpClientUtils;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @Description: TODO
 * @Copyright: Copyright (c) 2018
 * @Company: www.linkw.com
 * @author: smallcc
 * @ProjectName:fileController.java
 * @Date: 2018/8/8
 * @Time:18:22
**/
@Controller
public class fileController {
    @Autowired
    private  PropertyService propertyService;
    @RequestMapping(value = "/upload/image",produces=MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String upload(@RequestParam(value = "testFile", required = false) MultipartFile[] file,
                         @RequestParam(value = "file",required = false) MultipartFile logoFile,
                         @RequestParam(value = "appid") String appid,
                         HttpServletRequest request) throws Exception {
        String imgs=uploadFileList(file, request);

//        String substring = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        String sublog = logoFile.getOriginalFilename().substring(logoFile.getOriginalFilename().lastIndexOf(".") + 1);
        //logo地址
        String logo=propertyService.REPOSITORY_PATH+"/logo/"+new Date().getTime()+"."+sublog;
        String logoURL=propertyService.IMAGE_BASE_URL+"/logo/"+new Date().getTime()+"."+sublog;
        logoFile.transferTo(new File(logo));
        Map<String,String> map=new HashMap<>();
        map.put("appid",appid);
        map.put("logo",logoURL);
        map.put("imgs",imgs);
        String s = HttpClientUtils.doPost("http://118.24.121.59:8080/d4app/v1/dapp/update", map);
        return s;
    }
    public  String uploadFileList(MultipartFile[] multipartFiles, HttpServletRequest request) throws Exception, Exception {
        String imgsURL="";
        if (multipartFiles==null){
            return imgsURL;
        }
        for (MultipartFile multipartFile:multipartFiles) {
            String substring = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
            String imgs=propertyService.REPOSITORY_PATH+"/image/"+new Date().getTime()+"."+substring;
            imgsURL+=propertyService.IMAGE_BASE_URL+"/image/"+new Date().getTime()+"."+substring+",";
            multipartFile.transferTo(new File(imgs));
        }
        return imgsURL.substring(0,imgsURL.length()-1);
    }
}
