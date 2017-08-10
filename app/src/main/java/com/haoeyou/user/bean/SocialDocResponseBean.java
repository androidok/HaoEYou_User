package com.haoeyou.user.bean;

/**
 * Created by Mou on 2017/6/23.
 */

public class SocialDocResponseBean {
    private String easymobID = "";
    private String errorCode = "";
    private String errorMsg = "";
    private String headFileID = "";
    private String nickName = "";

    public SocialDocResponseBean() {
    }

    public String getEasymobID() {
        return easymobID;
    }

    public void setEasymobID(String easymobID) {
        this.easymobID = easymobID;
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

    public String getHeadFileID() {
        return headFileID;
    }

    public void setHeadFileID(String headFileID) {
        this.headFileID = headFileID;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
