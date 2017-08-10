package com.haoeyou.user.bean;

public class ServiceBean {
    private String name;
    private String open_htmlurl;
    private String open_iconurl;

    public ServiceBean() {
    }

    public ServiceBean(String name, String open_htmlurl, String open_iconurl) {
        this.name = name;
        this.open_htmlurl = open_htmlurl;
        this.open_iconurl = open_iconurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpen_htmlurl() {
        return open_htmlurl;
    }

    public void setOpen_htmlurl(String open_htmlurl) {
        this.open_htmlurl = open_htmlurl;
    }

    public String getOpen_iconurl() {
        return open_iconurl;
    }

    public void setOpen_iconurl(String open_iconurl) {
        this.open_iconurl = open_iconurl;
    }
}