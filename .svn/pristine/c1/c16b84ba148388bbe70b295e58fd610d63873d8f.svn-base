package com.haoeyou.user.mvp.model;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.haoeyou.user.bean.BasicRequestBean;
import com.haoeyou.user.bean.HomeDataResponse;
import com.haoeyou.user.bean.WaiterResponseBean;
import com.haoeyou.user.cache.UserCacheManager;
import com.haoeyou.user.common.Common;
import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.network.DataCallback;
import com.haoeyou.user.network.NetWorking;
import com.haoeyou.user.utils.JsonFormat;
import com.haoeyou.user.utils.LogUtil;
import com.haoeyou.user.utils.SharedPreferencesHelper;

import okhttp3.Call;

public class MainFragmentModel {

    public void getHomeData(final Context mContext, final MVPCallBack<HomeDataResponse> mBack) {
        NetWorking.requestNetData("opendata/getApphome", "", new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mBack.failed("");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("homeData", JsonFormat.format(response));
                HomeDataResponse bean = new Gson().fromJson(response, HomeDataResponse.class);
                if ("1".equals(bean.getErrorCode())) {
                    SharedPreferencesHelper.putString(mContext, "HOME_DATA", response);
                    mBack.succeed(bean);
                } else {
                    mBack.failed(bean.getErrorMsg());
                }
            }
        });
    }

    public void getMyWaiter(Context mContext, final MVPCallBack<WaiterResponseBean> mBack) {
        String jsonBean = new Gson().toJson(new BasicRequestBean(Common.TOKEN));
        NetWorking.requestNetData("chat/getMyWaiter", jsonBean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mBack.failed("");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("getMyWaiter", JsonFormat.format(response));
                WaiterResponseBean bean = new Gson().fromJson(response, WaiterResponseBean.class);
                if ("1".equals(bean.getErrorCode())) {
                    UserCacheManager.save(bean.getWaiter_account(), bean.getWaiter_nickname(), Common.BASE_URL + 
                            "fileStorage/download?id=" + bean.getWaiter_headfileid() + "&token=" + Common.TOKEN);
                    mBack.succeed(bean);
                } else {
                    mBack.failed(bean.getErrorMsg());
                }
            }
        });
    }
}