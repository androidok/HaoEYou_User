package com.haoeyou.user.bean;

/**
 * Created by Mou on 2017/7/11.
 */

public class TagTitleResponseBean {
    private String errorCode;
    private String errorMsg;
    private String[] tags;

    public TagTitleResponseBean() {
    }

    public TagTitleResponseBean(String errorCode, String errorMsg, String[] tags) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.tags = tags;
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

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }
}
