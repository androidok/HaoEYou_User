package com.haoeyou.user.mvp.model;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.haoeyou.user.bean.BasicResponseBean;
import com.haoeyou.user.bean.DepartmentResponseBean;
import com.haoeyou.user.bean.DoctorResponseBean;
import com.haoeyou.user.bean.Doctors;
import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.network.DataCallback;
import com.haoeyou.user.network.NetWorking;
import com.haoeyou.user.utils.JsonFormat;
import com.haoeyou.user.utils.SharedPreferencesHelper;

import java.util.ArrayList;

import okhttp3.Call;

/**
 * Created by Mou on 2017/6/1.
 */

public class SecondModel {
    public void getDoctorsPage(final Context mContext, String jsonBean, final MVPCallBack<ArrayList<Doctors>> mBack) {
        NetWorking.requestNetData("doctor/getDoctorsPage", jsonBean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mBack.failed("");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("getDoctorsPage", JsonFormat.format(response));
                DoctorResponseBean bean = new Gson().fromJson(response, DoctorResponseBean.class);
                if ("1".equals(bean.getErrorCode())) {
                    mBack.succeed(bean.getDoctors());
                    SharedPreferencesHelper.putString(mContext, "DOCTOR_DATA", response);
                } else {
                    mBack.failed(bean.getErrorMsg());
                }
            }
        });
    }

    public void getStandardDepartments(Context mContext, final MVPCallBack<ArrayList<DepartmentResponseBean>> mBack) {
        NetWorking.requestNetData("doctor/getStandardDepartments", "{}", new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mBack.failed("");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("getStandardDepartments", JsonFormat.format(response));
                BasicResponseBean bean = new Gson().fromJson(response, BasicResponseBean.class);
                if ("1".equals(bean.getErrorCode())) {
                    mBack.succeed(bean.getDepartmentList());
                } else {
                    mBack.failed(bean.getErrorMsg());
                }
            }
        });
    }
}
