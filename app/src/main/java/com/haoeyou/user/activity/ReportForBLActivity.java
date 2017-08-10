package com.haoeyou.user.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.haoeyou.user.R;
import com.haoeyou.user.adapter.BaseRecyclerAdapter;
import com.haoeyou.user.adapter.CaseReportsHolder;
import com.haoeyou.user.base.BaseActivity;
import com.haoeyou.user.bean.BasicRequestBean;
import com.haoeyou.user.bean.CaseReports;
import com.haoeyou.user.common.Common;
import com.haoeyou.user.event.ItemListener;
import com.haoeyou.user.mvp.presenter.BasePresenter;
import com.haoeyou.user.mvp.presenter.ReportForBLPresenter;
import com.haoeyou.user.mvp.view.ReportForBLView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class ReportForBLActivity extends BaseActivity implements ReportForBLView {
    public final static String TAG = "ReportForBLActivity";
    @Bind(R.id.title_back)
    ImageView mTitleBack;
    @Bind(R.id.title_tv)
    TextView mTitleTv;
    @Bind(R.id.more)
    TextView mMore;
    @Bind(R.id.image_more)
    ImageView mImageMore;
    @Bind(R.id.toolbar_layout)
    RelativeLayout mToolbarLayout;
    @Bind(R.id.report_recycle)
    RecyclerView mReportRecycle;
    private Context mContext;
    private BaseRecyclerAdapter mReportAdapter;
    private String patient_id;
    private ArrayList<CaseReports> reportList = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_report;
    }

    @Override
    protected void initInjector() {
        mContext = this;
    }

    @Override
    public BasePresenter getPresenter() {
        return new ReportForBLPresenter();
    }

    @Override
    protected void initEventAndData() {
        initData();
        initRecyclerView();
        requestReport();
    }


    @OnClick({R.id.title_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
        }
    }

    private void initData() {
        try {
            patient_id = getIntent().getExtras().getString("patient_id", "");
        } catch (Exception e) {
            patient_id = "";
        }
        mTitleTv.setText("病历报告");
        if (TextUtils.isEmpty(patient_id))
            return;
    }

    private void requestReport() {
        String jsonBean = new Gson().toJson(new BasicRequestBean(patient_id, Common.TOKEN));
        ((ReportForBLPresenter) mPresenter).getReport(mContext, jsonBean);
    }


    private void initRecyclerView() {
        reportList.add(new CaseReports("1", "20166822糖尿病", "", ""));
        reportList.add(new CaseReports("1", "20166822艾滋病", "", ""));
        mReportRecycle.setItemAnimator(new DefaultItemAnimator());
        mReportRecycle.setLayoutManager(new LinearLayoutManager(this));
        if (mReportRecycle == null)
            return;
        if (mReportAdapter == null) {
            mReportAdapter = new BaseRecyclerAdapter(mContext, reportList, R.layout.adapter_child_report, 
                    CaseReportsHolder.class, new ItemListener<CaseReports>() {
                @Override
                public void onItemClick(View view, int position, CaseReports mData) {
                    PDFActivity.startAction(mContext, ReportForBLActivity.TAG);
                }
            });
            mReportRecycle.setAdapter(mReportAdapter);
        }
    }


    @Override
    public void showLoadProgressDialog(String str) {

    }

    @Override
    public void disDialog() {

    }

    @Override
    public void getReportSuccess(ArrayList<CaseReports> mData) {
        if (mData == null || mData.size() == 0)
            return;
        if (reportList.size() != 0)
            reportList.clear();
        reportList.addAll(mData);
        mReportAdapter.notifyDataSetChanged();

    }

    @Override
    public void getReportFailed(String message) {
        showBaseMessageDialog(message);
    }

    /**
     * @param ct   上下文
     * @param from 从哪儿跳来的
     */
    public static void startAction(Context ct, String patient_id, String from) {
        Intent itt = new Intent(ct, ReportForBLActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Common.FROM, from);
        bundle.putString("patient_id", patient_id);
        itt.putExtras(bundle);
        ct.startActivity(itt);
    }
}