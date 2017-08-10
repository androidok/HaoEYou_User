package com.haoeyou.user.bean;

import java.util.ArrayList;

/**
 * Created by Mou on 2017/7/11.
 */

public class DepartmentResponseBean {

    private String cn_name;
    private String en_name;

    public DepartmentResponseBean() {
    }

    public DepartmentResponseBean(String cn_name, String en_name) {
        this.cn_name = cn_name;
        this.en_name = en_name;
    }

    public String getCn_name() {
        return cn_name;
    }

    public void setCn_name(String cn_name) {
        this.cn_name = cn_name;
    }

    public String getEn_name() {
        return en_name;
    }

    public void setEn_name(String en_name) {
        this.en_name = en_name;
    }
}
