package com.haoeyou.user.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.haoeyou.user.common.Common;
import com.haoeyou.user.mvp.presenter.BasePresenter;
import com.haoeyou.user.mvp.view.BaseView;
import com.haoeyou.user.utils.ToastUtils;
import com.haoeyou.user.widget.dialog.AlertDialog;
import com.haoeyou.user.widget.dialog.ProgressDialog;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.chat.EMClient;

import java.lang.reflect.Field;

import butterknife.ButterKnife;


/**
 * Created by zwl on 16/9/30.
 */

public abstract class BaseFragment<T extends BasePresenter<BaseView>> extends Fragment implements IBase {
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    protected Context mActivity;
    //是否可见状态
    private boolean isVisible;
    //View已经初始化完成
    private boolean isPrepared;
    //是否第一次加载完
    private boolean isFirstLoad = true;

    protected BasePresenter mPresenter;
    private View mView;
    //    private LoadProgressDialog progressDialog;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle 
            savedInstanceState) {
        if (mView != null) {
            ViewGroup parent = (ViewGroup) mView.getParent();
            if (parent != null) {
                parent.removeView(mView);
            }
            return mView;
        }
        isFirstLoad = true;
        //绑定View
        View view = null;
        view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        isPrepared = true;

        initInjector();//dagger2注解,子类实现initInjector()方法 进行inject()
        if (savedInstanceState != null) {
            initStateData(savedInstanceState);
        }
        mPresenter = getPresenter();
        if (mPresenter != null && this instanceof BaseView) {
            mPresenter.attach((BaseView) this);
        }
        //初始化事件和获取数据, 在此方法中获取数据不是懒加载模式
        initEventAndData();
        //在此方法中获取数据为懒加载模式,如不需要懒加载,请在initEventAndData获取数据,GankFragment有使用实例
        lazyLoad();
        EMClient.getInstance().addConnectionListener(connectionListener);
        mView = view;
        return view;
    }

    public void initStateData(Bundle savedInstanceState) {

    }

    @Override
    public void onDestroy() {
        ButterKnife.unbind(this);
        EMClient.getInstance().removeConnectionListener(connectionListener);
        super.onDestroy();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    @Override
    public void onAttach(Context context) {
        this.mActivity = context;
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void onInvisible() {
    }

    protected void lazyLoad() {
        if (!isPrepared || !isVisible || !isFirstLoad)
            return;
        isFirstLoad = false;
        lazyLoadData();
    }

    protected abstract int getLayoutId();

    protected abstract void initInjector();

    protected abstract void initEventAndData();

    protected abstract void lazyLoadData();

    public void showLoading(String string) {
        progressDialog = ProgressDialog.getInstance(getActivity());
        progressDialog.setMessage(TextUtils.isEmpty(string) ? "加载中..." : string);
        progressDialog.show();
    }

    public void dissLoadDialog() {
        if (progressDialog != null) {
            progressDialog.dismissHUD();
        }
    }


    public void showToast(String message) {
        ToastUtils.showToast(getActivity(), message);
    }

    public void showToast(int ResID) {
        ToastUtils.showToast(getActivity(), ResID);
    }

    public void showBaseMessageDialog(String message) {
        new AlertDialog(getActivity()).setWidthRatio(0.7f).setMessageGravity(Gravity.CENTER).builder()
                .hideTitleLayout().setMsg(message).setNegativeButton(("确定"), new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).show();
    }

    protected EMConnectionListener connectionListener = new EMConnectionListener() {

        @Override
        public void onDisconnected(int error) {
            Common.NET_STATE = false;
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    onConnectionDisconnected();
                }
            });
        }

        @Override
        public void onConnected() {
            Common.NET_STATE = true;
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    onConnectionConnected();
                }
            });
        }

    };

    public void onConnectionConnected() {

    }

    public void onConnectionDisconnected() {

    }
}
