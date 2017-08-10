package com.haoeyou.user.mvp.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.haoeyou.user.bean.HomeDataResponse;
import com.haoeyou.user.bean.WaiterResponseBean;
import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.fragment.MainFragment;
import com.haoeyou.user.mvp.model.MainFragmentModel;
import com.haoeyou.user.mvp.view.MainFragmentView;

/**
 * 类名: {@link MainFragmentPresenter}
 * <br/> 功能描述:主功能界面的解析层
 * <br/> 作者: MouTao
 * <br/> 时间: 2017/5/22
 */
public class MainFragmentPresenter extends BasePresenter<MainFragmentView> {
    MainFragmentModel mModel = new MainFragmentModel();

    public void getHomeData(Context mContext) {
        mView.showLoadProgressDialog("");
        mModel.getHomeData(mContext, new MVPCallBack<HomeDataResponse>() {
            @Override
            public void succeed(HomeDataResponse mData) {
                if (mView != null) {
                    mView.disDialog();
                    mView.getHomeData(mData);
                }
            }

            @Override
            public void failed(String message) {
                if (mView != null) {
                    mView.disDialog();
                }
            }
        });
    }

    public void getMyWaiter(Context mContext) {
        mModel.getMyWaiter(mContext, new MVPCallBack<WaiterResponseBean>() {
            @Override
            public void succeed(WaiterResponseBean mData) {
                if (mView != null) {
                    mView.getMyWaiterSuccess(mData);
                }
            }

            @Override
            public void failed(String message) {
                if (mView != null) {
                    if (!TextUtils.isEmpty(message))
                        mView.getMyWaiterFailed(message);
                }
            }
        });
    }
}
