package com.haoeyou.user.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haoeyou.user.R;
import com.haoeyou.user.base.BaseActivity;
import com.haoeyou.user.common.Common;
import com.haoeyou.user.mvp.presenter.BasePresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditInfoActivity extends BaseActivity {


    @Bind(R.id.title_back)
    ImageView mTitleBack;
    @Bind(R.id.title_tv)
    TextView mTitleTv;
    @Bind(R.id.more)
    TextView mMore;
    @Bind(R.id.toolbar_layout)
    RelativeLayout mToolbarLayout;
    @Bind(R.id.info)
    EditText mInfo;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_info;
    }

    @Override
    protected void initInjector() {
//        onTintStatusBar();
        mMore.setText("完成");
        mTitleTv.setText("编辑");
        String content = "  "+getIntent().getExtras().get("PRE_INFO");
        mInfo.setText(content);
        mInfo.setSelection(content.length());
    }

    @Override
    protected void initEventAndData() {
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }


    @OnClick({R.id.title_back, R.id.more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                backToFillingActivity(0, "");
                break;
            case R.id.more:
                backToFillingActivity(1, mInfo.getText().toString().trim());
                break;
        }
    }


    private void backToFillingActivity(int resultCode, String info) {
        Intent itt = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("INFO", info);
        itt.putExtras(bundle);
        setResult(resultCode, itt);
        finish();
    }


}
