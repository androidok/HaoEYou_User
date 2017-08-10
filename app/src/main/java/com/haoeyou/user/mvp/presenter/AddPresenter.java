package com.haoeyou.user.mvp.presenter;

import android.content.Context;

import com.haoeyou.user.bean.AddMedicalBean;
import com.haoeyou.user.bean.ImageUrlResponseBean;
import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.mvp.model.AddModel;
import com.haoeyou.user.mvp.view.AddView;
import com.yalantis.ucrop.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mou on 2017/7/4.
 */

public class AddPresenter extends BasePresenter<AddView> {
    AddModel mModel = new AddModel();

    public void uploadFile(Context mcontext, AddMedicalBean bean, List<LocalMedia> selectMedia) {
        mView.showLoadProgressDialog("上传中...");

        mModel.uploadFile(mcontext, bean, selectMedia, new MVPCallBack<ArrayList<ImageUrlResponseBean>>() {
            @Override
            public void succeed(ArrayList<ImageUrlResponseBean> list) {
                if (mView != null) {
                    mView.disDialog();
                    mView.uploadFileFinish(list);
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

    public void addFile(String medical_history_id, AddMedicalBean bean, List<LocalMedia> selectMedia) {
        mView.showLoadProgressDialog("上传中...");

        mModel.addFile(bean, medical_history_id, selectMedia, new MVPCallBack<ArrayList<ImageUrlResponseBean>>() {
            @Override
            public void succeed(ArrayList<ImageUrlResponseBean> list) {
                if (mView != null) {
                    mView.disDialog();
                    mView.uploadFileFinish(list);
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
