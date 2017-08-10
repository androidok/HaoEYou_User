package com.haoeyou.user.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.haoeyou.user.R;
import com.haoeyou.user.adapter.DataCompareFragmentAdapter;
import com.haoeyou.user.base.BaseActivity;
import com.haoeyou.user.common.Common;
import com.haoeyou.user.fragment.FinishFragment;
import com.haoeyou.user.fragment.UnFinishFragment;
import com.haoeyou.user.mvp.presenter.BasePresenter;
import com.haoeyou.user.utils.TabUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类名: {@link MyReservationActivity}
 * <br/> 功能描述: 我的预约界面内页,包含订单完成和未完成两个fragment
 * <br/> 作者: MouTao
 * <br/> 时间: 2017/7/28
 */
public class MyReservationActivity extends BaseActivity {
    private final static String TAG = "MyReservationActivity";
    @Bind(R.id.title_back)
    ImageView mTitleBack;
    @Bind(R.id.title_tv)
    TextView mTitleTv;
    @Bind(R.id.more)
    TextView mMoreTv;
    @Bind(R.id.image_more)
    ImageView mMore;
    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;
    @Bind(R.id.vp_tab_pager)
    ViewPager mVpTabPager;
    private Context mContext;
    private String[] tabTitles = new String[]{"未完成", "已完成"};
    private List<Fragment> fragmentList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_reservation;
    }

    @Override
    protected void initInjector() {
        mContext = this;
        initBaseUI();
        initTabLayout();
    }

    @Override
    protected void initEventAndData() {
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    private void initBaseUI() {
        mTitleTv.setText("我的预约");
        mMore.setImageResource(R.drawable.search);
        mTabLayout.post(new Runnable() {
            @Override
            public void run() {
                TabUtils.setIndicator(mTabLayout, 55, 50);
            }
        });
    }

    private void initTabLayout() {
        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new UnFinishFragment());
        fragmentList.add(new FinishFragment());
        // 把TabLayout和ViewPager关联起来
        DataCompareFragmentAdapter adapter = new DataCompareFragmentAdapter(getSupportFragmentManager(), tabTitles, 
                fragmentList);
        mVpTabPager.setAdapter(adapter);
        mVpTabPager.setOffscreenPageLimit(2);
        mTabLayout.setupWithViewPager(mVpTabPager);
    }

    /**
     * @param ct   上下文
     * @param from 从哪儿跳来的
     */
    public static void startAction(Context ct, String from) {
        Intent itt = new Intent(ct, MyReservationActivity.class);
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
