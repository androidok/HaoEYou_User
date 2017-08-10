package com.haoeyou.user.mvp.presenter;

import android.content.Context;

import com.haoeyou.user.bean.ArchivesInfoResponseBean;
import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.mvp.model.FillArchivesModel;
import com.haoeyou.user.mvp.model.FillArchivesModelImp;
import com.haoeyou.user.mvp.view.FillArchivesView;

/**
 * Created by Mou on 2017/6/9.
 */

public class FillArchivesPresenter extends BasePresenter<FillArchivesView> {
    FillArchivesModel mModel = new FillArchivesModel();

    public void createNewArchives(Context context, String jsonBean) {
        mModel.createNewArchives(context, jsonBean, new MVPCallBack<String>() {
            @Override
            public void succeed(String mData) {
                if (mView != null) {
                    mView.addSuccess();
                }
            }

            @Override
            public void failed(String message) {
                if (mView != null) {
                    mView.changeFailed(message);
                }
            }
        });
    }

    public void changeArchives(Context context, String jsonBean) {
        mModel.changeArchives(context, jsonBean, new MVPCallBack<String>() {
            @Override
            public void succeed(String mData) {
                if (mView != null) {
                    mView.changeSuccess();
                }
            }

            @Override
            public void failed(String message) {
                if (mView != null) {
                    mView.changeFailed(message);
                }
            }
        });
    }

    public void getArchivesInfo(Context context, String jsonBean) {
        mModel.getArchivesInfo(context, jsonBean, new MVPCallBack<ArchivesInfoResponseBean>() {
            @Override
            public void succeed(ArchivesInfoResponseBean mData) {
                if (mView != null) {
                    mView.setInfo(mData);
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
