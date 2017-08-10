package com.haoeyou.user.mvp.presenter;

import android.content.Context;

import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.mvp.model.SearchModel;
import com.haoeyou.user.mvp.view.SearchView;

import java.util.ArrayList;

/**
 * Created by Mou on 2017/6/8.
 */

public class SearchPresenter extends BasePresenter<SearchView> {
    SearchModel mModel = new SearchModel();

    public void getSearchItem(Context mContext, String jsonBean) {
        mModel.getSearchItem(mContext, jsonBean, new MVPCallBack<ArrayList<String>>() {
            @Override
            public void succeed(ArrayList<String> list) {
                if (mView != null) {
                    mView.setSearchItem(list);
                }
            }

            @Override
            public void failed(String message) {

            }
        });
    }
}
