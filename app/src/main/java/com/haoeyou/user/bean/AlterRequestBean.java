package com.haoeyou.user.bean;

/**
 * Created by Mou on 2017/6/23.
 */

public class AlterRequestBean {
    private String headFileID;
    private String nickName;
    private String token;

    public AlterRequestBean() {
    }

    public AlterRequestBean(String headFileID, String nickName, String token) {
        this.headFileID = headFileID;
        this.nickName = nickName;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
