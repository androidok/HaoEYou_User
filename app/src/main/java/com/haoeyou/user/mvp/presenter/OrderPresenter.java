package com.haoeyou.user.mvp.presenter;

import android.content.Context;

import com.haoeyou.user.bean.OrdersResponseBean;
import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.mvp.model.OrderModel;
import com.haoeyou.user.mvp.view.OrderView;

import java.util.ArrayList;

/**
 * Created by Mou on 2017-08-08.
 */

public class OrderPresenter extends BasePresenter<OrderView> {
    OrderModel mModel = new OrderModel();

    public void getOrderList(Context context, String jsonBean) {
        mModel.getOrderList(context, jsonBean, new MVPCallBack<ArrayList<OrdersResponseBean>>() {
            @Override
            public void succeed(ArrayList<OrdersResponseBean> mData) {
                if (mView != null) {
                    mView.getOrderListSuccess(mData);
                }
            }

            @Override
            public void failed(String message) {
                if (mView != null) {
                    mView.getOrderListFailed(message);
                }
            }
        });
    }

    public void cancleOrder(Context context, String jsonBean) {
        mView.showLoadProgressDialog("");
        mModel.cancleOrder(context, jsonBean, new MVPCallBack<Object>() {
            @Override
            public void succeed(Object mData) {
                if (mView != null) {
                    mView.disDialog();
                    mView.cancleOrderSuccess();
                }
            }

            @Override
            public void failed(String message) {
                if (mView != null) {
                    mView.disDialog();
                    mView.cancleOrderFailed();
                }
            }
        });
    }
}
