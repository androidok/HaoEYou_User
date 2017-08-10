package com.haoeyou.user.mvp.presenter;

import android.content.Context;

import com.haoeyou.user.bean.HealthDocState;
import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.mvp.model.WebPageModel;
import com.haoeyou.user.mvp.view.WebPageView;

import java.util.ArrayList;

/**
 * Created by Mou on 2017/6/20.
 */

public class WebPagePresenter extends BasePresenter<WebPageView> {
    WebPageModel mModel = new WebPageModel();

    public void getNameList(Context mContext, String jsonBean) {
        mView.showLoadProgressDialog("");
        mModel.getVisitingNameList(mContext, jsonBean, new MVPCallBack<ArrayList<HealthDocState>>() {
            @Override
            public void succeed(ArrayList<HealthDocState> mData) {
                if (mView != null) {
                    mView.disDialog();
                    mView.getDocListSucceed(mData);
                }
            }

            @Override
            public void failed(String message) {
                if (mView != null) {
                    mView.disDialog();
                    mView.getDocListSucceed(new ArrayList<HealthDocState>());
                }
            }
        });
    }
}
