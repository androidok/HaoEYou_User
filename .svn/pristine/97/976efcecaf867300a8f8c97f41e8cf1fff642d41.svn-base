package com.haoeyou.user.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.util.Util;
import com.haoeyou.user.R;
import com.haoeyou.user.bean.Doctors;
import com.haoeyou.user.common.Common;
import com.haoeyou.user.common.MyApplication;

import javax.security.auth.Destroyable;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/7.
 */
public class DoctorListHolder extends BaseHolder<Doctors> {
    @Bind(R.id.pic)
    ImageView mPic;
    @Bind(R.id.department)
    TextView mDepartment;
    @Bind(R.id.name)
    TextView mName;
    @Bind(R.id.degree)
    TextView mDegree;
    @Bind(R.id.area)
    TextView mArea;

    public DoctorListHolder(View view) {
        super(view);
    }

    @Override
    public void setData(Doctors mData) {
        mName.setText(mData.getName());
        mArea.setText(mData.getPlace());
        mDepartment.setText(mData.getDepartment());
        mDegree.setText(mData.getRank());
        Glide.with(mContext).load(Common.BASE_URL.replace("/api/", mData.getImage_url())).diskCacheStrategy
                (DiskCacheStrategy.RESULT).into(mPic);
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
