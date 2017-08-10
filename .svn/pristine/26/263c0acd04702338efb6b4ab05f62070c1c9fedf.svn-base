package com.haoeyou.user.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.haoeyou.user.R;
import com.haoeyou.user.bean.ServiceBean;
import com.haoeyou.user.common.Common;
import com.haoeyou.user.utils.ScreenUtil;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/7.
 */
public class ServiceListHolder extends BaseHolder<ServiceBean> {
    @Bind(R.id.icon)
    ImageView mIcon;
    @Bind(R.id.title)
    TextView mTitle;

    public ServiceListHolder(View view) {
        super(view);
    }

    @Override
    public void setData(ServiceBean mData) {
        super.setData(mData);
        ViewGroup.LayoutParams lp;
        lp = mView.getLayoutParams();
        lp.width = ScreenUtil.getScreenWidth(mContext) / 4;
        mView.setLayoutParams(lp);
        mTitle.setText(mData.getName());
        Glide.with(mContext).load(Common.BASE_URL.replace("/api/", mData.getOpen_iconurl())).diskCacheStrategy
                (DiskCacheStrategy.RESULT).into(mIcon);
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
