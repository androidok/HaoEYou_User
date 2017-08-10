package com.haoeyou.user.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.haoeyou.user.R;
import com.haoeyou.user.bean.AddMedicalResponseBean;
import com.haoeyou.user.bean.MedicalList;
import com.haoeyou.user.common.Common;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/7.
 */
public class RecordHolder extends BaseHolder<MedicalList> {
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.date)
    TextView mDate;
    @Bind(R.id.see_detail)
    ImageView mSeeDetail;
    @Bind(R.id.pic_0)
    ImageView mPic0;
    @Bind(R.id.pic_1)
    ImageView mPic1;
    @Bind(R.id.pic_2)
    ImageView mPic2;
    @Bind(R.id.pic_3)
    ImageView mPic3;
    @Bind(R.id.image_layout)
    LinearLayout mImageLayout;
    private Context mContext;
    private ImageView[] mImageViews = {mPic0, mPic1, mPic2, mPic3};

    public RecordHolder(View view) {
        super(view);
        mContext = view.getContext();
    }

    @Override
    public void setData(MedicalList mData) {
        super.setData(mData);
        mTitle.setText(mData.getTitle());
        mDate.setText(mData.getUpload_time());
        for (int i = 0; i < (mData.getFile_id_list().size() >= 4 ? 4 : mData.getFile_id_list().size()); i++) {
            String path = Common.BASE_URL + "fileStorage/download?id=" + mData.getFile_id_list().get(i) + "&token=" +
                    Common.TOKEN;
            Glide.with(mContext).load(path).centerCrop().diskCacheStrategy(DiskCacheStrategy.RESULT).override(90, 90)
                    .into(mImageViews[i]);
            mImageViews[i].setVisibility(View.VISIBLE);
        }
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

    @OnClick({R.id.see_detail, R.id.image_layout})
    public void onViewClicked(View view) {
        listener.onItemClick(view, getPosition(), mData);
    }
}
