package com.haoeyou.user.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.haoeyou.user.R;
import com.haoeyou.user.bean.Artical;
import com.haoeyou.user.common.Common;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/7.
 */
public class InfoListHolder extends BaseHolder<Artical> {
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.time)
    TextView mTime;
    @Bind(R.id.shortmessage)
    TextView mMessage;
    @Bind(R.id.pic)
    ImageView mPic;

    public InfoListHolder(View view) {
        super(view);
    }

    @Override
    public void setData(Artical mData) {
        super.setData(mData);
        mMessage.setText(mData.getTitle());
        mTime.setText(mData.getDate());
        Glide.with(mContext).load(Common.BASE_URL.replace("/api/", mData.getOpen_image_url())).diskCacheStrategy
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
