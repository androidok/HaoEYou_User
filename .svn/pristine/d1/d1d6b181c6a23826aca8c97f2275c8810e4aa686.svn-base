package com.haoeyou.user.utils.appupdate;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.haoeyou.user.R;
import com.haoeyou.user.activity.MainActivity;
import com.haoeyou.user.common.Common;
import com.haoeyou.user.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;

/**
 * Created by Mou on 2017/7/26.
 */

public class AppUpdateService extends Service {
    private final AppUpdateBinder aBinder = new AppUpdateBinder();
    // 消息通知对象
    private Notification notification = null;
    // 消息通知管理对象
    private NotificationManager notificationManager = null;
    // UI线程处理句柄
    private Handler handler;
    // 消息通知id
    public static final int NOTIFICATION_ID = 1000;

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        initNotification();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return aBinder;
    }

    public class AppUpdateBinder extends Binder {
        public AppUpdateService getService() {
            return AppUpdateService.this;
        }
    }


    /**
     * 初始化消息通知
     */
    public void initNotification() {
        long when = System.currentTimeMillis();

        // V7包下的NotificationCompat
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        // Ticker是状态栏显示的提示
        builder.setTicker("应用更新");
        builder.setSmallIcon(R.mipmap.icon_start);
        builder.setWhen(when);
        builder.setAutoCancel(true);
        // 自定义contentView
        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.notification_update);
        contentView.setTextViewText(R.id.notification_update_progress_text, "0%");
        contentView.setImageViewResource(R.id.notification_update_image, R.mipmap.icon_start);
        // 为notification设置contentView
        builder.setContent(contentView);
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        // 构建一个notification
        notification = builder.build();
    }

    /**
     * 开一个线程来下载apk文件
     *
     * @param url     apk地址
     * @param version 版本
     */
    public void downloadStart(String url, String version) {
        DownloadRunnable downloadRunnable = new DownloadRunnable(url, version);
        Thread thread = new Thread(downloadRunnable);
        thread.start();
    }

    class DownloadRunnable implements Runnable {
        private String url;
        private String version;
        private InputStream input = null;
        private int flag = 0;
        private String fileName;

        public DownloadRunnable(String url, String version) {
            this.url = url;
            this.version = version;
        }


        @Override
        public void run() {
            //开始下载
            File file = new File(Common.PHONE_PATH + "APK/com.haoeyou.user.pak");
            if (file.exists())
                file.delete();
            OkHttpUtils.get().url(url).build().execute(new FileCallBack(Common.PHONE_PATH + "APK/", "com.haoeyou" + 
                    "" + ".user" + ".apk")//
            {

                @Override
                public void inProgress(final float progress, final long total, int id) {
                    Message msg = new Message();
                    msg.what = AppUpdateManager.MSG_DOWNLOAD_PROGRESS;
                    Bundle bundle = new Bundle();
                    bundle.putInt(AppUpdateManager.KEY_PERCENT, (int) progress);
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }

                @Override
                public void onError(Call call, Exception e, int id) {
                    flag = AppUpdateManager.FLAG_DOWNLOAD_ERROR;
                    sendDownloadResult(flag, fileName);
                    ToastUtils.showToast(getApplicationContext(), e.getMessage());
                }

                @Override
                public void onResponse(File response, int id) {
                    flag = AppUpdateManager.FLAG_DOWNLOAD_SUCCESS;
                    sendDownloadResult(flag, fileName);
                    //                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    //                    intent.setDataAndType(Uri.parse("file://" + response.toString()), 
                    // "application/vnd.android" + "" 
                    //                            + "" + ".package-archive");
                }

            });

        }
    }


    /**
     * 通过handle通知消息
     *
     * @param flag
     * @param fileName
     */
    private void sendDownloadResult(int flag, String fileName) {
        Message msg = new Message();
        msg.what = AppUpdateManager.MSG_DOWNLOAD_RESULT;
        Bundle bundle = new Bundle();
        bundle.putInt(AppUpdateManager.KEY_DOWNLOAD_RESULT, flag);
        bundle.putString(AppUpdateManager.KEY_FILENAME, fileName);
        msg.setData(bundle);
        handler.sendMessage(msg);
    }

    /**
     * 服务被启动时回调此方法
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //如果service进程被kill掉，保留service的状态为开始状态，但不保留递送的intent对象。
        // 随后系统会尝试重新创建service，由于服务状态为开始状态，
        // 所以创建服务后一定会调用onStartCommand(Intent,int,int)方法。
        // 如果在此期间没有任何启动命令被传递到service，那么参数Intent将为null。
        return START_STICKY;
    }


    public NotificationManager getNotificationManager() {
        return notificationManager;
    }

    /**
     * 设置handler
     *
     * @param handler
     */
    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Notification getNotification() {
        return notification;
    }
}
