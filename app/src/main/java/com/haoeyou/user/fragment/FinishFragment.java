package com.haoeyou.user.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.haoeyou.user.R;
import com.haoeyou.user.activity.ConsultationDetailsActivity;
import com.haoeyou.user.activity.EvaluateActivity;
import com.haoeyou.user.activity.MainActivity;
import com.haoeyou.user.activity.NewsActivity;
import com.haoeyou.user.adapter.BaseRecyclerAdapter;
import com.haoeyou.user.adapter.FinishHolder;
import com.haoeyou.user.base.BaseFragment;
import com.haoeyou.user.bean.Artical;
import com.haoeyou.user.bean.BasicRequestBean;
import com.haoeyou.user.bean.OrdersResponseBean;
import com.haoeyou.user.common.Common;
import com.haoeyou.user.event.ItemListener;
import com.haoeyou.user.mvp.presenter.BasePresenter;
import com.haoeyou.user.mvp.presenter.OrderPresenter;
import com.haoeyou.user.mvp.view.OrderView;
import com.haoeyou.user.utils.ButtonTools;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by Mou on 2017/6/21.
 */

public class FinishFragment extends BaseFragment implements OrderView {
    public final static String TAG = "UnFinishFragment";
    @Bind(R.id.infor_recycle)
    RecyclerView mInfoRecycle;
    private Context mContext;
    private BaseRecyclerAdapter mAdapter;
    ArrayList<OrdersResponseBean> list = new ArrayList<>();

    @Override
    public BasePresenter getPresenter() {
        return new OrderPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_finish;
    }

    @Override
    protected void initInjector() {
        mContext = getActivity();
    }

    @Override
    protected void initEventAndData() {
        initRecycleView();
    }

    @Override
    protected void lazyLoadData() {
        BasicRequestBean bean = new BasicRequestBean("已完成", Common.TOKEN);
        String jsonBean = new Gson().toJson(bean);
        ((OrderPresenter) mPresenter).getOrderList(mContext, jsonBean.replace("targetAccount", "filter"));
    }

    public void initRecycleView() {
        LinearLayoutManager mgr = new LinearLayoutManager(mContext);
        mgr.setOrientation(LinearLayoutManager.VERTICAL);
        mInfoRecycle.setLayoutManager(mgr);
        mInfoRecycle.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = 20;
            }
        });
        setAdapter();
    }

    public void setAdapter() {
        if (mInfoRecycle == null)
            return;
        if (mAdapter == null) {
            mAdapter = new BaseRecyclerAdapter(mContext, list, R.layout.adapter_finish_item, FinishHolder.class, new 
                    ItemListener<OrdersResponseBean>() {
                @Override
                public void onItemClick(View view, int position, OrdersResponseBean mData) {
                    ButtonTools.disabledView(view, 1);
                    if (view instanceof TextView) {
                        Intent itt = new Intent(mContext, EvaluateActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("order_id", mData.getOrder_id());
                        itt.putExtras(bundle);
                        startActivityForResult(itt, 100);
                    } else {
                        Artical artical = new Artical("", mData.getDetail_url(), "", "");
                        NewsActivity.startAction(mContext, MainActivity.TAG, artical);
                        //ConsultationDetailsActivity.startAction(mContext, FinishFragment.TAG);
                    }

                }
            });
            mInfoRecycle.setAdapter(mAdapter);
        }
    }

    @Override
    public void showLoadProgressDialog(String str) {

    }

    @Override
    public void disDialog() {

    }

    @Override
    public void getOrderListSuccess(ArrayList<OrdersResponseBean> mData) {
        if (mData == null || mData.size() == 0)
            return;
        list.clear();
        list.addAll(mData);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void getOrderListFailed(String message) {

    }

    @Override
    public void cancleOrderSuccess() {

    }

    @Override
    public void cancleOrderFailed() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1)
            lazyLoadData();
    }
}
