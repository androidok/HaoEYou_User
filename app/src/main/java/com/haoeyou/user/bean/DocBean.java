package com.haoeyou.user.bean;

import java.util.ArrayList;

/**
 * Created by Mou on 2017/6/2.
 */

public class DocBean {
    private String docNumber;
    private String name;
    private String borthDay;
    private String tel;
    private String city;
    private String[] departmentList;

    public DocBean() {
    }

    public DocBean(String docNumber, String name, String borthDay, String tel, String city, String[] departmentList) {

        this.docNumber = docNumber;
        this.name = name;
        this.borthDay = borthDay;
        this.tel = tel;
        this.city = city;
        this.departmentList = departmentList;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBorthDay() {
        return borthDay;
    }

    public void setBorthDay(String borthDay) {
        this.borthDay = borthDay;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String[] getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(String[] departmentList) {
        this.departmentList = departmentList;
    }
}
