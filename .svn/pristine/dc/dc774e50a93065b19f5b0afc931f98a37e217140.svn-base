package com.haoeyou.user.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haoeyou.user.R;
import com.haoeyou.user.base.BaseActivity;
import com.haoeyou.user.bean.Artical;
import com.haoeyou.user.common.Common;
import com.haoeyou.user.mvp.presenter.BasePresenter;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class NewsActivity extends BaseActivity {
    private final static String TAG = "NewsActivity";

    private Context mContext;
    @Bind(R.id.bar)
    MaterialProgressBar mBar;
    @Bind(R.id.title_back)
    ImageView mTitleBack;
    @Bind(R.id.title_tv)
    TextView mTitleTv;
    @Bind(R.id.more)
    TextView mMore;
    @Bind(R.id.image_more)
    ImageView mImageMore;
    @Bind(R.id.toolbar_layout)
    RelativeLayout mToolbarLayout;
    @Bind(R.id.new_title)
    TextView mNewTitle;
    @Bind(R.id.author)
    TextView mAuthor;
    @Bind(R.id.date)
    TextView mDate;
    @Bind(R.id.web_view)
    WebView mWebView;
    Artical mAtical;
    private boolean isOnPause = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_news;
    }

    @Override
    protected void initInjector() {
        mContext = this;
    }

    @Override
    protected void initEventAndData() {
        getIntentData();
        initWebView();
    }

    private void getIntentData() {
        mAtical = (Artical) getIntent().getExtras().getSerializable("ARTICAL");
        if (mAtical == null)
            return;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    private void initWebView() {
        //更多内容访问:http://blog.csdn.net/carson_ho/article/details/52693322
        //声明WebSettings子类
        WebSettings webSettings = mWebView.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小 
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存 
        webSettings.setAllowFileAccess(true); //设置可以访问文件 
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口 
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        webSettings.setAppCachePath(Common.PHONE_PATH + "cache"); //设置  Application Caches 缓存目录 
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);//根据cache-control决定是否从网络上取数据。
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);

            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (100 == newProgress && !isFinishing()) {
                    mBar.setVisibility(View.GONE);
                }
            }
        });

        mWebView.loadUrl(Common.BASE_URL.replace("/api/", mAtical.getOpen_htmlurl()));
    }


    @OnClick(R.id.title_back)
    public void onViewClicked() {
        finish();
    }

    /**
     * 当Activity执行onResume()时让WebView执行resume
     */
    @Override
    protected void onResume() {
        super.onResume();
        try {
            if (isOnPause) {
                if (mWebView != null) {
                    mWebView.getClass().getMethod("onResume").invoke(mWebView, (Object[]) null);
                }
                isOnPause = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        try {
            if (mWebView != null) {
                mWebView.getClass().getMethod("onPause").invoke(mWebView, (Object[]) null);
                isOnPause = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 该处的处理尤为重要:
     * 应该在内置缩放控件消失以后,再执行mWebView.destroy()
     * 否则报错WindowLeaked
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.getSettings().setBuiltInZoomControls(true);
            mWebView.setVisibility(View.GONE);
            long delayTime = ViewConfiguration.getZoomControlsTimeout();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (mWebView == null)
                                return;
                            mWebView.destroy();
                            mWebView = null;
                        }
                    });
                }
            }, delayTime);
        }
        isOnPause = false;
    }

    //点击返回上一页面而不是退出浏览器
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        mWebView.setWebChromeClient(null);
        return super.onKeyDown(keyCode, event);
    }


    /**
     * @param ct   上下文
     * @param from 从哪儿跳来的
     */
    public static void startAction(Context ct, String from, Artical atical) {
        Intent itt = new Intent(ct, NewsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Common.FROM, from);
        bundle.putSerializable("ARTICAL", atical);
        itt.putExtras(bundle);
        ct.startActivity(itt);
    }
}
