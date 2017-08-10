package com.haoeyou.user.bean;

import java.io.Serializable;

/**
 * Created by Mou on 2017/7/10.
 */

public class ImageUrlResponseBean implements Serializable {
    private String errorCode;
    private String errorMsg;
    private String file_id;
    private String medical_history_id;
    private String patient_id;

    public ImageUrlResponseBean() {
    }

    public ImageUrlResponseBean(String errorCode, String errorMsg, String file_id, String medical_history_id, String 
            patient_id) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.file_id = file_id;
        this.medical_history_id = medical_history_id;
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

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public String getMedical_history_id() {
        return medical_history_id;
    }

    public void setMedical_history_id(String medical_history_id) {
        this.medical_history_id = medical_history_id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }
}
