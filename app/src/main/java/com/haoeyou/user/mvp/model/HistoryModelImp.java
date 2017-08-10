package com.haoeyou.user.mvp.model;

import android.content.Context;

import com.haoeyou.user.bean.AddMedicalResponseBean;
import com.haoeyou.user.bean.AllMedicalResponseBean;
import com.haoeyou.user.bean.BasicRequestBean;
import com.haoeyou.user.event.MVPCallBack;

/**
 * Created by Mou on 2017/7/7.
 */

public interface HistoryModelImp {
    void getMedicalHistoryList(Context mContext, BasicRequestBean bean, MVPCallBack<AllMedicalResponseBean> mBack);
}
