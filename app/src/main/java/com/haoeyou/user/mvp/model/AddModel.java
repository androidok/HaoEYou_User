package com.haoeyou.user.mvp.model;

import android.content.Context;
import android.graphics.Bitmap;

import com.google.gson.Gson;
import com.haoeyou.user.bean.AddMedicalBean;
import com.haoeyou.user.bean.AddMedicalResponseBean;
import com.haoeyou.user.bean.ImageUrlResponseBean;
import com.haoeyou.user.bean.UploadRequestBean;
import com.haoeyou.user.bean.UploadResponseBean;
import com.haoeyou.user.common.Common;
import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.network.DataCallback;
import com.haoeyou.user.network.NetWorking;
import com.haoeyou.user.utils.ImageUtils;
import com.haoeyou.user.utils.SharedPreferencesHelper;
import com.yalantis.ucrop.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Mou on 2017/7/4.
 */

public class AddModel implements AddModelImp {
    private int sucessNum;
    private int fialedNum;
    private int TotalFile;
    private ArrayList<ImageUrlResponseBean> imageList = new ArrayList<>();

    @Override
    public void uploadFile(Context mContext, final AddMedicalBean bean, final List<LocalMedia> selectMedia, final 
    MVPCallBack<ArrayList<ImageUrlResponseBean>> mBack) {

        NetWorking.requestNetData("userside/addMedicalHistory", new Gson().toJson(bean), new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mBack.failed("");
            }

            @Override
            public void onResponse(String response, int id) {
                AddMedicalResponseBean beanResponse = new Gson().fromJson(response, AddMedicalResponseBean.class);
                if ("1".equals(beanResponse.getErrorCode())) {
                    addFile(bean, beanResponse.getRecord().getId(), selectMedia, mBack);
                } else {
                    mBack.failed("");
                }
            }
        });


    }

    public void addFile(AddMedicalBean bean, String medical_history_id, List<LocalMedia> selectMedia, final 
    MVPCallBack<ArrayList<ImageUrlResponseBean> > mBack) {
        TotalFile = selectMedia.size();
        for (int i = 0; i < selectMedia.size(); i++) {
            Bitmap bt = ImageUtils.getBitmapByPath(selectMedia.get(i).getPath());
            String btString = ImageUtils.bitmapToBaseString(bt);
            String jsonBean = new Gson().toJson(new UploadRequestBean(btString, selectMedia.get(i).getPath().replace
                    ("/data/data/com.haoeyou.user/cache/", "bingli_"), Common.TOKEN, medical_history_id, bean
                    .getTitle(), bean.getPatient_id()));
            NetWorking.requestNetData("userside/uploadMedicalHistoryImage", jsonBean, new DataCallback() {

                @Override
                public void onError(Call call, Exception e, int id) {
                    fialedNum++;
                    back(mBack);
                }

                @Override
                public void onResponse(String response, int id) {
                    sucessNum++;
                    ImageUrlResponseBean bean = new Gson().fromJson(response, ImageUrlResponseBean.class);
                    if ("1".equals(bean.getErrorCode())) {
                        imageList.add(bean);
                    }
                    back(mBack);
                }

            });
        }
    }

    private void back(MVPCallBack mBack) {
        if (fialedNum + sucessNum == TotalFile) {
            mBack.succeed(imageList);
        }
    }
}