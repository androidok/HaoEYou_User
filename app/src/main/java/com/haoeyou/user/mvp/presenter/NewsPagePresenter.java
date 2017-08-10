package com.haoeyou.user.mvp.presenter;

import android.content.Context;

import com.haoeyou.user.bean.Artical;
import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.mvp.model.NewsPageModel;
import com.haoeyou.user.mvp.view.NewsPageView;

import java.util.ArrayList;

/**
 * Created by Mou on 2017/7/11.
 */

public class NewsPagePresenter extends BasePresenter<NewsPageView> {
    NewsPageModel mModel = new NewsPageModel();

    public void getArticlesPage(Context context, String jsonBean) {
        mModel.getArticlesPage(context, jsonBean, new MVPCallBack<ArrayList<Artical>>() {
            @Override
            public void succeed(ArrayList<Artical> mData) {
                if (mView != null) {
                    mView.getArticlesSuccess(mData);
                }
            }

            @Override
            public void failed(String message) {
                if (mView != null) {
                    mView.getArticlesFailed();
                }
            }
        });
    }
}
