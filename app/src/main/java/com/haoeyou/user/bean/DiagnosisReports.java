package com.haoeyou.user.bean;

/**
 * Created by Mou on 2017/7/19.
 */

public class DiagnosisReports {
    private String diagnosis_time;
    private String doctor;
    private String file_id;
    private String report_name;
    private String upload_person;
    private String upload_time;

    public DiagnosisReports() {
    }

    public DiagnosisReports(String diagnosis_time, String doctor, String file_id, String report_name, String 
            upload_person, String upload_time) {
        this.diagnosis_time = diagnosis_time;
        this.doctor = doctor;
        this.file_id = file_id;
        this.report_name = report_name;
        this.upload_person = upload_person;
        this.upload_time = upload_time;
    }

    public String getDiagnosis_time() {
        return diagnosis_time;
    }

    public void setDiagnosis_time(String diagnosis_time) {
        this.diagnosis_time = diagnosis_time;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
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
