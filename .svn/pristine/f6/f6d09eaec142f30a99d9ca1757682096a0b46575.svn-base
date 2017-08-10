package com.haoeyou.user.mvp.model;

import android.content.Context;

import com.google.gson.Gson;
import com.haoeyou.user.bean.BLResponseBean;
import com.haoeyou.user.bean.CaseReports;
import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.network.DataCallback;
import com.haoeyou.user.network.NetWorking;
import com.haoeyou.user.utils.JsonFormat;
import com.haoeyou.user.utils.LogUtil;

import java.util.ArrayList;

import okhttp3.Call;

/**
 * Created by Mou on 2017/7/18.
 */

public class ReportForBLModle {

    public void getReport(Context mContext, String jsonBean, final MVPCallBack<ArrayList<CaseReports>> mBack) {
        NetWorking.requestNetData("userside/getCaseReports", jsonBean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mBack.failed("");
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtil.e("获取病历报告", JsonFormat.format(response));
                BLResponseBean bean = new Gson().fromJson(response, BLResponseBean.class);
                if ("1".equals(bean.getErrorCode())) {
                    mBack.succeed(bean.getList());
                } else {
                    mBack.failed(bean.getErrorMsg());
                }
            }
        });
    }
}
