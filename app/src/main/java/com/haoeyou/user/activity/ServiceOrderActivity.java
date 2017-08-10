package com.haoeyou.user.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.haoeyou.user.R;
import com.haoeyou.user.base.BaseActivity;
import com.haoeyou.user.bean.Artical;
import com.haoeyou.user.bean.BasicResponseBean;
import com.haoeyou.user.bean.HealthDocState;
import com.haoeyou.user.bean.OrderRequestBean;
import com.haoeyou.user.common.AppManager;
import com.haoeyou.user.common.Common;
import com.haoeyou.user.mvp.presenter.BasePresenter;
import com.haoeyou.user.network.DataCallback;
import com.haoeyou.user.network.NetWorking;
import com.haoeyou.user.utils.ButtonTools;
import com.haoeyou.user.utils.JsonFormat;
import com.haoeyou.user.utils.LogUtil;
import com.haoeyou.user.widget.dialog.ApkUpDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class ServiceOrderActivity extends BaseActivity {
    private final static String TAG = "ServiceOrderActivity";
    @Bind(R.id.title_back)
    ImageView mTitleBack;
    @Bind(R.id.title_tv)
    TextView mTitleTv;
    @Bind(R.id.more)
    TextView mMore;
    @Bind(R.id.image_more)
    ImageView mImageMore;
    @Bind(R.id.toolbar_layout)
    RelativeLayout mToolbarLayout;
    @Bind(R.id.contract)
    TextView mContract;
    @Bind(R.id.message)
    EditText mMessage;
    @Bind(R.id.check)
    CheckBox mCheck;
    private Context mContext;
    private OrderRequestBean mOrderBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_service_order;
    }

    @Override
    protected void initInjector() {
        mContext = this;
    }

    @Override
    protected void initEventAndData() {
        mTitleTv.setText("服务预约");
        mMore.setText("提交");
        mMore.setTextColor(Color.parseColor("#F1F1F1"));
        mMore.setClickable(false);
        mOrderBean = (OrderRequestBean) this.getIntent().getExtras().getSerializable("ORDER_BEAN");
        if (mOrderBean == null)
            return;
        mMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(mMessage.getText().toString()) || !mCheck.isChecked()) {
                    mMore.setTextColor(Color.parseColor("#F1F1F1"));
                    mMore.setClickable(false);
                } else {
                    mMore.setTextColor(Color.parseColor("#3eb0ff"));
                    mMore.setClickable(true);
                }
            }
        });
        mCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (TextUtils.isEmpty(mMessage.getText().toString()) || !mCheck.isChecked()) {
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

    @OnClick({R.id.title_back, R.id.more, R.id.contract})
    public void onViewClicked(View view) {
        ButtonTools.disabledView(view, 1);
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.more:
                upload();
                break;
            case R.id.contract:
                Artical artical = new Artical("", "/frontend/agreement.html", "", "");
                NewsActivity.startAction(mContext, MainActivity.TAG, artical);
                break;
        }
    }

    private void upload() {
        mOrderBean.setNeed(mMessage.getText().toString());
        NetWorking.requestNetData("userside/createOrder", new Gson().toJson(mOrderBean), new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtil.e("创建订单", JsonFormat.format(response));
                BasicResponseBean bean = new Gson().fromJson(response, BasicResponseBean.class);
                if ("1".equals(bean.getErrorCode())) {
                    initOrderDialog();
                } else {
                    showBaseMessageDialog(bean.getErrorMsg());
                }
            }
        });
    }

    private void initOrderDialog() {
        new ApkUpDialog(mContext, "2").builder().setContent(R.drawable.uporder_buttom, "提交订单成功", getString(R.string
                .goto_persnal_centren), "查看此订单").
                setPositiveButton("好的", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }).setNegativeButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 取消下载
            }
        }).show();
    }


    /**
     * @param ct   上下文
     * @param from 从哪儿跳来的
     */
    public static void startAction(Context ct, OrderRequestBean orderBean, String from) {
        Intent itt = new Intent(ct, ServiceOrderActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Common.FROM, from);
        bundle.putSerializable("ORDER_BEAN", orderBean);
        itt.putExtras(bundle);
        ct.startActivity(itt);
    }
}
