package com.smallcc.rest.service.impl;

import com.alibaba.fastjson.JSON;
import com.smallcc.rest.service.JsonService;
import com.smallcc.rest.utils.HttpClientUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: json数据处理层
 * @Copyright: Copyright (c) 2018
 * @Company: www.linkw.com
 * @author: smallcc
 * @ProjectName:JsonServiceImpl.java
 * @Date: 2018/8/23
 * @Time:18:28
**/
@Service
public class JsonServiceImpl implements JsonService {
    @Value("${appkey}")
    private String appkey;
    @Value("${sign}")
    private String sign;
    @Override
    public String getJson(String symbol) {
        Map<String,String> map=new HashMap<>();
        map.put("symbol",symbol);
        String s;
        if (StringUtils.isNotBlank(symbol)) {
             s = HttpClientUtils.doGet("https://api.binance.com/api/v1/ticker/24hr",map);
            return s;
        }else {
             s = HttpClientUtils.doGet("https://api.binance.com/api/v1/ticker/24hr");
            return s;
        }

    }

    @Override
    public String getNewJson(String symbol) {
        Map<String,String> map=new HashMap<>();
        map.put("symbol",symbol);
        if (StringUtils.isNotBlank(symbol)) {
            String s = HttpClientUtils.doGet("https://api.binance.com/api/v3/ticker/price",map);
            return s;
        }else {
            String s = HttpClientUtils.doGet("https://api.binance.com/api/v3/ticker/price");
            return s;
        }
    }

    @Override
    public String getLatest() {
        String s = HttpClientUtils.doGet("http://data.fixer.io/api/latest?access_key=bcc914c680db2eaefbedfa9d3d883989&symbols=USD,AUD,CAD,PLN,MXN&format=1");
        return s;
    }

    /**
     * 获取汇率
     * @return
     */
    @Override
    public String getRateJson() {
        String json = "";
        String data = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("projectBean.startDate", data);
        requestEntity.add("projectBean.endDate", data);
        requestEntity.add("queryYN", "true");

        ResponseEntity<String> response = restTemplate.postForEntity("http://www.safe.gov.cn/AppStructured/hlw/RMBQuery.do", requestEntity, String.class);
        String html = response.getBody();
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("table[class=list]");
        if (elements.size() == 1)
        
        {
            Element table = elements.get(0);
            Elements tds = table.select("tr td");
            Elements ths = table.select("tr th");
            HashMap<String,Object> map =new HashMap<>();
            for (int i = 0; i < ths.size(); i++)
            {
                map.put(ths.get(i).text(),tds.get(i).text());
            }
             json= JSON.toJSONString(map);
        }

        return json;
    }

    /**
     * 获取新的汇率接口
     * @return
     */
    @Override
    public String getRateJsonData() {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String result = HttpClientUtils.doGet("http://www.safe.gov.cn/AppStructured/hlw/jsonRmb.do?date=" + date);
        return result;
    }
}
