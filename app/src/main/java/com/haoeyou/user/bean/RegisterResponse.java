package com.haoeyou.user.bean;

/**
 * 类名: {@link RegisterResponse}
 * <br/> 功能描述:注册响应基类
 * <br/> 作者: MouTao
 * <br/> 时间: 2017/5/31
 */
public class RegisterResponse{
    private String account;
    private String errorCode;
    private String errorMsg;

    public RegisterResponse() {
    }

    public RegisterResponse(String account, String errorCode, String errorMsg) {
        this.account = account;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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
}
