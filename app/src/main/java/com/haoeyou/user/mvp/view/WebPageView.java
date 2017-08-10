package com.haoeyou.user.mvp.view;

import com.haoeyou.user.bean.HealthDocState;

import java.util.ArrayList;

/**
 * Created by Mou on 2017/6/20.
 */

public interface WebPageView extends BaseView {
    void getDocListSucceed(ArrayList<HealthDocState> DocList);
}
