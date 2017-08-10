package com.haoeyou.user.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haoeyou.user.R;
import com.haoeyou.user.bean.CaseReports;

import butterknife.Bind;


/**
 * Created by Administrator on 2017/3/7.
 */
public class CaseReportsHolder extends BaseHolder<CaseReports> {


    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.detail_date)
    TextView mTime;
    @Bind(R.id.status)
    ImageView mStatus;
    @Bind(R.id.itemView)
    RelativeLayout mItemView;

    public CaseReportsHolder(View view) {
      super(view);
    }

    @Override
    public void setData(CaseReports mData) {
        super.setData(mData);
        mTitle.setText(mData.getReport_name());
    }

    @Override
    public void init() {
        super.init();
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, getPosition(), mData);
            }
        });
    }
}
