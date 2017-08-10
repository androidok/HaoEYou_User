package com.haoeyou.user.mvp.model;

import android.content.Context;

import com.haoeyou.user.event.MVPCallBack;

import java.util.ArrayList;

/**
 * Created by Mou on 2017/6/8.
 */

public interface SearchModelImp extends BaseModel {
    void getSearchItem(Context mContext, String jsonBean, MVPCallBack<ArrayList<String>> mBack);
}
