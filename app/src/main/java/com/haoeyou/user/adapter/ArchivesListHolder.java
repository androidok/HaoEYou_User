package com.haoeyou.user.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haoeyou.user.R;
import com.haoeyou.user.activity.FillArchivesActivity;
import com.haoeyou.user.activity.HistoryActivity;
import com.haoeyou.user.activity.ReportForBLActivity;
import com.haoeyou.user.activity.ReportForHZActivity;
import com.haoeyou.user.bean.DocListBean;
import com.haoeyou.user.common.Common;
import com.haoeyou.user.fragment.FourthFragment;
import com.haoeyou.user.utils.ButtonTools;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * Created by Administrator on 2017/3/7.
 */
public class ArchivesListHolder extends BaseHolder<DocListBean> {

    @Bind(R.id.name)
    TextView mName;
    @Bind(R.id.health)
    TextView mHealth;
    @Bind(R.id.history)
    TextView mHistory;
    @Bind(R.id.bl_red)
    View mBlRed;
    @Bind(R.id.bl_report)
    LinearLayout mBlReport;
    @Bind(R.id.hz_red)
    View mHzRed;
    @Bind(R.id.hz_report)
    LinearLayout mHzReport;

    public ArchivesListHolder(View view) {
        super(view);
    }

    @Override
    public void setData(DocListBean mData) {
        super.setData(mData);
        mName.setText(mData.getName());
    }

    @Override
    public void init() {
        super.init();
    }

    @OnClick({R.id.health, R.id.history, R.id.bl_report, R.id.hz_report})
    public void onViewClicked(View view) {
        ButtonTools.disabledView(view, 1);
        switch (view.getId()) {
            case R.id.health:
                //                FillArchivesActivity.startAction(mContext, FourthFragment.TAG, mData.getPatient_id
                // (), "", 
                //                        FillArchivesActivity.CHANGE);
                listener.onItemClick(view, getPosition(), mData);
                break;
            case R.id.history:
                HistoryActivity.startAction(mContext, mData.getPatient_id(), FourthFragment.TAG);
                break;
            case R.id.bl_report:
                ReportForBLActivity.startAction(mContext, mData.getPatient_id(), "bl");
                break;
            case R.id.hz_report:
                ReportForHZActivity.startAction(mContext, mData.getPatient_id(), "hz");
                break;
        }

    }


}
