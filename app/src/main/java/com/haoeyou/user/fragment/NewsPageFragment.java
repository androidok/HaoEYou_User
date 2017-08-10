package com.haoeyou.user.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.google.gson.Gson;
import com.haoeyou.user.R;
import com.haoeyou.user.activity.NewsActivity;
import com.haoeyou.user.adapter.BaseRecyclerAdapter;
import com.haoeyou.user.adapter.InfoListHolder;
import com.haoeyou.user.base.BaseFragment;
import com.haoeyou.user.bean.Artical;
import com.haoeyou.user.bean.TagNwesRequestBean;
import com.haoeyou.user.event.ItemListener;
import com.haoeyou.user.mvp.presenter.BasePresenter;
import com.haoeyou.user.mvp.presenter.NewsPagePresenter;
import com.haoeyou.user.mvp.view.NewsPageView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * ViewPager页面
 * Created by Moutao on 2016-08-22.
 */
public class NewsPageFragment extends BaseFragment implements NewsPageView {
    private String titleTag;
    private Context mContext;

    @Bind(R.id.infor_recycle)
    RecyclerView mInfoRecycle;
    @Bind(R.id.refreshLayout)
    RefreshLayout mRefreshLayout;

    private BaseRecyclerAdapter mAdapter;
    private int page = 1;
    private ArrayList<Artical> articleList = new ArrayList<>();
    private boolean isLoadingMore = false;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_self_news;
    }

    @Override
    public BasePresenter getPresenter() {
        return new NewsPagePresenter();
    }

    @Override
    protected void initInjector() {
        mContext = getActivity();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            titleTag = bundle.getString("TITLE_TAG");
        }
    }

    @Override
    protected void initEventAndData() {
        initRefresh();
        initRecycleView();
    }


    @Override
    protected void lazyLoadData() {
        requestArticles();
    }

    private void requestArticles() {
        String jsonBean = new Gson().toJson(new TagNwesRequestBean(titleTag, String.valueOf(page), String.valueOf
                (page == 1 ? 20 : 10)));
        ((NewsPagePresenter) mPresenter).getArticlesPage(mContext, jsonBean);
    }

    private void initRefresh() {
        mRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                isLoadingMore = true;
                page++;
                requestArticles();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                isLoadingMore = false;
                page = 1;
                requestArticles();
            }
        });
    }

    public void initRecycleView() {
        LinearLayoutManager mgr = new LinearLayoutManager(mContext);
        mgr.setOrientation(LinearLayoutManager.VERTICAL);
        mInfoRecycle.setLayoutManager(mgr);
        mInfoRecycle.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        setAdapter();
    }

    public void setAdapter() {
        if (mInfoRecycle == null)
            return;
        if (mAdapter == null) {
            mAdapter = new BaseRecyclerAdapter(mContext, articleList, R.layout.adapter_info_item, InfoListHolder
                    .class, new ItemListener<Artical>() {
                @Override
                public void onItemClick(View view, int position, Artical mData) {
                    NewsActivity.startAction(mContext, MainFragment.TAG, mData);
                }
            });
            mInfoRecycle.setAdapter(mAdapter);
        }
    }

    @Override
    public void showLoadProgressDialog(String str) {
        showLoading("");
    }

    @Override
    public void disDialog() {
        dissLoadDialog();
    }

    @Override
    public void getArticlesSuccess(ArrayList<Artical> mData) {
        mRefreshLayout.finishLoadmore(1000);
        mRefreshLayout.finishRefresh(1000);
        if (mData == null || mData.size() == 0)
            return;
        if (articleList.size() != 0 && !isLoadingMore)
            articleList.clear();
        articleList.addAll(mData);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void getArticlesFailed() {
        mRefreshLayout.finishLoadmore(1000, false);
        mRefreshLayout.finishRefresh(1000, false);
    }
}