package com.haoeyou.user.bean;

import java.util.ArrayList;

/**
 * Created by Mou on 2017/7/11.
 */

public class TagNwesRequestBean {
    private String articleTag;
    private String page;
    private String pageSize;

    public TagNwesRequestBean() {
    }

    public TagNwesRequestBean(String articleTag, String page, String pageSize) {
        this.articleTag = articleTag;
        this.page = page;
        this.pageSize = pageSize;
    }

    public String getArticleTag() {
        return articleTag;
    }

    public void setArticleTag(String articleTag) {
        this.articleTag = articleTag;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

}
