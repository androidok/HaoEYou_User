package com.haoeyou.user.mvp.model;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.haoeyou.user.bean.CategoryResponseBean;
import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.network.DataCallback;
import com.haoeyou.user.network.NetWorking;
import com.zhy.http.okhttp.utils.L;

import okhttp3.Call;

/**
 * Created by Mou on 2017/5/24.
 */

public class CareDepartmentModel implements CareDepartmentModelImp {
    @Override
    public void getCategoryList(Context mContext, String jsonBean, final MVPCallBack<CategoryResponseBean> mBack) {
        NetWorking.requestNetData("userside/getDepartmentCategory", jsonBean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("getDepartmentCategory", response);
                CategoryResponseBean bean = new Gson().fromJson(response, CategoryResponseBean.class);
                if ("1".equals(bean.getErrorCode())) {
                    mBack.succeed(bean);
                } else {
                    mBack.failed(bean.getErrorMsg());
                }
            }
        });
    }
}
