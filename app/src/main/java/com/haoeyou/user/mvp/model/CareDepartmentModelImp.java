package com.haoeyou.user.mvp.model;

import android.content.Context;

import com.haoeyou.user.bean.CategoryResponseBean;
import com.haoeyou.user.event.MVPCallBack;

/**
 * Created by Mou on 2017/5/24.
 */

public interface CareDepartmentModelImp extends BaseModel {
    /**
     * 请求科室分类
     *
     * @param mContext    上下文
     * @param jsonBean    请求字符串
     * @param mvpCallBack 回调
     */
    void getCategoryList(Context mContext, String jsonBean, MVPCallBack<CategoryResponseBean> mvpCallBack);
}
