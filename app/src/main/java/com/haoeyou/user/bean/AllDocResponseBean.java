package com.haoeyou.user.bean;

import java.util.ArrayList;

/**
 * Created by Mou on 2017/6/15.
 */

public class AllDocResponseBean {
    private String errorCode;
    private String errorMsg;
    private ArrayList<DocListBean> patient_list;

    public AllDocResponseBean() {
    }

    public AllDocResponseBean(String errorCode, String errorMsg, ArrayList<DocListBean> docList) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.patient_list = docList;
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

    public ArrayList<DocListBean> getPatient_list() {
        return patient_list;
    }

    public void setPatient_list(ArrayList<DocListBean> patient_list) {
        this.patient_list = patient_list;
    }
}
