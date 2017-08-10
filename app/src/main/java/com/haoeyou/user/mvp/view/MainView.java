package com.haoeyou.user.mvp.view;

import com.haoeyou.user.bean.SocialDocResponseBean;
import com.haoeyou.user.bean.VersionResponseBean;

/**
 * Created by Mou on 2017/6/26.
 */

public interface MainView extends BaseView {


    void alterFileSuccess();

    void alterNikeNameSuccess();

    void getVersionInfo(VersionResponseBean mData);
}
