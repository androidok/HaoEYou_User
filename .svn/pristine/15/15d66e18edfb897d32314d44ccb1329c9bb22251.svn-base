package com.haoeyou.user.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.haoeyou.user.R;
import com.haoeyou.user.base.BaseActivity;
import com.haoeyou.user.bean.BasicRequestBean;
import com.haoeyou.user.bean.Doctors;
import com.haoeyou.user.bean.WaiterResponseBean;
import com.haoeyou.user.cache.UserCacheManager;
import com.haoeyou.user.common.Common;
import com.haoeyou.user.mvp.presenter.BasePresenter;
import com.haoeyou.user.network.DataCallback;
import com.haoeyou.user.network.NetWorking;
import com.haoeyou.user.utils.JsonFormat;
import com.haoeyou.user.widget.Constant;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.exceptions.HyphenateException;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class DoctorActivity extends BaseActivity {
    public final static String TAG = "DoctorActivity";

    @Bind(R.id.title_back)
    ImageView mTitleBack;
    @Bind(R.id.title_tv)
    TextView mTitleTv;
    @Bind(R.id.more)
    TextView mMore;
    @Bind(R.id.image_more)
    ImageView mImageMore;
    @Bind(R.id.pic)
    ImageView mPic;
    @Bind(R.id.department)
    TextView mDepartment;
    @Bind(R.id.name)
    TextView mName;
    @Bind(R.id.degree)
    TextView mDegree;
    @Bind(R.id.area)
    TextView mArea;
    @Bind(R.id.sex)
    TextView mSex;
    @Bind(R.id.number)
    TextView mNumber;
    @Bind(R.id.service_project)
    TextView mProject;
    @Bind(R.id.feat)
    TextView mFeat;
    @Bind(R.id.order)
    Button mOrderBtn;
    @Bind(R.id.content_layout)
    LinearLayout mLayout;
    @Bind(R.id.web_view)
    WebView mWebView;
    @Bind(R.id.button_layout)
    LinearLayout mButtonLayout;
    private Context mContext;
    private Doctors mDoctor;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_doctor;
    }

    @Override
    protected void initInjector() {
        mContext = this;
    }

    @Override
    protected void initEventAndData() {
        mOrderBtn.setText("立即咨询");
        //发送名篇,从聊天界面点击名片进入,隐藏
        if (ChatActivity.TAG.equals(getIntent().getExtras().getString(Common.FROM)))
            mButtonLayout.setVisibility(View.GONE);
        try {
            mDoctor = (Doctors) getIntent().getExtras().getSerializable("DOCTOR_INFO");
        } catch (Exception e) {
            mDoctor = null;
        }
        if (mDoctor == null)
            return;

        mTitleTv.setText(mDoctor.getName());
        //        Glide.with(mContext).load(Common.BASE_URL.replace("/api/", mDoctor.getImage_url())).diskCacheStrategy
        //                (DiskCacheStrategy.RESULT).into(mPic);
        //        mDepartment.setText(mDoctor.getDepartment());
        //        mName.setText(mDoctor.getName());
        //        mDegree.setText(mDoctor.getPesitionals());
        //        mArea.setText("所在地: " + mDoctor.getPlace());
        //        mSex.setText("性别: " + mDoctor.getSex());
        //        mNumber.setText(mDoctor.getCer_number());
        //        mProject.setText(mDoctor.getService_projects());
        //        mProject.setText(mDoctor.getSpecial());
        //        mLayout.setVisibility(View.VISIBLE);
        initWebView();
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @OnClick({R.id.title_back, R.id.more, R.id.image_more, R.id.order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.more:
                break;
            case R.id.image_more:
                break;
            case R.id.order:
                getWaiter();
                break;
        }
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
        webSettings.setSupportZoom(false); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(false); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存 
        webSettings.setAllowFileAccess(true); //设置可以访问文件 
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false); //支持通过JS打开新窗口 
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        webSettings.setAppCachePath(Common.PHONE_PATH + "cache"); //设置  Application Caches 缓存目录 
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);//根据cache-control决定是否从网络上取数据。
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);

            }
        });

        mWebView.loadUrl(Common.BASE_URL.replace("/api/", mDoctor.getHtmlurl()));
    }


    private void getWaiter() {
        String jsonBean = new Gson().toJson(new BasicRequestBean(Common.TOKEN));
        NetWorking.requestNetData("chat/getMyWaiter", jsonBean, new DataCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("getMyWaiter", JsonFormat.format(response));
                WaiterResponseBean bean = new Gson().fromJson(response, WaiterResponseBean.class);
                if ("1".equals(bean.getErrorCode())) {
                    UserCacheManager.save(bean.getWaiter_account(), bean.getWaiter_nickname(), Common.BASE_URL + 
                            "fileStorage/download?id=" + bean.getWaiter_headfileid() + "&token=" + Common.TOKEN);
                    Intent intent = new Intent(mContext, ChatActivity.class);
                    intent.putExtra(Constant.EXTRA_CHAT_TYPE, Constant.CHATTYPE_SINGLE);
                    //                intent.putExtra(Constant.EXTRA_USER_ID, "9f3660c0-570a-11e7-84ce-fd9ca7d34105");
                    intent.putExtra(Constant.EXTRA_USER_ID, bean.getWaiter_account());
                    startActivity(intent);
                } else {
                    showBaseMessageDialog(bean.getErrorMsg());
                }
            }
        });
    }

    /**
     * @param ct   上下文
     * @param from 从哪儿跳来的
     */
    public static void startAction(Context ct, String from, Doctors mData) {
        Intent itt = new Intent(ct, DoctorActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Common.FROM, from);
        bundle.putSerializable("DOCTOR_INFO", mData);
        itt.putExtras(bundle);
        ct.startActivity(itt);
    }

}
