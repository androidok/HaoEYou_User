package com.haoeyou.user.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.haoeyou.user.R;
import com.haoeyou.user.activity.EvaluateActivity;
import com.haoeyou.user.bean.OrdersResponseBean;
import com.haoeyou.user.common.Common;
import com.haoeyou.user.fragment.FinishFragment;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/7.
 */
public class FinishHolder extends BaseHolder<OrdersResponseBean> {


    @Bind(R.id.pic)
    ImageView mPic;
    @Bind(R.id.name)
    TextView mName;
    @Bind(R.id.time)
    TextView mTime;
    @Bind(R.id.project)
    TextView mProject;
    @Bind(R.id.order_num)
    TextView mOrderNum;
    @Bind(R.id.evaluate_tv)
    TextView mEvaluateTv;

    public FinishHolder(View view) {
        super(view);
    }

    @Override

    public void setData(OrdersResponseBean mData) {
        super.setData(mData);
        Glide.with(mContext).load(Common.BASE_URL.replace("/api/", mData.getIcon_url())).diskCacheStrategy
                (DiskCacheStrategy.RESULT).into(mPic);
        mName.setText(mData.getPatient());
        mTime.setText(mData.getDate());
        mProject.setText(mData.getService_project());
        mOrderNum.setText(mData.getOrder_id());
        mEvaluateTv.setVisibility("1".equals(mData.getCommentable()) ? View.VISIBLE : View.GONE);
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

    @OnClick(R.id.evaluate_tv)
    public void onViewClicked(View v) {
        listener.onItemClick(v, getPosition(), mData);
    }
}
