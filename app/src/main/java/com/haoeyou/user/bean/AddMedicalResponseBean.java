package com.haoeyou.user.bean;

import java.util.ArrayList;

/**
 * Created by Mou on 2017/7/7.
 */

public class AddMedicalResponseBean {
    private String errorCode;
    private String errorMsg;
    private String patient_id;
    private MedicalList record;

    public AddMedicalResponseBean() {

    }

    public AddMedicalResponseBean(String errorCode, String errorMsg, String patient_id, MedicalList record) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.patient_id = patient_id;
        this.record = record;
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

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public MedicalList getRecord() {
        return record;
    }

    public void setRecord(MedicalList record) {
        this.record = record;
    }
}
