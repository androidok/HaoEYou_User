package com.haoeyou.user.mvp.model;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.haoeyou.user.bean.ArchivesInfoResponseBean;
import com.haoeyou.user.bean.BasicResponseBean;
import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.network.DataCallback;
import com.haoeyou.user.network.NetWorking;

import okhttp3.Call;

/**
 * Created by Mou on 2017/6/9.
 */

public class FillArchivesModel implements FillArchivesModelImp {
    @Override
    public void createNewArchives(Context context, String jsonBean, final MVPCallBack<String> mBack) {
        NetWorking.requestNetData("userside/addHealthDoc", jsonBean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("userside/addHealthDoc", response);
                ArchivesInfoResponseBean responseBean = new Gson().fromJson(response, ArchivesInfoResponseBean.class);
                if ("1".equals(responseBean.getErrorCode())) {
                    mBack.succeed(responseBean.getErrorMsg());
                } else {
                    mBack.failed(responseBean.getErrorMsg());
                }
            }
        });

    }

    @Override
    public void changeArchives(Context context, String jsonBean, final MVPCallBack<String> mBack) {
        NetWorking.requestNetData("userside/alterHealthDoc", jsonBean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("alterHealthDoc", response);
                BasicResponseBean responseBean = new Gson().fromJson(response, BasicResponseBean.class);
                if ("1".equals(responseBean.getErrorCode())) {
                    mBack.succeed(responseBean.getErrorMsg());
                } else {
                    mBack.failed(responseBean.getErrorMsg());
                }
            }
        });
    }

    @Override
    public void getArchivesInfo(Context context, String jsonBean, final MVPCallBack<ArchivesInfoResponseBean> mBack) {
        NetWorking.requestNetData("userside/getHealthDoc", jsonBean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("getHealthDoc", response);
                ArchivesInfoResponseBean responseBean = new Gson().fromJson(response, ArchivesInfoResponseBean.class);
                if ("1".equals(responseBean.getErrorCode())) {
                    mBack.succeed(responseBean);
                } else {
                    mBack.failed(responseBean.getErrorMsg());
                }
            }
        });
    }
}
