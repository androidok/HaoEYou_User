package com.haoeyou.user.mvp.model;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.haoeyou.user.bean.AddMedicalResponseBean;
import com.haoeyou.user.bean.AllMedicalResponseBean;
import com.haoeyou.user.bean.BasicRequestBean;
import com.haoeyou.user.bean.MedicalList;
import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.network.DataCallback;
import com.haoeyou.user.network.NetWorking;

import okhttp3.Call;

/**
 * Created by Mou on 2017/7/7.
 */

public class HistoryModel implements HistoryModelImp {

    @Override
    public void getMedicalHistoryList(Context mContext, BasicRequestBean bean, final 
    MVPCallBack<AllMedicalResponseBean> mBack) {
        String jsonBean = new Gson().toJson(bean);
        NetWorking.requestNetData("userside/getMedicalHistoryList", jsonBean.replace("targetAccount", "patient_id"), 
                new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mBack.failed("");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("获取全部病历报告", response);
                AllMedicalResponseBean bean = new Gson().fromJson(response,AllMedicalResponseBean.class);
                if ("1".equals(bean.getErrorCode())) {
                    mBack.succeed(bean);
                } else {
                    mBack.failed("");
                } 

            }
        });
    }
}
