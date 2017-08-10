package com.haoeyou.user.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haoeyou.user.R;
import com.haoeyou.user.base.BaseActivity;
import com.haoeyou.user.common.Common;
import com.haoeyou.user.entity.PickerListener;
import com.haoeyou.user.mvp.presenter.BasePresenter;
import com.haoeyou.user.utils.PickerUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类名: {@link BaseInfoActivity}
 * <br/> 功能描述:基本资料界面
 * <br/> 作者: MouTao
 * <br/> 时间: 2017/5/25
 */
public class BaseInfoActivity extends BaseActivity {
    private final static String TAG = "BaseInfoActivity";
    private Context mContext;
    @Bind(R.id.title_back)
    ImageView mTitleBack;
    @Bind(R.id.title_tv)
    TextView mTitleTv;
    @Bind(R.id.more)
    TextView mMore;
    @Bind(R.id.toolbar_layout)
    RelativeLayout mToolbarLayout;
    @Bind(R.id.name)
    EditText mName;
    @Bind(R.id.sex)
    TextView mSex;
    @Bind(R.id.birthday)
    TextView mBirthday;
    @Bind(R.id.age)
    TextView mAge;
    @Bind(R.id.tel)
    EditText mTel;
    @Bind(R.id.urgent_tel)
    EditText mUrgentTel;
    @Bind(R.id.area)
    TextView mArea;
    @Bind(R.id.department)
    TextView mDepartment;
    @Bind(R.id.normal_btn)
    Button mNormalBtn;
    private PickerUtils mPicker;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_info;
    }

    @Override
    protected void initInjector() {
        onTintStatusBar();
        ButterKnife.bind(this);
        mContext = this;
    }

    @Override
    protected void initEventAndData() {
        mTitleTv.setText("基本资料");
        mMore.setText("完成");
        mPicker = new PickerUtils(mContext);
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    /**
     * @param ct   上下文
     * @param from 从哪儿跳来的
     */
    public static void startAction(Context ct, String from) {
        Intent itt = new Intent(ct, BaseInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Common.FROM, from);
        itt.putExtras(bundle);
        ct.startActivity(itt);
    }


    @OnClick({R.id.title_back, R.id.more, R.id.sex, R.id.birthday, R.id.tel, R.id.urgent_tel, R.id.area, R.id
            .department, R.id.normal_btn})
    public void onViewClicked(View view) {
        closeInputBroad();
        switch (view.getId()) {
            case R.id.title_back:
                break;
            case R.id.more:
                break;
            case R.id.sex:
                showSexPicker();
                break;
            case R.id.birthday:
                showBirthdayPicker();
                break;
            case R.id.area:
                showAreaPicker();
                break;
            case R.id.department:
                gotoEditInfo(BaseInfoActivity.TAG, "", 102);
                break;
            case R.id.normal_btn:
                break;
        }
    }

    private void showSexPicker() {
        mPicker.buildSexPicker(new PickerListener<String>() {
            @Override
            public void pickerData(int position,String data) {
                mSex.setText(data);
            }
        });
    }

    private void showBirthdayPicker() {
        mPicker.buildBirthdayPicker(new PickerListener<String>() {
            @Override
            public void pickerData(int position,String data) {
                mBirthday.setText(data);
            }
        });
    }

    private void showAreaPicker() {
        mPicker.buildAreaPicker(new PickerListener<String>() {
            @Override
            public void pickerData(int position,String data) {
                mArea.setText(data);
            }
        });
    }

    private void gotoEditInfo(String from, String info, int requestCode) {
        Intent itt = new Intent(BaseInfoActivity.this, CareDepartmentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Common.FROM, from);
        bundle.putString("PRE_INFO", info);
        itt.putExtras(bundle);
        startActivityForResult(itt, requestCode);
    }

    /**
     * <br/> 方法名称: closeInputBroad
     * <br/> 方法详述: 关闭软键盘
     */
    private void closeInputBroad() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mName.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(mTel.getWindowToken(), 0);
    }

}
