package com.haoeyou.user.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.haoeyou.user.R;
import com.haoeyou.user.adapter.BaseRecyclerAdapter;
import com.haoeyou.user.adapter.CaseReportsHolder;
import com.haoeyou.user.base.BaseActivity;
import com.haoeyou.user.common.Common;
import com.haoeyou.user.event.ItemListener;
import com.haoeyou.user.mvp.presenter.BasePresenter;
import com.haoeyou.user.mvp.presenter.OrderDetailsPresenter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 类名: {@link SimpleDetailsActivity}
 * <br/> 功能描述:视频会诊\书面会诊详情界面
 * <br/> 作者: MouTao
 * <br/> 时间: 2017/6/22
 */
public class SimpleDetailsActivity extends BaseActivity {
    private final static String TAG = "ConsultationDetailsActivity";
    @Bind(R.id.title_back)
    ImageView mTitleBack;
    @Bind(R.id.title_tv)
    TextView mTitleTv;
    @Bind(R.id.orderNum)
    TextView mOrderNum;
    @Bind(R.id.icon1)
    ImageView mIcon1;
    @Bind(R.id.item1)
    TextView mItem1;
    @Bind(R.id.line2)
    View mLine2;
    @Bind(R.id.icon2)
    ImageView mIcon2;
    @Bind(R.id.item2)
    TextView mItem2;
    @Bind(R.id.line3)
    View mLine3;
    @Bind(R.id.icon3)
    ImageView mIcon3;
    @Bind(R.id.item3)
    TextView mItem3;
    @Bind(R.id.line4)
    View mLine4;
    @Bind(R.id.icon4)
    ImageView mIcon4;
    @Bind(R.id.item4)
    TextView mItem4;
    @Bind(R.id.orderTime)
    TextView mOrderTime;
    @Bind(R.id.name)
    TextView mName;
    @Bind(R.id.service_item)
    TextView mServiceItem;
    @Bind(R.id.service_need)
    TextView mNeedFW;
    @Bind(R.id.fw_layout)
    LinearLayout mFWLayout;
    @Bind(R.id.ratingBar)
    RatingBar mRatingBar;
    @Bind(R.id.evaluate_message)
    TextView mEvaluateMessage;
    @Bind(R.id.pj_layout)
    LinearLayout mPJLayout;
    @Bind(R.id.scroll)
    ScrollView mScroll;
    private Context mContext;
    private BaseRecyclerAdapter mAdapterHZ;
    private BaseRecyclerAdapter mAdapterBL;
    private ArrayList<String> list_HZ = new ArrayList<String>();
    private ArrayList<String> list_BL = new ArrayList<String>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_simple_details;
    }

    @Override
    protected void initInjector() {
        mContext = this;
        mTitleTv.setText("订单详情");
        mLine2.setBackgroundResource(R.drawable.shap_dotted_line);
    }

    @Override
    protected void initEventAndData() {
    }

    @Override
    public BasePresenter getPresenter() {
        return new OrderDetailsPresenter();
    }


    /**
     * @param ct   上下文
     * @param from 从哪儿跳来的
     */
    public static void startAction(Context ct, String from) {
        Intent itt = new Intent(ct, SimpleDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Common.FROM, from);
        itt.putExtras(bundle);
        ct.startActivity(itt);
    }

    @OnClick(R.id.title_back)
    public void onViewClicked() {
        finish();
    }
}
