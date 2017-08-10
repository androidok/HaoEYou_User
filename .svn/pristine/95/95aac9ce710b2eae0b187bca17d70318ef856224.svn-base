package com.haoeyou.user.mvp.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.haoeyou.user.bean.AllDocResponseBean;
import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.mvp.model.FourthFragmentModel;
import com.haoeyou.user.mvp.view.BaseView;
import com.haoeyou.user.mvp.view.FourthFragmentView;

import java.util.ArrayList;

/**
 * Created by Mou on 2017/6/15.
 */

public class FourthFragmentPresenter extends BasePresenter<FourthFragmentView> {
    FourthFragmentModel mModel = new FourthFragmentModel();

    public void getAllDoc(Context mContext, String jsonBean) {
        mModel.getAllDoc(mContext, jsonBean, new MVPCallBack<AllDocResponseBean>() {
            @Override
            public void succeed(AllDocResponseBean mData) {
                if (mView != null) {
                    mView.getAllDocSuccess(mData);
                }
            }

            @Override
            public void failed(String message) {
                if (mView != null) {
                    if (!TextUtils.isEmpty(message))
                        mView.getAllDocFailed(message);
                }
            }
        });
    }
}
