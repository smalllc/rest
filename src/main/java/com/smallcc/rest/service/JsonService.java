package com.smallcc.rest.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @Description: jsonService处理
 * @Copyright: Copyright (c) 2018
 * @Company: www.linkw.com
 * @author: smallcc
 * @ProjectName:JsonService.java
 * @Date: 2018/8/23
 * @Time:18:27
**/

@CacheConfig
public interface JsonService {
    @Cacheable(cacheNames = "json")
    String getJson(String symbol);
    @Cacheable(cacheNames = "jsonData")
    String getNewJson(String symbol);
    @Cacheable(cacheNames = "latest")
    @Deprecated
    String getLatest();
    @Cacheable(cacheNames = "rate")
    String getRateJson();
    @Cacheable(cacheNames = "get_rate")
    String getRateJsonData();
}
