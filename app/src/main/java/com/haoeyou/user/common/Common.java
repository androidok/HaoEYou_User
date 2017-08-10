package com.haoeyou.user.common;


import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;

/**
 * 类名: {@link Common}
 * <br/> 功能描述: 存储全局数据的基本类
 * <br/> 作者: MouTao
 * <br/> 时间: 2017/5/19
 */
public class Common {
    /**
     * 是否为第一次登录
     */
    public static boolean IS_FIRST_LOAD = false;
    /**
     * 登录账户
     */
    public static String ACCOUNT = "";
    /**
     * 用户TOKEN
     */
    public static String TOKEN = "";
    /**
     * 从哪儿来
     */
    public static String FROM = "";
    /**
     * inflater 实例
     */
    private static LayoutInflater inflater;
    /**
     * 当前网络状态
     */
    public static boolean NET_STATE = true;

    /**
     * view 实例
     *
     * @param context
     * @param parent
     * @param res
     * @return
     */
    public static View inflate(Context context, ViewGroup parent, int res) {
        if (inflater == null) {
            inflater = LayoutInflater.from(context);
        }
        return inflater.inflate(res, parent, false);

    }

    /**
     * 手机存储路径
     * Environment.getExternalStorageDirectory();
     */
    public static final String PHONE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File
            .separator + "haoeyou" + File.separator;

    /**
     * 拍照权限
     */
    public static final int PERMISSIONS_CAMERA = 1;

    /**
     * 读权限
     */
    public static final int READ_EXTERNAL_STORAGE = 2;
    /**
     * 读权限
     */
    public static final int LOCATION_STATE = 3;
    /**
     * 用户昵称
     */
    public static String NIKE_NAME = "";
    /**
     * 文件头像ID
     */
    public static String FILE_ID = "";
    /**
     * 环信ID
     */
    public static String EM_ID = "";
    /**
     * 户头像连接
     */
    public static String HEAD_URL = "";
    /**
     * 基础连接
     */
    public static String BASE_URL = "http://app.haoeyou.com:8080/api/";
}
