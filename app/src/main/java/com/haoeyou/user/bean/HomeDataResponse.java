package com.haoeyou.user.bean;

import java.util.ArrayList;

/**
 * Created by Mou on 2017/7/10.
 */

public class HomeDataResponse {
    private String errorCode;
    private String errorMsg;
    private ArrayList<Artical> article_list;
    private ArrayList<Banner> banner_list;
    private ArrayList<DoctorBanner> doctor_list;
    private ArrayList<ServiceBean> service_list;

    public HomeDataResponse() {
    }

    public HomeDataResponse(String errorCode, String errorMsg, ArrayList<Artical> article_list, ArrayList<Banner> 
            banner_list, ArrayList<DoctorBanner> doctor_list, ArrayList<ServiceBean> service_list) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.article_list = article_list;
        this.banner_list = banner_list;
        this.doctor_list = doctor_list;
        this.service_list = service_list;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public ArrayList<Artical> getArticle_list() {
        return article_list;
    }

    public void setArticle_list(ArrayList<Artical> article_list) {
        this.article_list = article_list;
    }

    public ArrayList<Banner> getBanner_list() {
        return banner_list;
    }

    public void setBanner_list(ArrayList<Banner> banner_list) {
        this.banner_list = banner_list;
    }

    public ArrayList<DoctorBanner> getDoctor_list() {
        return doctor_list;
    }

    public void setDoctor_list(ArrayList<DoctorBanner> doctor_list) {
        this.doctor_list = doctor_list;
    }

    public ArrayList<ServiceBean> getService_list() {
        return service_list;
    }

    public void setService_list(ArrayList<ServiceBean> service_list) {
        this.service_list = service_list;
    }
}
