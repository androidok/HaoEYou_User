package com.haoeyou.user.mvp.model;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.haoeyou.user.bean.TagTitleResponseBean;
import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.network.DataCallback;
import com.haoeyou.user.network.NetWorking;
import com.haoeyou.user.utils.JsonFormat;

import okhttp3.Call;

/**
 * Created by Mou on 2017/7/11.
 */

public class NewsGroupModel implements NewsGroupModelImp {
    @Override
    public void getArticleTags(Context mContext, final MVPCallBack<TagTitleResponseBean> mBack) {
        NetWorking.requestNetData("article/getArticleTags", "", new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mBack.failed("");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("getArticleTags", JsonFormat.format(response));
                TagTitleResponseBean bean = new Gson().fromJson(response, TagTitleResponseBean.class);
                if ("1".equals(bean.getErrorCode())) {
                    mBack.succeed(bean);
                } else {
                    mBack.failed(bean.getErrorMsg());
                }
            }
        });
    }
}
