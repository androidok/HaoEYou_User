package com.haoeyou.user.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.haoeyou.user.R;
import com.haoeyou.user.base.BaseActivity;
import com.haoeyou.user.bean.BasicRequestBean;
import com.haoeyou.user.bean.BasicResponseBean;
import com.haoeyou.user.common.Common;
import com.haoeyou.user.mvp.presenter.BasePresenter;
import com.haoeyou.user.network.DataCallback;
import com.haoeyou.user.network.NetWorking;
import com.haoeyou.user.utils.JsonFormat;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class FeedbackActivity extends BaseActivity {
    private final static String TAG = "FeedbackActivity";
    @Bind(R.id.title_back)
    ImageView mTitleBack;
    @Bind(R.id.title_tv)
    TextView mTitleTv;
    @Bind(R.id.totalNum)
    TextView mTotalNum;
    @Bind(R.id.more)
    TextView mMore;
    @Bind(R.id.image_more)
    ImageView mImageMore;
    @Bind(R.id.toolbar_layout)
    RelativeLayout mToolbarLayout;
    @Bind(R.id.message)
    EditText mMessage;
    @Bind(R.id.normal_btn)
    Button mNormalBtn;
    private Context mContext;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initInjector() {
        mContext = this;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initEventAndData() {
        mTitleTv.setText("意见反馈");
        mNormalBtn.setText("提交");
        mNormalBtn.setClickable(false);
        initTextWatcher();
    }

    private void initTextWatcher() {
        mMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mTotalNum.setText(mMessage.getText().length() + "/300");
                if (mMessage.getText().length() != 0) {
                    mNormalBtn.setClickable(true);
                } else {
                    mNormalBtn.setClickable(false);
                }
            }
        });
    }

    @OnClick({R.id.title_back, R.id.normal_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.normal_btn:
                addFeedBack();
                break;
        }
    }

    private void addFeedBack() {
        showLoading("");
        String jsonBean = new Gson().toJson(new BasicRequestBean(mMessage.getText().toString(), Common.TOKEN))
                .replace("targetAccount", "content");
        NetWorking.requestNetData("feedback/addFeedback", jsonBean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dissLoadDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                dissLoadDialog();
                Log.e("addFeedback", JsonFormat.format(response));
                BasicResponseBean bean = new Gson().fromJson(response, BasicResponseBean.class);
                if ("1".equals(bean.getErrorCode())) {
                    showToast("提交成功");
                    finish();
                } else {
                    showBaseMessageDialog(bean.getErrorMsg());
                }
            }
        });
    }


    public static void startAction(Context ct, String from) {
        Intent itt = new Intent(ct, FeedbackActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Common.FROM, from);
        itt.putExtras(bundle);
        ct.startActivity(itt);
    }
}
