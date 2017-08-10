package com.haoeyou.user.mvp.presenter;

import android.content.Context;

import com.haoeyou.user.bean.AddMedicalResponseBean;
import com.haoeyou.user.bean.AllMedicalResponseBean;
import com.haoeyou.user.bean.BasicRequestBean;
import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.mvp.model.HistoryModel;
import com.haoeyou.user.mvp.view.HistoryView;

/**
 * Created by Mou on 2017/7/7.
 */

public class HistoryPresenter extends BasePresenter<HistoryView> {
    HistoryModel mModel = new HistoryModel();

    public void getMedicalHistoryList(Context mContext, BasicRequestBean bean) {
        mView.showLoadProgressDialog("");
        mModel.getMedicalHistoryList(mContext, bean, new MVPCallBack<AllMedicalResponseBean>() {
            @Override
            public void succeed(AllMedicalResponseBean mData) {
                if (mView != null) {
                    mView.disDialog();
                    mView.getMedicalHistoryListSuccesse(mData);
                }
            }

            @Override
            public void failed(String message) {
                if (mView != null) {
                    mView.disDialog();
                }
            }
        });
    }

}
