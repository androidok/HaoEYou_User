package com.haoeyou.user.mvp.presenter;

import android.content.Context;

import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.mvp.model.RegisterModel;
import com.haoeyou.user.mvp.view.RegisterView;

/**
 * Created by Mou on 2017/5/31.
 */

public class RegisterPresenter extends BasePresenter<RegisterView> {
    RegisterModel mModel = new RegisterModel();

    public void getVerificationCode(Context mContext, String tel) {
        mModel.getVerificationCode(mContext, tel, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {

            }

            @Override
            public void failed(String message) {

            }
        });
    }

    public void registerAccount(Context mContext, String registerBean) {
        mModel.registerAccount(mContext, registerBean, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                if (mView != null) {
                    mView.createAccountSuccess();
                }
            }

            @Override
            public void failed(String message) {
                if (mView != null) {
                    mView.createAccountFailed(message);
                }
            }
        });
    }
}
