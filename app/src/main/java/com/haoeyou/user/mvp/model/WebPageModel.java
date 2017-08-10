package com.haoeyou.user.mvp.model;

import android.content.Context;

import com.google.gson.Gson;
import com.haoeyou.user.bean.HealthDocState;
import com.haoeyou.user.bean.HealthDocStateResponseBean;
import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.network.DataCallback;
import com.haoeyou.user.network.NetWorking;
import com.haoeyou.user.utils.JsonFormat;
import com.haoeyou.user.utils.LogUtil;

import java.util.ArrayList;

import okhttp3.Call;

/**
 * Created by Mou on 2017/6/20.
 */

public class WebPageModel {

    public void getVisitingNameList(Context mContext, String jsonBean, final MVPCallBack<ArrayList<HealthDocState>> 
            mBack) {
        NetWorking.requestNetData("userside/getHealthdDocStates", jsonBean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtil.e("获取档案状态", JsonFormat.format(response));
                HealthDocStateResponseBean bean = new Gson().fromJson(response, HealthDocStateResponseBean.class);
                if ("1".equals(bean.getErrorCode())) {
                      mBack.succeed(bean.getStateList());  
                } else {
                    mBack.failed(bean.getErrorCode());
                } 
            }
        });
    }
}
