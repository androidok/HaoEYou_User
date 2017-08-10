package com.haoeyou.user.mvp.presenter;

import android.content.Context;
import android.icu.math.MathContext;

import com.haoeyou.user.bean.SocialDocResponseBean;
import com.haoeyou.user.bean.VersionResponseBean;
import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.mvp.model.MainModel;
import com.haoeyou.user.mvp.view.MainView;

/**
 * Created by Mou on 2017/6/26.
 */

public class MainPrasenter extends BasePresenter<MainView> {
    MainModel mModel = new MainModel();

    public void uploadFile(Context mContext, String jsonBean) {
        mModel.uploadFile(mContext, jsonBean, new MVPCallBack<Object>() {
            @Override
            public void succeed(Object mData) {
                if (mView == null)
                    return;
                mView.alterFileSuccess();
            }

            @Override
            public void failed(String message) {

            }
        });
    }

    public void alterNickName(Context mContext, String jsonBean) {
        mModel.alterNickName(mContext, jsonBean, new MVPCallBack<Object>() {
            @Override
            public void succeed(Object mData) {
                if (mView == null)
                    return;
                mView.alterNikeNameSuccess();
            }

            @Override
            public void failed(String message) {

            }
        });
    }

    public void checkAppVersion(Context context, String jsonBean) {
        mModel.checkAppVersion(context, jsonBean, new MVPCallBack<VersionResponseBean>() {
            @Override
            public void succeed(VersionResponseBean mData) {
                if (mView != null) {
                    mView.getVersionInfo(mData);
                }
            }

            @Override
            public void failed(String message) {
                if (mView != null) {

                }
            }
        });
    }

}
