package com.haoeyou.user.mvp.model;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.haoeyou.user.bean.AllDocResponseBean;
import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.network.DataCallback;
import com.haoeyou.user.network.NetWorking;

import java.util.ArrayList;

import okhttp3.Call;

/**
 * Created by Mou on 2017/6/15.
 */

public class FourthFragmentModel implements FourthFragmentModelImp {
    @Override
    public void getAllDoc(Context mContext, String JsonBean, final MVPCallBack<AllDocResponseBean> mBack) {
        NetWorking.requestNetData("userside/getPatients", JsonBean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mBack.failed("");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("getPatients", response);
                AllDocResponseBean bean = new Gson().fromJson(response, AllDocResponseBean.class);
                if ("1".equals(bean.getErrorCode())) {
                    mBack.succeed(bean);
                } else {
                    mBack.failed(bean.getErrorMsg());
                }
            }
        });
    }
}
