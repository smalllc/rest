package com.smallcc.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @Description: 配置文件解析器
 * @Copyright: Copyright (c) 2018
 * @Company: www.linkw.com
 * @author: smallcc
 * @ProjectName:FileConfig.java
 * @Date: 2018/8/8
 * @Time:14:57
**/
@Component
@Configuration
public class FileConfig {
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        resolver.setResolveLazily(true);//resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
        resolver.setMaxInMemorySize(40960);
        resolver.setMaxUploadSize(20*1024*1024);//上传文件大小 20M 50*1024*1024
        System.out.println("加载成功");
        return resolver;
    }
}
