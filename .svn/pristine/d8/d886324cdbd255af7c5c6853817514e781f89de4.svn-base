package com.haoeyou.user.mvp.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.haoeyou.user.bean.CaseReports;
import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.mvp.model.ReportForBLModle;
import com.haoeyou.user.mvp.view.ReportForBLView;

import java.util.ArrayList;

/**
 * Created by Mou on 2017/7/18.
 */

public class ReportForBLPresenter extends BasePresenter<ReportForBLView> {
    ReportForBLModle mModle = new ReportForBLModle();

    public void getReport(Context mContext, String jsonBean) {
        mView.showLoadProgressDialog("");
        mModle.getReport(mContext, jsonBean, new MVPCallBack<ArrayList<CaseReports>>() {
            @Override
            public void succeed(ArrayList<CaseReports> mData) {
                if (mView != null) {
                    mView.getReportSuccess(mData);
                }
            }

            @Override
            public void failed(String message) {
                if (mView != null) {
                    if (!TextUtils.isEmpty(message))
                        mView.getReportFailed(message);
                }
            }
        });
    }
}
