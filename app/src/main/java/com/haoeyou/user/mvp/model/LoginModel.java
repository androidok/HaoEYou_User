package com.haoeyou.user.mvp.model;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.haoeyou.user.bean.BasicRequestBean;
import com.haoeyou.user.bean.LoginResponse;
import com.haoeyou.user.bean.SocialDocResponseBean;
import com.haoeyou.user.cache.UserCacheManager;
import com.haoeyou.user.common.Common;
import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.network.DataCallback;
import com.haoeyou.user.network.NetWorking;
import com.haoeyou.user.utils.SharedPreferencesHelper;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.zhy.http.okhttp.OkHttpUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.Call;

public class LoginModel implements LoginModelImp {

    @Override
    public void login(final Context mContext, final String Account, String jsonBean, final MVPCallBack<String> mBack) {
        NetWorking.requestNetData("authorization/createToken", jsonBean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mBack.failed("");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("createToken", response);
                final LoginResponse bean = new Gson().fromJson(response, LoginResponse.class);
                if ("1".equals(bean.getErrorCode())) {
                    Common.TOKEN = bean.getToken();
                    SharedPreferencesHelper.putString(mContext, "TOKEN", bean.getToken());
                    //登录环信
                    loginEM(mContext, Account, mBack);
                } else {
                    mBack.failed(bean.getErrorMsg());
                }

            }
        });
    }

    /**
     * <br/> 方法详述: 获取社交档案,登录环信,避免注册用户删除当前程序后,再次下载登录时候数据为空
     */
    private void loginEM(final Context mContext, final String Account, final MVPCallBack<String> mBack) {
        BasicRequestBean bean = new BasicRequestBean(Account, Common.TOKEN);
        String jsonbean = new Gson().toJson(bean);
        NetWorking.requestNetData("chat/getSocialDoc", jsonbean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mBack.failed("");
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("getSocialDoc", response);
                SocialDocResponseBean bean = new Gson().fromJson(response, SocialDocResponseBean.class);

                if ("1".equals(bean.getErrorCode())) {
                    Common.HEAD_URL = Common.BASE_URL + "fileStorage/download?id=" + bean.getHeadFileID() + "&token="
                            + Common.TOKEN;
                    Common.NIKE_NAME = bean.getNickName();
                    Common.FILE_ID = bean.getHeadFileID();
                    SharedPreferencesHelper.putString(mContext, "NIKE_NAME", bean.getNickName());
                    SharedPreferencesHelper.putString(mContext, "FILE_ID", bean.getHeadFileID());
                    SharedPreferencesHelper.putString(mContext, "HEAD_URL", Common.HEAD_URL);
                    EventBus.getDefault().post("0");
                    //TODO 环信登录 
                    EMClient.getInstance().login(Account, "haoeyouuser", new EMCallBack() {
                        @Override
                        public void onSuccess() {
                            UserCacheManager.save(EMClient.getInstance().getCurrentUser(), Common.NIKE_NAME, Common
                                    .HEAD_URL);
                            mBack.succeed("");
                        }

                        @Override
                        public void onError(int i, String s) {
//                            mContext.getMainLooper().prepare();
//                            Log.e("ONE", "用户登录失败," + i + "," + s);
                            mBack.failed("");
//                            mContext.getMainLooper().loop();
                        }

                        @Override
                        public void onProgress(int i, String s) {

                        }
                    });

                } else {
                    mBack.failed(bean.getErrorMsg());
                }
            }
        });
    }
}