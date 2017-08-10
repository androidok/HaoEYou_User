package com.haoeyou.user.bean;

/**
 * Created by Mou on 2017/7/18.
 */

public class CaseReports {
    private String file_id;
    private String report_name;
    private String upload_person;
    private String upload_time;

    public CaseReports() {
    }

    public CaseReports(String file_id, String report_name, String upload_person, String upload_time) {
        this.file_id = file_id;
        this.report_name = report_name;
        this.upload_person = upload_person;
        this.upload_time = upload_time;
    }

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public String getReport_name() {
        return report_name;
    }

    public void setReport_name(String report_name) {
        this.report_name = report_name;
    }

    public String getUpload_person() {
        return upload_person;
    }

    public void setUpload_person(String upload_person) {
        this.upload_person = upload_person;
    }

    public String getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(String upload_time) {
        this.upload_time = upload_time;
    }
}
