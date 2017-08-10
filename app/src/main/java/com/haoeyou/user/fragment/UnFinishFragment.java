package com.haoeyou.user.fragment;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.haoeyou.user.R;
import com.haoeyou.user.activity.ConsultationDetailsActivity;
import com.haoeyou.user.activity.MainActivity;
import com.haoeyou.user.activity.NewsActivity;
import com.haoeyou.user.activity.SimpleDetailsActivity;
import com.haoeyou.user.adapter.BaseRecyclerAdapter;
import com.haoeyou.user.adapter.UnfinishHolder;
import com.haoeyou.user.base.BaseFragment;
import com.haoeyou.user.bean.Artical;
import com.haoeyou.user.bean.BasicRequestBean;
import com.haoeyou.user.bean.JsonBean;
import com.haoeyou.user.bean.OrdersResponseBean;
import com.haoeyou.user.common.Common;
import com.haoeyou.user.event.ItemListener;
import com.haoeyou.user.mvp.presenter.BasePresenter;
import com.haoeyou.user.mvp.presenter.OrderPresenter;
import com.haoeyou.user.mvp.view.OrderView;
import com.haoeyou.user.widget.dialog.AlertDialog;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by Mou on 2017/6/21.
 */

public class UnFinishFragment extends BaseFragment implements OrderView {
    public final static String TAG = "UnFinishFragment";
    @Bind(R.id.infor_recycle)
    RecyclerView mInfoRecycle;
    private Context mContext;
    private BaseRecyclerAdapter mAdapter;
    ArrayList<OrdersResponseBean> list = new ArrayList<>();
    public int flagPosition;

    @Override
    public BasePresenter getPresenter() {
        return new OrderPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_unfinish;
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
        BasicRequestBean bean = new BasicRequestBean("未完成", Common.TOKEN);
        String jsonBean = new Gson().toJson(bean);
        ((OrderPresenter) mPresenter).getOrderList(mContext, jsonBean.replace("targetAccount", "filter"));
    }

    public void initRecycleView() {
        LinearLayoutManager mgr = new LinearLayoutManager(mContext);
        mgr.setOrientation(LinearLayoutManager.VERTICAL);
        mInfoRecycle.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = 20;
            }
        });
        mInfoRecycle.setLayoutManager(mgr);
        setAdapter();
    }

    public void setAdapter() {
        if (mInfoRecycle == null)
            return;
        if (mAdapter == null) {
            mAdapter = new BaseRecyclerAdapter(mContext, list, R.layout.adapter_order_item, UnfinishHolder.class, new
                    ItemListener<OrdersResponseBean>() {
                @Override
                public void onItemClick(View view, int position, OrdersResponseBean mData) {
                    if (view instanceof TextView) {
                        initConfirmDialog(position);
                    } else {
                        Artical artical = new Artical("", mData.getDetail_url(), "", "");
                        NewsActivity.startAction(mContext, MainActivity.TAG, artical);
                    }
                }
            });
            mInfoRecycle.setAdapter(mAdapter);
        }
    }

    private void initConfirmDialog(final int position) {
        new AlertDialog(mContext).setMessageGravity(Gravity.CENTER).builder().setMsg("是否删除订单").setNegativeButton
                ("确定", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                flagPosition = position;
                deleteOrders();
            }
        }).setPositiveButton("取消", null).show();
    }

    private void deleteOrders() {
        String jsonBean = new Gson().toJson(new BasicRequestBean(list.get(flagPosition).getOrder_id(), Common.TOKEN));
        ((OrderPresenter) mPresenter).cancleOrder(mContext, jsonBean.replace("targetAccount", "order_id"));
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
    public void getOrderListSuccess(ArrayList<OrdersResponseBean> mData) {
        if (mData == null || mData.size() == 0)
            return;
        list.clear();
        list.addAll(mData);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void getOrderListFailed(String message) {
        if (!TextUtils.isEmpty(message))
            showBaseMessageDialog(message);
    }

    @Override
    public void cancleOrderSuccess() {
        list.remove(flagPosition);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void cancleOrderFailed() {

    }
}
