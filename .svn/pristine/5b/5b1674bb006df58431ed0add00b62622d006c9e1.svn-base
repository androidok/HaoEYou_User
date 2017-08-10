package com.haoeyou.user.bean;

import com.haoeyou.user.entity.ChildEntity;

import java.util.ArrayList;

/**
 * 可展开收起的组数据的实体类 它比GroupEntity只是多了一个boolean类型的isExpand，用来表示展开和收起的状态。
 */
public class ReportGroupEntity {

    private String date;
    private String doctorName;
    private String reportName;
    private ArrayList<ReportChildEntity> childGroup;
    private boolean isExpand;

    public ReportGroupEntity() {
    }

    public ReportGroupEntity(String date, String doctorName, String reportName, boolean isExpand, 
                             ArrayList<ReportChildEntity> childGroup) {
        this.date = date;
        this.doctorName = doctorName;
        this.reportName = reportName;
        this.childGroup = childGroup;
        this.isExpand = isExpand;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public ArrayList<ReportChildEntity> getChildGroup() {
        return childGroup;
    }

    public void setChildGroup(ArrayList<ReportChildEntity> childGroup) {
        this.childGroup = childGroup;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }
}
