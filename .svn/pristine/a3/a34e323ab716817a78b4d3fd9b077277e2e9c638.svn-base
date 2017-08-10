package com.haoeyou.user.common;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.haoeyou.user.DemoHelper;
import com.haoeyou.user.utils.CrashHandler;
import com.haoeyou.user.utils.SharedPreferencesHelper;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.model.FunctionConfig;
import com.luck.picture.lib.model.FunctionOptions;
import com.luck.picture.lib.model.PictureConfig;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.bugly.crashreport.CrashReport;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * 类名: MyApplication
 * <br/> 功能描述:
 * <br/> 作者: MouTao
 * <br/> 时间: 2017/6/21
 */

public class MyApplication extends MultiDexApplication {
    /**
     * 系统通知栏提醒
     */
    public static NotificationManager notifyManager = null;
    private static MyApplication mMyApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mMyApplication = this;
        initTinker();
        initBugly();
        CrashHandler.getInstance().init(mMyApplication);
        DemoHelper.getInstance().init(mMyApplication);
        isFirstLoad();
        initOkHttp();
        initGlideConfig();
        initIM();
        initSmartRefresh();
    }

    private void initTinker() {
        //        if (BuildConfig.TINKER_ENABLE) {
        //            // 我们可以从这里获得Tinker加载过程的信息
        //            ApplicationLike tinkerApplicationLike = TinkerPatchApplicationLike
        // .getTinkerPatchApplicationLike();
        //            // 初始化TinkerPatch SDK, 更多配置可参照API章节中的,初始化SDK
        //            TinkerPatch.init(tinkerApplicationLike).reflectPatchLibrary().setPatchRollbackOnScreenOff(true)
        //                    .setPatchRestartOnSrceenOff(true);
        //            // 每隔3个小时去访问后台时候有更新,通过handler实现轮训的效果
        //            new FetchPatchHandler().fetchPatchWithInterval(3);
        //            Log.i("TAG", "tinker init");
        //        }
    }

    private void initBugly() {
        Context context = getApplicationContext();
        // 获取当前进程名
        String processName = getAppName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(this.getPackageName()));
       /* 第三个参数为SDK调试模式开关，调试模式的行为特性如下：
        输出详细的Bugly SDK的Log；
        每一条Crash都会被立即上报；
        自定义日志将会在Logcat中输出。
        建议在测试阶段建议设置成true，发布时设置为false*/
        CrashReport.initCrashReport(getApplicationContext(), "eb70d04388", true);
    }

    //初始化环信
    private void initIM() {
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
        // 如果APP启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回

        if (processAppName == null || !processAppName.equalsIgnoreCase(this.getPackageName())) {
            Log.e("IM", "enter the service process!");
            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }
        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(true);
        //初始化
        EMClient.getInstance().init(this, options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(false);
        EaseUI.getInstance().init(this, options);
    }

    private void isFirstLoad() {
        //判断是否为第一次登录,如果是,则进入资料引导页,否则直接进入主界面
        Common.IS_FIRST_LOAD = SharedPreferencesHelper.getBoolean(getApplicationContext(), "FIRST", true);
        SharedPreferencesHelper.putBoolean(getApplicationContext(), "FIRST", false);
        if (!Common.IS_FIRST_LOAD) {
            Common.TOKEN = SharedPreferencesHelper.getString(getApplicationContext(), "TOKEN", "");
            Common.ACCOUNT = SharedPreferencesHelper.getString(getApplicationContext(), "ACCOUNT", "");
            Common.NIKE_NAME = SharedPreferencesHelper.getString(getApplicationContext(), "NIKE_NAME", "");
            Common.HEAD_URL = SharedPreferencesHelper.getString(getApplicationContext(), "HEAD_URL", "");
            Common.FILE_ID = SharedPreferencesHelper.getString(getApplicationContext(), "FILE_ID", "");
        }

    }

    private void initSmartRefresh() {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                //指定为经典Header，默认是 贝塞尔雷达Header
                return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
    }

    private void initGlideConfig() {
        FunctionOptions options = new FunctionOptions.Builder().setType(FunctionConfig.TYPE_IMAGE).setCompress(true)
                .setGrade(Luban.THIRD_GEAR).create();
        PictureConfig.getInstance().init(options);
    }

    public String getCachePath() {
        File cacheDir;
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            cacheDir = getExternalCacheDir();
        else
            cacheDir = getCacheDir();
        if (cacheDir != null && !cacheDir.exists())
            cacheDir.mkdirs();
        return cacheDir.getAbsolutePath();
    }

    /**
     * 初始化
     */
    private void initOkHttp() {
        //进行okhttp初始化配置
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(30000L, TimeUnit.MILLISECONDS).readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);

    }

    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }

    public static MyApplication getApplication() {
        return mMyApplication;
    }

    @Override
    public void onTerminate() {
        if (this.notifyManager != null) {
            notifyManager.cancelAll();
        }
        super.onTerminate();
    }

}


