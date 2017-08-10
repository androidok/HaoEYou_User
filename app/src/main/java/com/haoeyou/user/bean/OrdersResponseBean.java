package com.haoeyou.user.bean;

/**
 * Created by Mou on 2017-08-09.
 */

public class OrdersResponseBean {
    private String cancelable;
    private String commentable;
    private String date;
    private String detail_url;
    private String icon_url;
    private String order_id;
    private String order_num;
    private String patient;
    private String service_project;

    public OrdersResponseBean() {
    }

    public OrdersResponseBean(String cancelable, String commentable, String date, String detail_url, String icon_url,
                              String order_id, String order_num, String patient, String service_project) {
        this.cancelable = cancelable;
        this.commentable = commentable;
        this.date = date;
        this.detail_url = detail_url;
        this.icon_url = icon_url;
        this.order_id = order_id;
        this.order_num = order_num;
        this.patient = patient;
        this.service_project = service_project;
    }

    public String getCancelable() {
        return cancelable;
    }

    public void setCancelable(String cancelable) {
        this.cancelable = cancelable;
    }

    public String getCommentable() {
        return commentable;
    }

    public void setCommentable(String commentable) {
        this.commentable = commentable;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetail_url() {
        return detail_url;
    }

    public void setDetail_url(String detail_url) {
        this.detail_url = detail_url;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getService_project() {
        return service_project;
    }

    public void setService_project(String service_project) {
        this.service_project = service_project;
    }
}
