package com.haoeyou.user.mvp.model;

import android.content.Context;

import com.haoeyou.user.event.MVPCallBack;

/**
 * 类名: {@link LoginModelImp}
 * <br/> 功能描述: {@link LoginModel}的回调借口
 * <br/> 作者: MouTao
 * <br/> 时间: 2017/5/16
 * <br/> 最后修改者:
 * <br/> 最后修改内容:
 */
public interface LoginModelImp extends BaseModel {
    /**
     * 登录Action
     *
     * @param mContext 上下文
     * @param jsonBean 请求字符串
     * @param mBack    监听
     */
    void login(Context mContext, String Account,String jsonBean, MVPCallBack<String> mBack);
}