package com.haoeyou.user.mvp.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.haoeyou.user.bean.DiagnosisReports;
import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.mvp.model.ReportForHZModle;
import com.haoeyou.user.mvp.view.ReportForHZView;

import java.util.ArrayList;

/**
 * Created by Mou on 2017/7/18.
 */

public class ReportForHZPresenter extends BasePresenter<ReportForHZView> {
    ReportForHZModle mModle = new ReportForHZModle();

    public void getReport(Context mContext, String jsonBean) {
        mView.showLoadProgressDialog("");
        mModle.getReport(mContext, jsonBean, new MVPCallBack<ArrayList<DiagnosisReports>>() {
            @Override
            public void succeed(ArrayList<DiagnosisReports> mData) {
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
