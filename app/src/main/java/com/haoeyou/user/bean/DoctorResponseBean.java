package com.haoeyou.user.bean;

import java.util.ArrayList;

/**
 * Created by Mou on 2017/7/11.
 */

public class DoctorResponseBean {

    private String errorCode;
    private String errorMsg;
    private String total;
    private ArrayList<Doctors> doctors;

    public DoctorResponseBean() {
    }

    public DoctorResponseBean(String errorCode, String errorMsg, String total, ArrayList<Doctors> doctors) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.total = total;
        this.doctors = doctors;
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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public ArrayList<Doctors> getDoctors() {
        return doctors;
    }

    public void setDoctors(ArrayList<Doctors> doctors) {
        this.doctors = doctors;
    }
}
