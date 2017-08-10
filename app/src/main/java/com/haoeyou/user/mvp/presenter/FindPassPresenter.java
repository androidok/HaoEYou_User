package com.haoeyou.user.mvp.presenter;

import android.content.Context;

import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.mvp.model.FindPassModel;
import com.haoeyou.user.mvp.view.FillingView;
import com.haoeyou.user.mvp.view.FindPassView;

/**
 * 类名: {@link FindPassPresenter}
 * <br/> 功能描述: 找回密码解析层
 * <br/> 作者: MouTao
 * <br/> 时间: 2017/6/1
 */
public class FindPassPresenter extends BasePresenter<FindPassView> {
    FindPassModel mModel = new FindPassModel();

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

    public void resetPassword(Context mContext, String jsonBen) {
        mModel.resetPassword(mContext, jsonBen, new MVPCallBack() {
            @Override
            public void succeed(Object bean) {
                if (mView != null) {
                    mView.resetPassSuccess();
                }
            }

            @Override
            public void failed(String message) {
                if (mView != null) {
                    mView.resetPassFailed(message);
                }
            }
        });
    }
}
