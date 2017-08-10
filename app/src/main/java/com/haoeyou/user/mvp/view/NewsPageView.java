package com.haoeyou.user.mvp.view;

import com.haoeyou.user.bean.Artical;

import java.util.ArrayList;

/**
 * Created by Mou on 2017/7/11.
 */

public interface NewsPageView extends BaseView {
    void getArticlesSuccess(ArrayList<Artical> mData);

    void getArticlesFailed();
}
