package com.haoeyou.user.network;

import com.haoeyou.user.common.Common;
import com.haoeyou.user.utils.JsonFormat;
import com.haoeyou.user.utils.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.Map;

import okhttp3.MediaType;

/**
 * 类名: NetWorking
 * <br/>功能描述:
 * <br/>作者: MoutTao
 * <br/>时间: 2017/3/9
 * <br/>最后修改者:
 * <br/>最后修改内容:
 */


public class NetWorking {


    private static Map<String, String> login = null;

    /**
     * @param tag        标识，可以通过tag来取消请求 OkHttpUtils.cancelTag(tag);//取消以Activity.this作为tag的请求
     * @param myCallBack 回调，new DataCallback
     */
    public static void requestNetData(String tag, String jsonBean, DataCallback myCallBack) {
            //拼接完整的url
            String url = Common.BASE_URL + tag;
            LogUtil.e("Request_" + tag, JsonFormat.format(jsonBean));
            OkHttpUtils.postString().url(url).tag(tag).content(jsonBean).mediaType(MediaType.parse("application/json;" +
                    "" + "" + " " + "charset=utf-8")).build().execute(myCallBack);
    }

}
