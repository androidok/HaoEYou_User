package com.haoeyou.user.mvp.model;

import android.content.Context;

import com.haoeyou.user.event.MVPCallBack;

/**
 * 类名: {@link FindPassModelImp}
 * <br/> 功能描述:{@link RegisterModel}的实现方法
 * <br/> 作者: MouTao
 * <br/> 时间: 2017/5/23
 */
public interface FindPassModelImp extends BaseModel {
    void resetPassword(Context mContext, String jsonBen, MVPCallBack mBack);

    void getVerificationCode(Context mContext, String jsonBen, MVPCallBack mBack);
}
