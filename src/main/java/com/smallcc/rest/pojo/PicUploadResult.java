package com.smallcc.rest.pojo;
/**
 * @Description: 文件上传返回的数据模型
 * @Copyright: Copyright (c) 2018
 * @Company: www.linkw.com
 * @author: smallcc
 * @ProjectName:PicUploadResult.java
 * @Date: 2018/8/8
 * @Time:15:33
**/
public class PicUploadResult {
    private Integer error;
    private String url;
    private String width;
    private String height;
    public Integer getError() {
        return error;
    }
    public void setError(Integer error) {
        this.error = error;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getWidth() {
        return width;
    }
    public void setWidth(String width) {
        this.width = width;
    }
    public String getHeigth() {
        return height;
    }
    public void setHeigth(String heigth) {
        this.height = heigth;
    }


}
