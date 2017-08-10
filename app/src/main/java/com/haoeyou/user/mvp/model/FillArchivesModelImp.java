package com.haoeyou.user.mvp.model;

import android.content.Context;

import com.haoeyou.user.bean.ArchivesInfoResponseBean;
import com.haoeyou.user.event.MVPCallBack;

/**
 * Created by Mou on 2017/6/9.
 */

public interface FillArchivesModelImp extends BaseModel {
    /**
     * 创建档案
     *
     * @param context
     * @param jsonBean
     * @param mBack
     */
    void createNewArchives(Context context, String jsonBean, MVPCallBack<String> mBack);

    /**
     * 更改档案信息
     *
     * @param context
     * @param jsonBean
     * @param mBack
     */
    void changeArchives(Context context, String jsonBean, MVPCallBack<String> mBack);

    /**
     * 获取档案信息
     *
     * @param context
     * @param jsonBean
     * @param mBack
     */
    void getArchivesInfo(Context context, String jsonBean, MVPCallBack<ArchivesInfoResponseBean> mBack);

}
