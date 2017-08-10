package com.haoeyou.user.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.haoeyou.user.R;
import com.haoeyou.user.base.BaseActivity;
import com.haoeyou.user.bean.CodeBean;
import com.haoeyou.user.bean.RegisterBean;
import com.haoeyou.user.common.Common;
import com.haoeyou.user.mvp.presenter.BasePresenter;
import com.haoeyou.user.mvp.presenter.LoginPresenter;
import com.haoeyou.user.mvp.presenter.RegisterPresenter;
import com.haoeyou.user.mvp.view.RegisterView;
import com.haoeyou.user.utils.PhoneFormatCheckUtils;
import com.haoeyou.user.utils.SharedPreferencesHelper;
import com.haoeyou.user.widget.dialog.AlertDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类名: {@link RegisterActivity}
 * <br/> 功能描述:用户注册界面
 * <br/> 作者: MouTao
 * <br/> 时间: 2017/5/23
 */
public class RegisterActivity extends BaseActivity implements RegisterView {
    @Bind(R.id.title_back)
    ImageView mTitleBack;
    @Bind(R.id.title_tv)
    TextView mTitleTv;
    @Bind(R.id.toolbar_layout)
    RelativeLayout mToolbarLayout;
    @Bind(R.id.tel)
    EditText mTel;
    @Bind(R.id.clean)
    ImageView mClean;
    @Bind(R.id.code)
    EditText mCode;
    @Bind(R.id.get_code)
    TextView mGetCode;
    @Bind(R.id.nick)
    EditText mNick;
    @Bind(R.id.password)
    EditText mPassword;
    @Bind(R.id.normal_btn)
    Button mNormalBtn;
    @Bind(R.id.button_layout)
    LinearLayout mButtonLayout;
    public static final String TAG = "RegisterActivity";
    private Context mContext;
    private Boolean telFlag = false;
    private Boolean codeFlag = false;
    private Boolean nickFlag = false;
    private Boolean passFlag = false;
    private CountDownTimer timer;

    @Override
    public BasePresenter getPresenter() {
        return new RegisterPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initInjector() {
        mContext = this;
    }

    @Override
    protected void initEventAndData() {
        initWidget();
    }

    /**
     * <br/> 方法名称: initWidget
     * <br/> 方法详述: 初始化控件
     * <br/> 参数:
     * <br/> 返回值:
     */
    private void initWidget() {
        mTitleTv.setText(R.string.register);
        mNormalBtn.setText(R.string.register);
        mTitleBack.setImageResource(R.drawable.icon_back);
        mNormalBtn.setClickable(false);
        mTel.setText(getIntent().getExtras().getString("ACOUNT"));
        setOnChangeListener();
    }


    @OnClick({R.id.title_back, R.id.clean, R.id.get_code, R.id.normal_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                LoginActivity.startAction(mContext, "", RegisterActivity.TAG);
                finish();
                break;
            case R.id.clean:
                mTel.setText("");
                break;
            case R.id.get_code:
                if (PhoneFormatCheckUtils.isChinaPhoneLegal(mTel.getText().toString().trim())) {
                    starCountDown();
                    mGetCode.setTextColor(Color.parseColor("#a1a1a1"));
                    mGetCode.setClickable(false);
                    String jsonBean = new Gson().toJson(new CodeBean(mTel.getText().toString().trim()));
                    ((RegisterPresenter) mPresenter).getVerificationCode(mContext, jsonBean);
                }
                break;
            case R.id.normal_btn:
                String jsonBean = new Gson().toJson(new RegisterBean(mTel.getText().toString(), mNick.getText()
                        .toString(), mPassword.getText().toString(), mCode.getText().toString()));
                ((RegisterPresenter) mPresenter).registerAccount(mContext, jsonBean);
                break;
        }
    }

    private void setOnChangeListener() {
        mTel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (PhoneFormatCheckUtils.isChinaPhoneLegal(mTel.getText().toString().trim())) {
                    telFlag = true;
                    checkIsCanRegister();
                } else {
                    telFlag = false;
                }
            }
        });
        mCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (6 == mCode.getText().toString().length()) {
                    codeFlag = true;
                    checkIsCanRegister();
                } else {
                    codeFlag = false;
                }
            }
        });
        mNick.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(mNick.getText().toString().trim())) {
                    nickFlag = true;
                    checkIsCanRegister();
                } else {
                    nickFlag = false;
                }
            }
        });
        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (6 <= mPassword.getText().toString().trim().length()) {
                    passFlag = true;
                    checkIsCanRegister();
                } else {
                    passFlag = false;
                }
            }
        });
    }

    /**
     * <br/> 方法名称: checkIsCanRegister
     * <br/> 方法详述: 判断是否可以点击
     */
    private void checkIsCanRegister() {
        if (telFlag && codeFlag && nickFlag && passFlag) {
            mNormalBtn.setBackgroundResource(R.drawable.selector_save);
            mNormalBtn.setClickable(true);
        } else {
            mNormalBtn.setBackgroundResource(R.drawable.gray_background);
            mNormalBtn.setClickable(false);
        }
    }

    /**
     * <br/> 方法名称: starCountDown
     * <br/> 方法详述: 开始倒计时
     */
    private void starCountDown() {
        timer = new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                mGetCode.setText(millisUntilFinished / 1000 + "后重新获取");
            }

            public void onFinish() {
                mGetCode.setText("获取验证码");
                mGetCode.setClickable(true);
                mGetCode.setTextColor(Color.parseColor("#3eb0ff"));
            }

        };
        timer.start();
    }


    /**
     * @param ct   上下文
     * @param from 从哪儿跳来的
     */
    public static void startAction(Context ct, String account, String from) {
        Intent itt = new Intent(ct, RegisterActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Common.FROM, from);
        bundle.putString("ACOUNT", account);
        itt.putExtras(bundle);
        ct.startActivity(itt);
    }

    @Override
    public void showLoadProgressDialog(String str) {

    }

    @Override
    public void disDialog() {

    }

    @Override
    public void createAccountSuccess() {
        SharedPreferencesHelper.putString(mContext, "NIKE_NAME", mNick.getText().toString());
        Common.NIKE_NAME = mNick.getText().toString();

        new AlertDialog(this).setWidthRatio(0.7f).setMessageGravity(Gravity.CENTER).builder()/*.setTitle(getString(R
        .string
        .prompt))*/.hideTitleLayout().setMsg("恭喜你,账户注册成功!").setCancelable(false).setNegativeButton(("去登陆"), new View
                .OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.startAction(mContext, mTel.getText().toString(), RegisterActivity.TAG);
                finish();
            }
        }).show();
    }

    @Override
    public void createAccountFailed(String message) {
        showBaseMessageDialog(message);
    }

    @Override
    protected void onStop() {
        if (timer != null) {
            timer.cancel();
        }
        super.onStop();
    }
}
