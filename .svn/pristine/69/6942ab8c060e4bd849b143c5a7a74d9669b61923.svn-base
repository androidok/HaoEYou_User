package com.haoeyou.user.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haoeyou.user.R;
import com.haoeyou.user.adapter.BaseRecyclerAdapter;
import com.haoeyou.user.adapter.RecordHolder;
import com.haoeyou.user.base.BaseActivity;
import com.haoeyou.user.bean.AllMedicalResponseBean;
import com.haoeyou.user.bean.BasicRequestBean;
import com.haoeyou.user.bean.MedicalList;
import com.haoeyou.user.common.Common;
import com.haoeyou.user.event.ItemListener;
import com.haoeyou.user.mvp.presenter.BasePresenter;
import com.haoeyou.user.mvp.presenter.HistoryPresenter;
import com.haoeyou.user.mvp.view.HistoryView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类名: {@link HistoryActivity}
 * <br/> 功能描述:基本资料界面
 * <br/> 作者: MouTao
 * <br/> 时间: 2017/5/25
 */
public class HistoryActivity extends BaseActivity implements HistoryView {
    public static final String TAG = "HistoryActivity";
    @Bind(R.id.title_back)
    ImageView mTitleBack;
    @Bind(R.id.title_tv)
    TextView mTitleTv;
    @Bind(R.id.more)
    TextView mMore;
    @Bind(R.id.toolbar_layout)
    RelativeLayout mToolbarLayout;
    @Bind(R.id.record_list)
    RecyclerView mRecordList;
    @Bind(R.id.add_record)
    Button mAddRecord;
    @Bind(R.id.nothing)
    LinearLayout mNothing;
    @Bind(R.id.image_more)
    ImageView mImageMore;
    private Context mContext;
    private BaseRecyclerAdapter mAdapter;
    private String patient_id;
    ArrayList<MedicalList> list = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_history;
    }

    @Override
    protected void initInjector() {
        mContext = this;
        ButterKnife.bind(this);
    }

    @Override
    protected void initEventAndData() {
        try {
            patient_id = getIntent().getExtras().getString("patient_id", "");
        } catch (Exception e) {
            patient_id = "";
        }
        mImageMore.setImageResource(R.drawable.icon_add);
        mImageMore.setVisibility(View.VISIBLE);
        mTitleTv.setText("过往病史");
        mNothing.setVisibility(View.GONE);
        if (TextUtils.isEmpty(patient_id))
            return;
        initRecycleView();
        LayzUpload();
    }

    private void LayzUpload() {
        if (TextUtils.isEmpty(patient_id))
            return;
        ((HistoryPresenter) mPresenter).getMedicalHistoryList(mContext, new BasicRequestBean(patient_id, Common.TOKEN));
    }

    public void initRecycleView() {
        LinearLayoutManager mgr = new LinearLayoutManager(mContext);
        mgr.setOrientation(LinearLayoutManager.VERTICAL);
        mRecordList.setLayoutManager(mgr);
        mRecordList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        setAdapter();
    }


    public void setAdapter() {
        if (mRecordList == null)
            return;
        if (mAdapter == null) {
            mAdapter = new BaseRecyclerAdapter(mContext, list, R.layout.adapter_record_item, RecordHolder.class, new 
                    ItemListener<MedicalList>() {
                @Override
                public void onItemClick(View view, int position, MedicalList mData) {
                    HistoryDetailActivity.startAction(mContext, patient_id, HistoryActivity.TAG, mData);
                }
            });
            mRecordList.setAdapter(mAdapter);
        }
    }

    @Override
    public BasePresenter getPresenter() {
        return new HistoryPresenter();
    }

    @OnClick({R.id.title_back, R.id.image_more, R.id.record_list, R.id.add_record, R.id.nothing})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.image_more:
            case R.id.add_record:
                AddRecordActivity.startAction(mContext, "", patient_id, "", HistoryActivity.TAG);
                break;
            case R.id.record_list:
                break;
            case R.id.nothing:
                break;
        }
    }


    @Override
    public void getMedicalHistoryListSuccesse(AllMedicalResponseBean mData) {
        if (mData.getList() == null || mData.getList().size() == 0) {
            mNothing.setVisibility(View.GONE);
            return;
        }
        mNothing.setVisibility(View.GONE);
        if (list.size() > 0)
            list.clear();
        mAdapter.setmDatas(mData.getList());
    }

    @Override
    public void showLoadProgressDialog(String str) {
        showLoading("");
    }

    @Override
    public void disDialog() {
        dissLoadDialog();
    }

    /**
     * @param ct   上下文
     * @param from 从哪儿跳来的
     */
    public static void startAction(Context ct, String patient_id, String from) {
        Intent itt = new Intent(ct, HistoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Common.FROM, from);
        bundle.putString("patient_id", patient_id);
        itt.putExtras(bundle);
        ct.startActivity(itt);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LayzUpload();
    }

}
