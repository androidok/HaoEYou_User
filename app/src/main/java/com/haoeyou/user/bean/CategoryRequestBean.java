package com.haoeyou.user.bean;

import java.util.List;

/**
 * 类名: {@link CategoryRequestBean}
 * <br/> 功能描述:科室分类请求基类
 * <br/> 作者: MouTao
 * <br/> 时间: 2017/6/5
 */
public class CategoryRequestBean {
    /**
     * 用户token
     */
    private String token;
    /**
     * 筛选条件
     */
    private String filter;

    public CategoryRequestBean() {
        
    }

    public CategoryRequestBean(String token, String filter) {
        this.token = token;
        this.filter = filter;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
