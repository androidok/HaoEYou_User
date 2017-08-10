package com.haoeyou.user.bean;

import java.io.Serializable;

/**
 * Created by Mou on 2017/7/11.
 */

public class Doctors implements Serializable {
    private String cer_number;
    private String department;
    private String htmlurl;
    private String image_url;
    private String name;
    private String place;
    private String sex;
    private String rank;
    private String pesitionals;
    private String service_projects;
    private String special;

    public Doctors() {
    }

    public Doctors(String cer_number, String department, String htmlurl, String image_url, String name, String sex, 
                   String rank, String pesitionals, String place, String service_projects, String special) {
        this.cer_number = cer_number;
        this.department = department;
        this.htmlurl = htmlurl;
        this.image_url = image_url;
        this.name = name;
        this.sex = sex;
        this.rank = rank;
        this.pesitionals = pesitionals;
        this.place = place;
        this.service_projects = service_projects;
        this.special = special;
    }

    public String getCer_number() {
        return cer_number;
    }

    public void setCer_number(String cer_number) {
        this.cer_number = cer_number;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getHtmlurl() {
        return htmlurl;
    }

    public void setHtmlurl(String htmlurl) {
        this.htmlurl = htmlurl;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPesitionals() {
        return pesitionals;
    }

    public void setPesitionals(String pesitionals) {
        this.pesitionals = pesitionals;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getService_projects() {
        return service_projects;
    }

    public void setService_projects(String service_projects) {
        this.service_projects = service_projects;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }
}
