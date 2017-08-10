package com.haoeyou.user.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.haoeyou.user.R;
import com.yalantis.ucrop.entity.LocalMedia;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/7.
 */
public class HistoryDetailHolder extends BaseHolder<LocalMedia> {
    private Context mContext;
    @Bind(R.id.image)
    ImageView mImage;

    public HistoryDetailHolder(View view) {
        super(view);
        mContext = view.getContext();
    }

    @Override
    public void setData(LocalMedia mData) {
        super.setData(mData);
        Glide.with(mContext).load(mData.getPath()).centerCrop().diskCacheStrategy(DiskCacheStrategy.RESULT).override
                (90, 90).into(mImage);
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
