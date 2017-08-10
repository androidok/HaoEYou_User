package com.haoeyou.user.mvp.view;

import com.haoeyou.user.bean.HomeDataResponse;
import com.haoeyou.user.bean.WaiterResponseBean;

/**
 * Created by Mou on 2017/5/22.
 */

public interface MainFragmentView extends BaseView {
    void getHomeData(HomeDataResponse mData);

    void getMyWaiterSuccess(WaiterResponseBean mData);

    void getMyWaiterFailed(String message);
}
