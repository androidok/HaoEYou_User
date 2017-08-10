package com.haoeyou.user.mvp.view;

import com.haoeyou.user.bean.OrdersResponseBean;

import java.util.ArrayList;

/**
 * Created by Mou on 2017-08-08.
 */

public interface OrderView extends BaseView {

    void getOrderListSuccess(ArrayList<OrdersResponseBean> mData);

    void getOrderListFailed(String message);

    void cancleOrderSuccess();

    void cancleOrderFailed();
}
