package com.haoeyou.user.mvp.model;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.haoeyou.user.bean.AlterRequestBean;
import com.haoeyou.user.bean.BasicResponseBean;
import com.haoeyou.user.bean.SocialDocResponseBean;
import com.haoeyou.user.bean.UploadResponseBean;
import com.haoeyou.user.bean.VersionResponseBean;
import com.haoeyou.user.cache.UserCacheManager;
import com.haoeyou.user.common.Common;
import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.network.DataCallback;
import com.haoeyou.user.network.NetWorking;
import com.haoeyou.user.utils.JsonFormat;
import com.haoeyou.user.utils.LogUtil;
import com.haoeyou.user.utils.SharedPreferencesHelper;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Call;

/**
 * Created by Mou on 2017/6/26.
 */

public class MainModel {
    public void uploadFile(final Context mContext, String jsonBean, final MVPCallBack<Object> mBack) {
        NetWorking.requestNetData("chat/uploadHead", jsonBean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("提交文件", JsonFormat.format(response));
                UploadResponseBean bean = new Gson().fromJson(response, UploadResponseBean.class);
                if ("1".equals(bean.getErrorCode())) {
                    //文件提交成功后,把文件ID保存到本地,
                    Common.FILE_ID = bean.getFileID();
                    Common.HEAD_URL = Common.BASE_URL + "fileStorage/download?id=" + bean.getFileID() + "&token=" + 
                            Common.TOKEN;
                    SharedPreferencesHelper.putString(mContext, "FILE_ID", bean.getFileID());
                    SharedPreferencesHelper.putString(mContext, "HEAD_URL", Common.HEAD_URL);
                    UserCacheManager.save(Common.ACCOUNT, Common.NIKE_NAME, Common.HEAD_URL);
                    mBack.succeed("");
                } else {
                    mBack.failed(bean.getErrorMsg());
                }
            }
        });
    }

    public void alterNickName(Context mContext, String jsonBean, final MVPCallBack mBack) {
        NetWorking.requestNetData("chat/alterSocialDoc", jsonBean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("修改昵称", JsonFormat.format(response));
                BasicResponseBean bean = new Gson().fromJson(response, BasicResponseBean.class);
                if ("1".equals(bean.getErrorCode())) {
                    UserCacheManager.save(Common.ACCOUNT, Common.NIKE_NAME, Common.BASE_URL + 
                            "fileStorage/download?id=" + Common.FILE_ID + "&token=" + Common.TOKEN);
                    mBack.succeed("");
                } else {
                    mBack.failed(bean.getErrorMsg());
                }
            }
        });
    }

    public void checkAppVersion(final Context context, String jsonBean, final MVPCallBack<VersionResponseBean> mBack) {
        NetWorking.requestNetData("appversion", jsonBean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtil.e("版本获取", JsonFormat.format(response));
                VersionResponseBean bean = new Gson().fromJson(response, VersionResponseBean.class);
                if (bean != null) {
                    mBack.succeed(bean);
                }
            }
        });
    }

}
