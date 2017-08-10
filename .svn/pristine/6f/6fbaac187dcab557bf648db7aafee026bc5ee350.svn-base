package com.haoeyou.user.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.haoeyou.user.R;
import com.haoeyou.user.common.AppManager;
import com.haoeyou.user.common.Common;
import com.haoeyou.user.mvp.presenter.BasePresenter;
import com.haoeyou.user.mvp.view.BaseView;
import com.haoeyou.user.utils.StatusBarUtil;
import com.haoeyou.user.utils.ToastUtils;
import com.haoeyou.user.utils.permission.CheckPermListener;
import com.haoeyou.user.utils.permission.EasyPermissions;
import com.haoeyou.user.utils.permission.PermissionCallbacks;
import com.haoeyou.user.widget.dialog.AlertDialog;
import com.haoeyou.user.widget.dialog.ProgressDialog;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;

import java.util.List;

import butterknife.ButterKnife;
import me.naturs.library.statusbar.StatusBarHelper;


public abstract class BaseActivity<T extends BasePresenter<BaseView>> extends AppCompatActivity implements IBase, 
        PermissionCallbacks {
    /**
     * 权限回调接口
     */
    private CheckPermListener mPermissionListener;
    protected static final int RC_PERM = 123;
    public BasePresenter mPresenter;
    // public RxManager mRxManager;
    protected StatusBarHelper mStatusBarHelper;
    /**
     * 传入标示符
     */
    public String fromTag;
    //    private LoadProgressDialog progressDialog;
    public static boolean isNewCar = false;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        // mRxManager = new RxManager();
        //setBaseConfig();
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        //onTintStatusBar();
        mPresenter = getPresenter();
        initInjector();
        if (mPresenter != null && this instanceof BaseView) {
            mPresenter.attach((BaseView) this);
        }
        onSaveState(savedInstanceState);
        //注册一个监听连接状态的listener
        initEventAndData();
    }

    public void onSaveState(Bundle savedInstanceState) {
    }


    private void setBaseConfig() {
        initTheme();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //   setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //          SetStatusBarColor();
        //        SetStatusBarColor(R.color.colorAccent);
    }

    public void onTintStatusBar() {
        if (mStatusBarHelper == null) {
            mStatusBarHelper = new StatusBarHelper(this, StatusBarHelper.LEVEL_19_TRANSLUCENT, StatusBarHelper
                    .LEVEL_21_VIEW);
        }
        //        //状态栏的设置
        mStatusBarHelper.setColor(getResources().getColor(R.color.tool_color));
    }

    /**
     * 获取布局
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化数据
     */
    protected abstract void initInjector();

    /**
     * 设置监听
     */
    protected abstract void initEventAndData();

    private void initTheme() {
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor() {
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.colorAccent));
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    public void SetStatusBarColor(int color) {
        StatusBarUtil.setStatusBarColor(this, color);
    }

    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected void SetTranslanteBar() {
        StatusBarUtil.translucentStatusBar(this, false);
    }

    /**
     * 是否需要注册网络变化的Observer,如果不需要监听网络变化,则返回false;否则返回true.默认返回false
     */
    protected boolean needRegisterNetworkChangeObserver() {
        return false;
    }

    public void initToolBarConfig() {
        View decorView = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上  
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            decorView.setFitsSystemWindows(true);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0  
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }


    public void showLoading(String string) {
        progressDialog = ProgressDialog.getInstance(this);
        progressDialog.setMessage(TextUtils.isEmpty(string) ? "加载中..." : string);
        progressDialog.show();
    }

    public void dissLoadDialog() {
        if (progressDialog != null) {
            progressDialog.dismissHUD();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onDestroy() {

        if (mPresenter != null && this instanceof BaseView) {
            mPresenter.detachView();
            mPresenter = null;
        }
        //mRxManager.clear();
        ButterKnife.unbind(this);
        AppManager.getAppManager().finishActivity(this);
        super.onDestroy();
    }

    public void showToast(String message) {
        ToastUtils.showToast(BaseActivity.this, message);
    }

    public void showToast(int ResID) {
        ToastUtils.showToast(BaseActivity.this, ResID);
    }


    public void showBaseMessageDialog(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog(BaseActivity.this).setWidthRatio(0.7f).setMessageGravity(Gravity.CENTER).builder()
                        .hideTitleLayout().setMsg(message).setNegativeButton(("确 定"), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                }).show();
            }
        });
    }

    public void checkPermission(CheckPermListener listener, int resString, String... mPerms) {
        mPermissionListener = listener;
        if (EasyPermissions.hasPermissions(this, mPerms)) {
            if (mPermissionListener != null)
                mPermissionListener.superPermission();
        } else {
            EasyPermissions.requestPermissions(this, getString(resString), RC_PERM, mPerms);
        }
    }

    /**
     * 用户权限处理,
     * 如果全部获取, 则直接过.
     * 如果权限缺失, 则提示Dialog.
     *
     * @param requestCode  请求码
     * @param permissions  权限
     * @param grantResults 结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] 
            grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        //同意了某些权限可能不是全部
    }

    @Override
    public void onPermissionsAllGranted() {
        if (mPermissionListener != null)
            mPermissionListener.superPermission();//同意了全部权限的回调
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        EasyPermissions.checkDeniedPermissionsNeverAskAgain(this, getString(R.string.perm_tip), R.string.setting, R
                .string.cancel, null, perms);
    }
}
