package com.haoeyou.user.mvp.presenter;


import android.content.Context;
import android.util.Log;

import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.mvp.model.LoginModel;
import com.haoeyou.user.mvp.model.MainActivityModel;
import com.haoeyou.user.mvp.view.LoginActivityView;
import com.haoeyou.user.mvp.view.MainActivityView;

/**
 * 类名: {@link LoginPresenter}
 * <br/> 功能描述:{@link com.haoeyou.user.activity.LoginActivity}的解析层
 * <br/> 作者: MouTao
 * <br/> 时间: 2017/5/16
 * <br/> 最后修改者:
 * <br/> 最后修改内容:
 */
public class LoginPresenter extends BasePresenter<LoginActivityView> {
    LoginModel mModel = new LoginModel();

    public void login(Context mContext, String Account, String jsonBean) {
        mView.showLoadProgressDialog("");
        mModel.login(mContext, Account, jsonBean, new MVPCallBack<String>() {
            @Override
            public void succeed(String bean) {
                Log.e("Json", bean.toString());
                if (mView != null) {
                    mView.disDialog();
                    mView.loginSuccess();
                }
            }

            @Override
            public void failed(String message) {
                if (mView != null) {
                    mView.disDialog();
                    mView.loginFailed(message);
                }
            }
        });
    }
}