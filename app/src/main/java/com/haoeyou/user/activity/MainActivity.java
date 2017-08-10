package com.haoeyou.user.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.haoeyou.user.DemoHelper;
import com.haoeyou.user.R;
import com.haoeyou.user.activity.chat.ConversationListFragment;
import com.haoeyou.user.adapter.DataCompareFragmentAdapter;
import com.haoeyou.user.base.BaseActivity;
import com.haoeyou.user.bean.AlterRequestBean;
import com.haoeyou.user.bean.Artical;
import com.haoeyou.user.bean.UploadRequestBean;
import com.haoeyou.user.bean.VersionRequestBean;
import com.haoeyou.user.bean.VersionResponseBean;
import com.haoeyou.user.common.Common;
import com.haoeyou.user.fragment.FourthFragment;
import com.haoeyou.user.fragment.MainFragment;
import com.haoeyou.user.fragment.SecondFragment;
import com.haoeyou.user.mvp.presenter.BasePresenter;
import com.haoeyou.user.mvp.presenter.MainPrasenter;
import com.haoeyou.user.mvp.view.MainView;
import com.haoeyou.user.utils.ButtonTools;
import com.haoeyou.user.utils.ImageUtils;
import com.haoeyou.user.utils.InputBroadUtils;
import com.haoeyou.user.utils.PictureUtils;
import com.haoeyou.user.utils.SharedPreferencesHelper;
import com.haoeyou.user.utils.appupdate.AppUpdateManager;
import com.haoeyou.user.utils.permission.CheckPermListener;
import com.haoeyou.user.widget.CircleImageView;
import com.haoeyou.user.widget.dialog.AlertDialog;
import com.haoeyou.user.widget.dialog.IOSDialog;
import com.haoeyou.user.widget.progressbar.NumberProgressBar;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.ui.EaseBaiduMapActivity;
import com.luck.picture.lib.model.FunctionConfig;
import com.luck.picture.lib.model.FunctionOptions;
import com.luck.picture.lib.model.PictureConfig;
import com.yalantis.ucrop.entity.LocalMedia;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainView {
    public final static String TAG = "MainActivity";
    static final String FRAGMENTS_TAG = "android:support:fragments";
    private Context mContext;
    @Bind(R.id.shouey_icon)
    ImageView mShoueyIcon;
    @Bind(R.id.shouye_tv)
    TextView mShouyeTv;
    @Bind(R.id.first_layout)
    LinearLayout mFirstLayout;
    @Bind(R.id.doctor_icon)
    ImageView mDoctorIcon;
    @Bind(R.id.doctor_tv)
    TextView mDoctorTv;
    @Bind(R.id.second_layout)
    LinearLayout mSecondLayout;
    @Bind(R.id.message_icon)
    ImageView mMessageIcon;
    @Bind(R.id.message_tv)
    TextView mMessageTv;
    @Bind(R.id.third_layout)
    LinearLayout mThirdLayout;
    @Bind(R.id.report_icon)
    ImageView mReportIcon;
    @Bind(R.id.report_tv)
    TextView mReportTv;
    @Bind(R.id.fourth_layout)
    LinearLayout mFourthLayout;
    @Bind(R.id.pager)
    ViewPager mPager;
    @Bind(R.id.message_red)
    View mRedIcon;
    private CircleImageView mHeadPic;
    private EditText mNickName;
    private TextView mBaseInfo;
    private TextView mHealth;
    private TextView mHistory;
    private TextView mExit;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNagigationView;
    private Toolbar mToolbar;
    private Button mBtn;
    private List<LocalMedia> selectMedia = new ArrayList<LocalMedia>();
    private int currentTabIndex = 0;
    private int lastIndex = 0;
    private ArrayList<Fragment> fragmentList;
    private MainFragment mMainFragment = new MainFragment();
    private SecondFragment mSecondFragment = new SecondFragment();
    private FourthFragment mFourthFragment = new FourthFragment();
    private ConversationListFragment mConversation = new ConversationListFragment();
    private ImageView[] imageArray = new ImageView[]{mShoueyIcon, mDoctorIcon, mMessageIcon, mReportIcon};
    private TextView[] tvArray = new TextView[]{mShouyeTv, mDoctorTv, mMessageTv, mReportTv};
    private boolean mode;
    private TextView mNotLogin;
    private NumberProgressBar mUpBar;
    private TextView mUpInfo;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_menu_with_toolbar;
    }

    @Override
    protected void initInjector() {
        mContext = this;
        initFragmentUI();
        initViews();
    }

    @Override
    public BasePresenter getPresenter() {
        return new MainPrasenter();
    }

    @Override
    protected void initEventAndData() {
        //注册事件总线
        if (!EventBus.getDefault().isRegistered(mContext)) {
            EventBus.getDefault().register(mContext);
        }
        if (Common.NET_STATE) {
            checkVersion();
        } else {
            showBaseMessageDialog("无网络可用,请检查网络设置!");
        }
    }

    private void initFragmentUI() {
        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(mMainFragment);
        fragmentList.add(mSecondFragment);
        fragmentList.add(mConversation);
        fragmentList.add(mFourthFragment);
        DataCompareFragmentAdapter adapter = new DataCompareFragmentAdapter(getSupportFragmentManager(), new 
                String[]{"管理", "医生", "消息", "档案"}, fragmentList);
        mPager.setAdapter(adapter);
        mPager.setOffscreenPageLimit(4);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                currentTabIndex = position;
                changeToolBarStatus(position);
                chageIconState(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化ToolBar控件
     */
    private void initViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerlayout);
        mNagigationView = (NavigationView) findViewById(R.id.id_navigationview);
        View headView = mNagigationView.inflateHeaderView(R.layout.nav_header_main);
        mHeadPic = (CircleImageView) headView.findViewById(R.id.head_pic);
        mHeadPic.setOnClickListener(click);
        mBaseInfo = (TextView) headView.findViewById(R.id.base_info);
        mBaseInfo.setOnClickListener(click);
        mHealth = (TextView) headView.findViewById(R.id.health);
        mHealth.setOnClickListener(click);
        mHistory = (TextView) headView.findViewById(R.id.history);
        mHistory.setOnClickListener(click);
        mExit = (TextView) headView.findViewById(R.id.exit);
        mExit.setOnClickListener(click);
        mNickName = (EditText) headView.findViewById(R.id.nick_name);
        mNotLogin = (TextView) headView.findViewById(R.id.notLogin);
        mNickName.setText(Common.NIKE_NAME);
        mNickName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (TextUtils.isEmpty(mNickName.getText()) || "未登录".equals(mNickName.getText()))
                    return;
                AlterRequestBean bean = new AlterRequestBean(Common.FILE_ID, mNickName.getText().toString(), Common
                        .TOKEN);
                ((MainPrasenter) mPresenter).alterNickName(mContext, new Gson().toJson(bean));
            }
        });
        headView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (InputBroadUtils.isSHowKeyboard(mContext, v)) {
                    InputBroadUtils.CloseBroadByView(mContext, v);
                    return true;
                }
                return false;
            }
        });
        mNickName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    return;
                mNickName.clearFocus();
                InputBroadUtils.CloseBroadByView(mContext, v);
            }
        });
        if (Common.IS_FIRST_LOAD || TextUtils.isEmpty(Common.TOKEN)) {
            mNickName.setVisibility(View.GONE);
            mNotLogin.setVisibility(View.VISIBLE);
            mExit.setVisibility(View.GONE);
        } else {
            mExit.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(Common.HEAD_URL).centerCrop().error(R.drawable.login_head).diskCacheStrategy
                    (DiskCacheStrategy.ALL).into(mHeadPic);
        }
    }


    @OnClick({R.id.first_layout, R.id.second_layout, R.id.third_layout, R.id.fourth_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.first_layout:
                mPager.setCurrentItem(0);
                break;
            case R.id.second_layout:
                mPager.setCurrentItem(1);
                break;
            case R.id.third_layout:
                if (Common.IS_FIRST_LOAD || TextUtils.isEmpty(Common.TOKEN)) {
                    LoginActivity.startAction(mContext, Common.ACCOUNT, MainActivity.TAG);
                    return;
                }
                mPager.setCurrentItem(2);
                break;
            case R.id.fourth_layout:
                if (Common.IS_FIRST_LOAD || TextUtils.isEmpty(Common.TOKEN)) {
                    LoginActivity.startAction(mContext, Common.ACCOUNT, MainActivity.TAG);
                    return;
                }
                mPager.setCurrentItem(3);
                break;
        }
    }

    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ButtonTools.disabledView(view, 1);
            switch (view.getId()) {
                case R.id.head_pic:
                    if (Common.IS_FIRST_LOAD || TextUtils.isEmpty(Common.TOKEN)) {
                        LoginActivity.startAction(mContext, Common.ACCOUNT, MainActivity.TAG);
                    } else {
                        showIOSDialog();
                    }
                    break;
                case R.id.base_info://我的预约
                    MyReservationActivity.startAction(mContext, MainActivity.TAG);
                    mDrawerLayout.closeDrawers();
                    break;
                case R.id.health://意见反馈
                    FeedbackActivity.startAction(mContext, MainActivity.TAG);
                    mDrawerLayout.closeDrawers();
                    break;
                case R.id.history://关于我们
                    Artical artical = new Artical("", "/frontend/about.html", "", "");
                    NewsActivity.startAction(mContext, MainActivity.TAG, artical);
                    mDrawerLayout.closeDrawers();
                    break;
                case R.id.exit://退出
                    UserExite();
                    mDrawerLayout.closeDrawers();
                    break;
            }

        }
    };

    /**
     * 版本比对
     */
    private void checkVersion() {
        String pkName = this.getPackageName();
        String versionName = "";
        try {
            versionName = this.getPackageManager().getPackageInfo(pkName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(versionName))
            return;
        ((MainPrasenter) mPresenter).checkAppVersion(mContext, new Gson().toJson(new VersionRequestBean(this
                .getPackageName(), versionName)));

    }

    private void UserExite() {
        LoginActivity.startAction(mContext, Common.ACCOUNT, MainActivity.TAG);
        Common.TOKEN = "";
        //退出登录后重新启动app,本地用户数据是为空的
        Common.ACCOUNT = "";
        Common.NIKE_NAME = "";
        Common.HEAD_URL = "";
        Common.FILE_ID = "";
        SharedPreferencesHelper.putString(getApplicationContext(), "TOKEN", "");
        SharedPreferencesHelper.putString(getApplicationContext(), "ACCOUNT", "");
        SharedPreferencesHelper.putString(getApplicationContext(), "NIKE_NAME", "");
        SharedPreferencesHelper.putString(getApplicationContext(), "HEAD_URL", "");
        SharedPreferencesHelper.putString(getApplicationContext(), "FILE_ID", "");
        mHeadPic.setImageResource(R.drawable.default_head);
        mMainFragment.getTitleBack().setImageResource(R.drawable.default_head);
        mNickName.setText("");
        mNotLogin.setVisibility(View.VISIBLE);
        mNickName.setVisibility(View.GONE);
        mExit.setVisibility(View.INVISIBLE);
        EMClient.getInstance().logout(true);
    }

    EMMessageListener messageListener = new EMMessageListener() {

        @Override
        public void onMessageReceived(List<EMMessage> messages) {
            // notify new message
            for (EMMessage message : messages) {
                DemoHelper.getInstance().getNotifier().onNewMsg(message);
            }
            refreshUIWithMessage();
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {
            refreshUIWithMessage();
        }

        @Override
        public void onMessageRead(List<EMMessage> messages) {
        }

        @Override
        public void onMessageDelivered(List<EMMessage> message) {
        }

        @Override
        public void onMessageChanged(EMMessage message, Object change) {
        }
    };

    /**
     * 切换状态栏的状态
     */
    private void changeToolBarStatus(int position) {
        switch (position) {
            case 0:
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                break;
            case 1:
            case 2:
            case 3:
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                break;
        }
    }

    public void changeDrawerLayout() {
        if (mDrawerLayout.isDrawerOpen(mNagigationView)) {
            mDrawerLayout.closeDrawers();
        } else {
            mDrawerLayout.openDrawer(mNagigationView);
        }
    }

    /**
     * 头像选择
     */
    private void selectedHeadPic() {
        // 先初始化参数配置，再启动
        FunctionOptions options = PictureUtils.getHeadPicOptions(selectMedia);
        if (mode) {
            // 只拍照
            PictureConfig.getInstance().init(options).startOpenCamera(MainActivity.this);
        } else {
            PictureConfig.getInstance().init(options).openPhoto(MainActivity.this, resultCallback);
        }
    }

    //相册图片选择回调
    private PictureConfig.OnSelectResultCallback resultCallback = new PictureConfig.OnSelectResultCallback() {
        @Override
        public void onSelectSuccess(List<LocalMedia> resultList) {

        }

        @Override
        public void onSelectSuccess(LocalMedia media) {
            if (media == null)
                return;
            Common.HEAD_URL = media.getPath();
            Bitmap bt = ImageUtils.getBitmapByPath(Common.HEAD_URL);
            if (bt != null) {
                String btString = ImageUtils.bitmapToBaseString(bt);
                upLoadDoc(new UploadRequestBean(btString, media.getPath().replace("/data/data/com.haoeyou" + "" + "" 
                        + ".user/cache/", "head_"), Common.TOKEN));
            }
        }
    };

    //単独拍照图片回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == FunctionConfig.CAMERA_RESULT) {
                if (data == null)
                    return;
                selectMedia = (List<LocalMedia>) data.getSerializableExtra(FunctionConfig.EXTRA_RESULT);
                if (selectMedia == null)
                    return;
                Common.HEAD_URL = selectMedia.get(0).getPath();
                Bitmap bt = ImageUtils.getBitmapByPath(Common.HEAD_URL);
                if (bt == null)
                    return;
                //TODO 头像上传
                String btString = ImageUtils.bitmapToBaseString(bt);
                upLoadDoc(new UploadRequestBean(btString, selectMedia.get(0).getPath().replace("/data/data/com" + "" 
                        + ".haoeyou.user/cache/", "head_"), Common.TOKEN));
            }
        }
    }

    /**
     * 上传当前用户的社交资料,
     * <br/>#更改昵称:headFileID字段传""
     * <br/>#更改头像:nickName传头像照片名
     */
    private void upLoadDoc(UploadRequestBean bean) {
        ((MainPrasenter) mPresenter).uploadFile(mContext, new Gson().toJson(bean));
    }


    /**
     * 底部导航栏的改变
     */
    private void chageIconState(int position) {
        if (lastIndex == position)
            return;
        switch (lastIndex) {
            case 0:
                mShoueyIcon.setImageResource(R.drawable.home_page_unselect);
                mShouyeTv.setTextColor(Color.parseColor("#000000"));
                break;
            case 1:
                mDoctorIcon.setImageResource(R.drawable.doctor_unselect);
                mDoctorTv.setTextColor(Color.parseColor("#000000"));
                break;
            case 2:
                mMessageIcon.setImageResource(R.drawable.message_nuselect);
                mMessageTv.setTextColor(Color.parseColor("#000000"));
                break;
            case 3:
                mReportIcon.setImageResource(R.drawable.baogao_unselect);
                mReportTv.setTextColor(Color.parseColor("#000000"));
                break;
        }
        switch (position) {
            case 0:
                mShoueyIcon.setImageResource(R.drawable.home_page_select);
                mShouyeTv.setTextColor(Color.parseColor("#3eb0ff"));
                break;
            case 1:
                mDoctorIcon.setImageResource(R.drawable.doctor_select);
                mDoctorTv.setTextColor(Color.parseColor("#3eb0ff"));
                break;
            case 2:
                mMessageIcon.setImageResource(R.drawable.message_select);
                mMessageTv.setTextColor(Color.parseColor("#3eb0ff"));
                break;
            case 3:
                mReportIcon.setImageResource(R.drawable.baogao_select);
                mReportTv.setTextColor(Color.parseColor("#3eb0ff"));
                break;
        }
        lastIndex = position;
    }

    @Override
    public void showLoadProgressDialog(String str) {

    }

    @Override
    public void disDialog() {

    }


    @Override
    public void alterFileSuccess() {
        Glide.with(mContext).load(Common.HEAD_URL).centerCrop().error(R.drawable.login_head).diskCacheStrategy
                (DiskCacheStrategy.ALL).into(mHeadPic);
        Glide.with(mContext).load(Common.HEAD_URL).centerCrop().error(R.drawable.login_head).diskCacheStrategy
                (DiskCacheStrategy.ALL).into(mMainFragment.getTitleBack());
    }

    @Override
    public void alterNikeNameSuccess() {
        Common.NIKE_NAME = mNickName.getText().toString();
        SharedPreferencesHelper.putString(getApplicationContext(), "NIKE_NAME", mNickName.getText().toString());
    }

    @Override
    public void getVersionInfo(VersionResponseBean mData) {
        if ("1".equals(mData.getCommand()) || "2".equals(mData.getCommand())) {
            AppUpdateManager.getInstance(mContext, mData.getUrl(), mData.getCommand()).downloadAPK();
        }
    }

    private void refreshUIWithMessage() {
        runOnUiThread(new Runnable() {
            public void run() {
                // refresh unread count
                updateUnreadLabel();
                if (currentTabIndex == 2) {
                    //refresh conversation list
                    if (mConversation != null) {
                        mConversation.refresh();
                    }
                }
            }
        });
    }

    /**
     * 更新未读消息数目的总数
     */
    public void updateUnreadLabel() {
        int count = getUnreadMsgCountTotal();
        if (count > 0) {
            mRedIcon.setVisibility(View.VISIBLE);
        } else {
            mRedIcon.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 获取未读消息数目
     */
    public int getUnreadMsgCountTotal() {
        int unreadMsgCountTotal = 0;
        int chatroomUnreadMsgCount = 0;
        unreadMsgCountTotal = EMClient.getInstance().chatManager().getUnreadMessageCount();
        for (EMConversation conversation : EMClient.getInstance().chatManager().getAllConversations().values()) {
            if (conversation.getType() == EMConversation.EMConversationType.ChatRoom)
                chatroomUnreadMsgCount = chatroomUnreadMsgCount + conversation.getUnreadMsgCount();
        }
        return unreadMsgCountTotal - chatroomUnreadMsgCount;
    }

    /**
     * 当初选择图片来源的dialog
     */
    private void showIOSDialog() {
        new IOSDialog(mContext).builder().setCareme("照相机", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode = true;
                selectedHeadPic();
            }
        }).setAlbum("相册", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode = false;
                selectedHeadPic();
            }
        }).setCancle("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).show();
    }


    private void showNoticeDialog() {
        new AlertDialog(mContext).setWidthRatio(0.7f).builder()/*.setTitle(getString(R.string.prompt))*/
        .hideTitleLayout().setMsg("为了更精准的健康管理," + "请到个人中心完善就诊人健康资料").setNegativeButton(("我知道了"), null).show();
    }

    @Override
    protected void onResume() {
        // unregister this event listener when this activity enters the background
        DemoHelper sdkHelper = DemoHelper.getInstance();
        sdkHelper.pushActivity(this);
        EMClient.getInstance().chatManager().addMessageListener(messageListener);
        super.onResume();
    }

    @Override
    protected void onStop() {
        EMClient.getInstance().chatManager().removeMessageListener(messageListener);
        DemoHelper sdkHelper = DemoHelper.getInstance();
        sdkHelper.popActivity(this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    /**
     * @param ct   上下文
     * @param from 从哪儿跳来的
     */
    public static void startAction(Context ct, String from) {
        Intent itt = new Intent(ct, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Common.FROM, from);
        itt.putExtras(bundle);
        ct.startActivity(itt);
    }

    @Override
    public void onBackPressed() {
        //抽屉打开的话,点击返回键为关闭
        if (mDrawerLayout.isDrawerOpen(mNagigationView)) {
            mDrawerLayout.closeDrawers();
            return;
        }
        super.onBackPressed();
    }

    // 事件总线，当登录成功后，更新ui
    @Subscribe
    public void onEventMainThread(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mNickName.setText(Common.NIKE_NAME);
                mNickName.setVisibility(View.VISIBLE);
                mNotLogin.setVisibility(View.GONE);
                mExit.setVisibility(View.VISIBLE);
                Glide.with(mContext).load(Common.HEAD_URL).centerCrop().error(R.drawable.login_head)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).into(mHeadPic);
                Glide.with(mContext).load(Common.HEAD_URL).centerCrop().error(R.drawable.login_head)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).into(mMainFragment.getTitleBack());
                if (Common.IS_FIRST_LOAD) {
                    showNoticeDialog();
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //抽屉打开的话,点击返回键为关闭
            if (mDrawerLayout.isDrawerOpen(mNagigationView)) {
                mDrawerLayout.closeDrawers();
                return true;
            }
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
