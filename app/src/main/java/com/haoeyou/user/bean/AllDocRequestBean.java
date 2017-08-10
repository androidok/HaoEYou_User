package com.haoeyou.user.bean;

/**
 * Created by Mou on 2017/6/15.
 */

public class AllDocRequestBean {
    private String account;
    private String token;

    public AllDocRequestBean() {
    }

    public AllDocRequestBean(String token) {
        this.token = token;
    }
    public AllDocRequestBean(String account, String token) {
        this.account = account;
        this.token = token;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
