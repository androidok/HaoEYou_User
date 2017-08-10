package com.haoeyou.user.mvp.model;

import android.content.Context;

import com.haoeyou.user.bean.TagTitleResponseBean;
import com.haoeyou.user.event.MVPCallBack;

/**
 * Created by Mou on 2017/7/11.
 */

public interface NewsGroupModelImp {
    void getArticleTags(Context mContext, MVPCallBack<TagTitleResponseBean> mBack);

}
