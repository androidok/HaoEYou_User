package com.haoeyou.user.activity;


import com.haoeyou.user.R;
import com.haoeyou.user.base.BaseActivity;
import com.haoeyou.user.mvp.presenter.BasePresenter;
import com.haoeyou.user.mvp.presenter.MainActivityPresenter;
import com.haoeyou.user.mvp.view.MainActivityView;

/**
 * 类名: {@link OtherActivity}
 * <br/> 功能描述: 主功能界面
 * <br/> 作者: MouTao
 * <br/> 时间: 2017/5/16
 * <br/> 最后修改者:
 * <br/> 最后修改内容:
 */
public class OtherActivity extends BaseActivity implements MainActivityView {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public BasePresenter getPresenter() {
        return new MainActivityPresenter();
    }

    @Override
    public void showLoadProgressDialog(String str) {

    }

    @Override
    public void disDialog() {

    }

    @Override
    public void showToast(String message) {

    }
}
