package com.haoeyou.user.mvp.presenter;

import android.content.Context;

import com.haoeyou.user.bean.TagTitleResponseBean;
import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.mvp.model.NewsGroupModel;
import com.haoeyou.user.mvp.view.NewsGroupView;

/**
 * Created by Mou on 2017/7/11.
 */

public class NewsGroupPresenter extends BasePresenter<NewsGroupView> {
    NewsGroupModel mModel = new NewsGroupModel();

    public void getArticleTags(Context context) {
        mModel.getArticleTags(context, new MVPCallBack<TagTitleResponseBean>() {
            @Override
            public void succeed(TagTitleResponseBean mData) {
                if (mView != null) {
                    mView.getArticleTagsSuccess(mData);
                }
            }

            @Override
            public void failed(String message) {

            }
        });
    }
}
