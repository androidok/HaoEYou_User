package com.haoeyou.user.bean;

/**
 * 类名: {@link RegisterBean}
 * <br/> 功能描述: 注册基类
 * <br/> 作者: MouTao
 * <br/> 时间: 2017/5/31
 */
public class RegisterBean {
    /**
     * 电话号码
     */
    private String tel;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 密码
     */
    private String password;
    /**
     * 验证码
     */
    private String smsCode;

    public RegisterBean() {
        
    }

    public RegisterBean(String tel, String nickName, String password, String smsCode) {
        this.tel = tel;
        this.nickName = nickName;
        this.password = password;
        this.smsCode = smsCode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
}
