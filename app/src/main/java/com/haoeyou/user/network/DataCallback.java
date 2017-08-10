package com.haoeyou.user.network;

import com.zhy.http.okhttp.callback.Callback;

import java.net.URLDecoder;

import okhttp3.Response;

/**
 * 类名: {@link DataCallback}
 * <br/> 功能描述: okhttp的回调
 * <br/> 作者: MouTao
 * <br/> 时间: 2017/5/17
 * <br/> 最后修改者:
 * <br/> 最后修改内容:
 */
public abstract class DataCallback extends Callback<String> {
    @Override
    public String parseNetworkResponse(Response response, int id) throws Exception {
        return URLDecoder.decode(response.body().string(), "UTF-8");
    }
}
