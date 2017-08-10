package com.haoeyou.user.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.haoeyou.user.R;
import com.haoeyou.user.adapter.BaseRecyclerAdapter;
import com.haoeyou.user.adapter.HistoryDetailHolder;
import com.haoeyou.user.base.BaseActivity;
import com.haoeyou.user.bean.AllMedicalResponseBean;
import com.haoeyou.user.bean.BasicRequestBean;
import com.haoeyou.user.bean.ImageUrlResponseBean;
import com.haoeyou.user.bean.MedicalList;
import com.haoeyou.user.common.Common;
import com.haoeyou.user.event.ItemListener;
import com.haoeyou.user.mvp.presenter.BasePresenter;
import com.haoeyou.user.network.DataCallback;
import com.haoeyou.user.network.NetWorking;
import com.luck.picture.lib.model.PictureConfig;
import com.yalantis.ucrop.entity.LocalMedia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class HistoryDetailActivity extends BaseActivity implements ItemListener {
    public final static String TAG = "HistoryDetailActivity";
    @Bind(R.id.pic_list)
    RecyclerView mPicList;
    @Bind(R.id.title_back)
    ImageView mTitleBack;
    @Bind(R.id.title_tv)
    TextView mTitleTv;
    @Bind(R.id.more)
    TextView mMore;
    @Bind(R.id.toolbar_layout)
    RelativeLayout mToolbarLayout;
    private Context mContext;
    MedicalList mData;
    String patient_id;
    List<LocalMedia> selectMedia = new ArrayList<>();
    @Bind(R.id.image_more)
    ImageView mImageMore;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_history_detail;
    }

    @Override
    protected void initInjector() {
        mContext = this;
    }

    @Override
    protected void initEventAndData() {
        mImageMore.setImageResource(R.drawable.icon_add);
        mImageMore.setVisibility(View.VISIBLE);
        getIntentData();
        initRecyclerView();
        setAdapter();
    }

    private void getIntentData() {
        try {
            mData = (MedicalList) getIntent().getExtras().getSerializable("MEDICAL_LIST");
            patient_id = getIntent().getExtras().getString("patient_id", "");
        } catch (Exception e) {
            return;
        }
        if (mData == null || TextUtils.isEmpty(patient_id))
            return;
        mTitleTv.setText(mData.getTitle());
        for (int i = 0; i < mData.getFile_id_list().size(); i++) {
            String path = Common.BASE_URL + "fileStorage/download?id=" + mData.getFile_id_list().get(i) + "&token=" +
                    Common.TOKEN;
            selectMedia.add(new LocalMedia(path, 1, 1, false, 1, 1, 0));
        }
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    private void initRecyclerView() {
        GridLayoutManager mgr = new GridLayoutManager(mContext, 4);
        mPicList.setLayoutManager(mgr);
        mPicList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
                outRect.set(10, 10, 10, 20);
            }
        });

    }

    BaseRecyclerAdapter mAdapter;

    public void setAdapter() {
        if (mPicList == null)
            return;
        if (mAdapter == null) {
            mAdapter = new BaseRecyclerAdapter(mContext, selectMedia, R.layout.adapter_history_detail_item, 
                    HistoryDetailHolder.class, this);
            mPicList.setAdapter(mAdapter);
        }
    }


    @Override
    public void onItemClick(View view, int position, Object mData) {
        PictureConfig.getInstance().externalPicturePreview((Activity) mContext, position, selectMedia);
    }

    /**
     * @param ct   上下文
     * @param from 从哪儿跳来的
     */
    public static void startAction(Context ct, String patient_id, String from, MedicalList mData) {
        Intent intent = new Intent(ct, HistoryDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("MEDICAL_LIST", mData);
        bundle.putString("patient_id", patient_id);
        bundle.putString(Common.FROM, from);
        intent.putExtras(bundle);
        ct.startActivity(intent);
    }


    @OnClick({R.id.title_back, R.id.image_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.image_more:
                addFile();
                break;
        }
    }

    private void addFile() {
        if (TextUtils.isEmpty(patient_id))
            return;
        Intent itt = new Intent(mContext, AddRecordActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Common.FROM, HistoryDetailActivity.TAG);
        bundle.putString("patient_id", patient_id);
        bundle.putString("medical_history_id", mData.getId());
        bundle.putString("oldMessage", mData.getTitle());
        itt.putExtras(bundle);
        startActivityForResult(itt, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (100 == requestCode && 1 == resultCode) {
            ArrayList<ImageUrlResponseBean> imageList = (ArrayList<ImageUrlResponseBean>) data.getExtras()
                    .getSerializable("IMAGE_LIST");
            if (imageList == null)
                return;
            for (ImageUrlResponseBean bean : imageList) {
                String path = Common.BASE_URL + "fileStorage/download?id=" + bean.getFile_id() + "&token=" + Common
                        .TOKEN;
                selectMedia.add(new LocalMedia(path, 1, 1, false, 1, 1, 0));
            }
            mAdapter.notifyDataSetChanged();
        }
    }
}