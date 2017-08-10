package com.haoeyou.user.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.haoeyou.user.R;
import com.haoeyou.user.base.BaseActivity;
import com.haoeyou.user.bean.BasicResponseBean;
import com.haoeyou.user.bean.EvaluateRequestBean;
import com.haoeyou.user.common.Common;
import com.haoeyou.user.mvp.presenter.BasePresenter;
import com.haoeyou.user.network.DataCallback;
import com.haoeyou.user.network.NetWorking;
import com.haoeyou.user.utils.ButtonTools;
import com.haoeyou.user.widget.CircleImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;
import okhttp3.Call;

/**
 * 类名: {@link EvaluateActivity}
 * <br/> 功能描述:评价界面
 * <br/> 作者: MouTao
 * <br/> 时间: 2017/6/21
 */
public class EvaluateActivity extends BaseActivity {
    private final static String TAG = "EvaluateActivity";
    @Bind(R.id.title_back)
    ImageView mTitleBack;
    @Bind(R.id.head_pic)
    CircleImageView mHeadPic;
    @Bind(R.id.title_tv)
    TextView mTitleTv;
    @Bind(R.id.more)
    TextView mMore;
    @Bind(R.id.image_more)
    ImageView mImageMore;
    @Bind(R.id.toolbar_layout)
    RelativeLayout mToolbarLayout;
    @Bind(R.id.ratingBar)
    MaterialRatingBar mRatingBar;
    @Bind(R.id.message)
    EditText mMessage;
    private Context mContext;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_evaluate;
    }

    @Override
    protected void initInjector() {
        mContext = this;
    }

    @Override
    protected void initEventAndData() {
        mTitleTv.setText("评价");
        mMore.setText("提交");
        mMore.setTextColor(Color.parseColor("#F1F1F1"));
        mMore.setClickable(false);
        mMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(mMessage.getText().toString())) {
                    mMore.setTextColor(Color.parseColor("#F1F1F1"));
                    mMore.setClickable(false);
                } else {
                    mMore.setTextColor(Color.parseColor("#3eb0ff"));
                    mMore.setClickable(true);
                }
            }
        });
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @OnClick({R.id.title_back, R.id.more})
    public void onViewClicked(View view) {
        ButtonTools.disabledView(view, 1);
        switch (view.getId()) {
            case R.id.title_back:
                setResult(-1);
                finish();
                break;
            case R.id.more:
                upLoadEvalute();
                break;
        }
    }

    private void upLoadEvalute() {
        showLoading("");
        String jsonBean = new Gson().toJson(new EvaluateRequestBean(Common.TOKEN, this.getIntent().getExtras()
                .getString("order_id"), mMessage.getText().toString(), ((int) mRatingBar.getRating()) + ""));
        NetWorking.requestNetData("userside/commentOrder", jsonBean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dissLoadDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                dissLoadDialog();
                //TODO 评价完成后退出
                BasicResponseBean bean = new Gson().fromJson(response, BasicResponseBean.class);
                if ("1".equals(bean.getErrorCode())) {
                    setResult(1);
                    finish();
                } else {
                    showBaseMessageDialog(bean.getErrorMsg());
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(-1);

    }
}
