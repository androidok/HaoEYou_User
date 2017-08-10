package com.haoeyou.user.bean;

import java.util.ArrayList;

/**
 * Created by Mou on 2017/7/18.
 */

public class BLResponseBean {
    private String errorCode;
    private String errorMsg;
    private ArrayList<CaseReports> list;
    private String patient_id;

    public BLResponseBean() {
    }

    public BLResponseBean(String errorCode, String errorMsg, ArrayList<CaseReports> list, String patient_id) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.list = list;
        this.patient_id = patient_id;
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

    public ArrayList<CaseReports> getList() {
        return list;
    }

    public void setList(ArrayList<CaseReports> list) {
        this.list = list;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }
}
