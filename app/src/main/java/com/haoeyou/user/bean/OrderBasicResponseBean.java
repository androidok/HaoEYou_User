package com.haoeyou.user.bean;

import java.util.ArrayList;

/**
 * Created by Mou on 2017-08-09.
 */

public class OrderBasicResponseBean extends BasicResponseBean {
    //    private String "errorCode";
    //    private String "errorMsg"
    private ArrayList<OrdersResponseBean> orders;

    public OrderBasicResponseBean(String errorCode, String errorMsg, ArrayList<OrdersResponseBean> orders) {
        super(errorCode, errorMsg);
        this.orders = orders;
    }

    public ArrayList<OrdersResponseBean> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<OrdersResponseBean> orders) {
        this.orders = orders;
    }
}
