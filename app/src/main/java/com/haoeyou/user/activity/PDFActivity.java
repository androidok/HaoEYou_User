package com.haoeyou.user.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.haoeyou.user.R;
import com.haoeyou.user.base.BaseActivity;
import com.haoeyou.user.common.Common;
import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.mvp.presenter.BasePresenter;
import com.haoeyou.user.utils.permission.CheckPermListener;
import com.haoeyou.user.utils.permission.EasyPermissions;
import com.shockwave.pdfium.PdfDocument;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.util.List;
import java.util.jar.Manifest;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import okhttp3.Call;

public class PDFActivity extends BaseActivity implements OnPageChangeListener, OnLoadCompleteListener {
    private final static String TAG = "PDFActivity";
    private final static int REQUEST_CODE = 42;
    public static final int PERMISSION_CODE = 42042;

    public static final String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";
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
    @Bind(R.id.pdfView)
    PDFView pdfView;
    @Bind(R.id.bar)
    MaterialProgressBar mBar;
    private Context mContext;
    Integer pageNumber = 0;

    String pdfFileName = "file.pdf";
    public static final String SAMPLE_FILE = "sample.pdf";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pdf;
    }

    @Override
    protected void initInjector() {
        mContext = this;
    }

    @Override
    protected void initEventAndData() {
        mTitleTv.setText("正在下载");
        pdfView.setEnabled(false);

        checkPermission(new CheckPermListener() {
            @Override
            public void superPermission() {
                downLoadPDF();
            }
        }, R.string.need_read_write, android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission
                .WRITE_EXTERNAL_STORAGE);
        //        File file = new File(Common.PHONE_PATH + "PDF/file.pdf");
        //        DISPLAYFROMURI(FILE);
        //        displayFromAsset(SAMPLE_FILE);
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @OnClick(R.id.title_back)
    public void onViewClicked() {
        finish();
    }

    private void downLoadPDF() {
        mBar.setVisibility(View.VISIBLE);
        OkHttpUtils//
                .get()//
                .url("http://app.haoeyou.com:8080/frontend/pdf/report1.pdf").tag(this)//
                .build()//
                .execute(new FileCallBack(Common.PHONE_PATH + "PDF/", "file.pdf")//
                {
                    @Override
                    public void inProgress(float progress, long total, int id) {
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (isFinishing())
                            return;
                        showToast("下载失败");
                        mTitleTv.setText("下载失败");
                    }

                    @Override
                    public void onResponse(File response, int id) {
                        mTitleTv.setText("XXX报告");
                        showToast("下载成功");
                        mBar.setVisibility(View.GONE);
                        Uri uri = Uri.parse(response.getAbsolutePath());
                        if (uri == null)
                            return;
                        displayFromUri(response);
                    }

                });
    }

    private void displayFromAsset(String assetFileName) {
        pdfFileName = assetFileName;
        pdfView.fromAsset(SAMPLE_FILE).defaultPage(pageNumber).onPageChange(this).enableAnnotationRendering(true)
                .onLoad(this).scrollHandle(new DefaultScrollHandle(this)).spacing(10) // in dp
                .load();
    }

    private void displayFromUri(File uri) {
        //        pdfFileName = getFileName(uri);
        pdfView.fromFile(uri).defaultPage(pageNumber).onPageChange(this).enableAnnotationRendering(true).onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this)).spacing(10) // in dp
                .load();
    }


    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if (result == null) {
            result = uri.getLastPathSegment();
        }
        return result;
    }

    @Override
    public void loadComplete(int nbPages) {
        pdfView.setEnabled(true);
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
//        Log.e(TAG, "title = " + meta.getTitle());
//        Log.e(TAG, "author = " + meta.getAuthor());
//        Log.e(TAG, "subject = " + meta.getSubject());
//        Log.e(TAG, "keywords = " + meta.getKeywords());
//        Log.e(TAG, "creator = " + meta.getCreator());
//        Log.e(TAG, "producer = " + meta.getProducer());
//        Log.e(TAG, "creationDate = " + meta.getCreationDate());
//        Log.e(TAG, "modDate = " + meta.getModDate());

        printBookmarksTree(pdfView.getTableOfContents(), "-");
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        //        showToast(page);
        //        setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));

    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }

    public static void startAction(Context ct, String from) {
        Intent itt = new Intent(ct, PDFActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Common.FROM, from);
        itt.putExtras(bundle);
        ct.startActivity(itt);
    }

    @Override
    protected void onDestroy() {
        OkHttpUtils.getInstance().cancelTag(this);
        super.onDestroy();
    }

}
