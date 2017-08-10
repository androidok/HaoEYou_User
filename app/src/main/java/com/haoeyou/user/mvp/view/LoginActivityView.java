package com.haoeyou.user.mvp.view;

/**
 * 类名: {@link LoginActivityView}
 * <br/> 功能描述: {@link com.haoeyou.user.activity.LoginActivity}的View层监听
 * <br/> 作者: MouTao
 * <br/> 时间: 2017/5/16
 * <br/> 最后修改者:
 * <br/> 最后修改内容:
 */
public interface LoginActivityView extends BaseView {

    void loginSuccess();

    void loginFailed(String message);
}
