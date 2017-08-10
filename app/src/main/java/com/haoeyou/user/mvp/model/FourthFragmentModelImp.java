package com.haoeyou.user.mvp.model;

import android.content.Context;

import com.haoeyou.user.bean.AllDocResponseBean;
import com.haoeyou.user.event.MVPCallBack;

import java.util.ArrayList;

/**
 * Created by Mou on 2017/6/15.
 */

public interface FourthFragmentModelImp {
    void getAllDoc(Context mContext, String JsonBean, MVPCallBack<AllDocResponseBean> mBack);
}
