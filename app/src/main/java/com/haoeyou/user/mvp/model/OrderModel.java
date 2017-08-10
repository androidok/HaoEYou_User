package com.haoeyou.user.mvp.model;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.haoeyou.user.bean.BasicResponseBean;
import com.haoeyou.user.bean.OrderBasicResponseBean;
import com.haoeyou.user.bean.OrdersResponseBean;
import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.network.DataCallback;
import com.haoeyou.user.network.NetWorking;
import com.haoeyou.user.utils.JsonFormat;
import com.haoeyou.user.utils.LogUtil;

import java.util.ArrayList;

import okhttp3.Call;

/**
 * Created by Mou on 2017-08-08.
 */

public class OrderModel {
    public void getOrderList(Context mContext, String jsonBean, final MVPCallBack<ArrayList<OrdersResponseBean>> 
            mBack) {
        NetWorking.requestNetData("userside/getOrders", jsonBean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mBack.failed("");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("获取未完成订单", JsonFormat.format(response));
                OrderBasicResponseBean bean = new Gson().fromJson(response, OrderBasicResponseBean.class);
                if ("1".equals(bean.getErrorCode())) {
                    mBack.succeed(bean.getOrders());
                } else {
                    mBack.failed(bean.getErrorMsg());
                }
            }
        });
    }

    public void cancleOrder(Context mContext, final String jsonBean, final MVPCallBack<Object> mBack) {
        NetWorking.requestNetData("userside/cancelOrder", jsonBean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mBack.failed("");
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtil.e("取消订单", JsonFormat.format(response));
                BasicResponseBean bean = new Gson().fromJson(response, BasicResponseBean.class);
                if ("1".equals(bean.getErrorCode())) {
                    mBack.succeed("");
                } else {
                    mBack.failed(bean.getErrorMsg());
                }

            }
        });
    }
}
