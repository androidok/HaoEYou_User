package com.haoeyou.user.bean;

import java.io.Serializable;

/**
 * Created by Mou on 2017-08-04.
 */

public class OrderRequestBean implements Serializable {
    private String token;
    private String need;
    private String patient_id;
    private String service_project;

    public OrderRequestBean() {
    }

    public OrderRequestBean(String token, String need, String patient_id, String service_project) {
        this.token = token;
        this.need = need;
        this.patient_id = patient_id;
        this.service_project = service_project;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNeed() {
        return need;
    }

    public void setNeed(String need) {
        this.need = need;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getService_project() {
        return service_project;
    }

    public void setService_project(String service_project) {
        this.service_project = service_project;
    }
}
