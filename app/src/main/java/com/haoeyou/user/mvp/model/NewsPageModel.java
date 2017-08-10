package com.haoeyou.user.mvp.model;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.haoeyou.user.bean.Artical;
import com.haoeyou.user.bean.TagNwesResponseBean;
import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.network.DataCallback;
import com.haoeyou.user.network.NetWorking;
import com.haoeyou.user.utils.JsonFormat;

import java.util.ArrayList;

import okhttp3.Call;

/**
 * Created by Mou on 2017/7/11.
 */

public class NewsPageModel {

    public void getArticlesPage(Context context, String jsonBean, final MVPCallBack<ArrayList<Artical>> mBack) {
        NetWorking.requestNetData("article/getArticlesPage", jsonBean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mBack.failed("");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("getArticlesPage", JsonFormat.format(response));
                TagNwesResponseBean bean = new Gson().fromJson(response, TagNwesResponseBean.class);
                if ("1".equals(bean.getErrorCode())) {
                    mBack.succeed(bean.getArticles());
                } else {
                    mBack.failed(bean.getErrorMsg());
                }
            }
        });
    }
}
