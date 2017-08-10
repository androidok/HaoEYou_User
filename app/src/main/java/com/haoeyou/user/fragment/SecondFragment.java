package com.haoeyou.user.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.haoeyou.user.R;
import com.haoeyou.user.activity.DoctorActivity;
import com.haoeyou.user.activity.SearchActivity;
import com.haoeyou.user.adapter.BaseRecyclerAdapter;
import com.haoeyou.user.adapter.DepartListHolder;
import com.haoeyou.user.adapter.DoctorListHolder;
import com.haoeyou.user.adapter.InfoListHolder;
import com.haoeyou.user.base.BaseFragment;
import com.haoeyou.user.bean.DepartmentResponseBean;
import com.haoeyou.user.bean.DoctorRequestBean;
import com.haoeyou.user.bean.DoctorResponseBean;
import com.haoeyou.user.bean.Doctors;
import com.haoeyou.user.bean.HomeDataResponse;
import com.haoeyou.user.event.EndlessRecyclerOnScrollListener;
import com.haoeyou.user.event.ItemListener;
import com.haoeyou.user.mvp.presenter.BasePresenter;
import com.haoeyou.user.mvp.presenter.SecondPresenter;
import com.haoeyou.user.mvp.view.SecondFragmentView;
import com.haoeyou.user.utils.ButtonTools;
import com.haoeyou.user.utils.SharedPreferencesHelper;
import com.hyphenate.util.NetUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类名: {@link SecondFragment}
 * <br/> 功能描述:
 * <br/> 作者: MouTao
 * <br/> 时间: 2017/5/18
 * <br/> 最后修改者:
 * <br/> 最后修改内容:
 */
public class SecondFragment extends BaseFragment implements SecondFragmentView {
    public final static String TAG = "SecondFragment";
    private Context mContext;
    @Bind(R.id.title_back)
    ImageView mTitleBack;
    @Bind(R.id.image_more)
    ImageView mImageMore;
    @Bind(R.id.title_tv)
    TextView mTitleTv;
    @Bind(R.id.more)
    TextView mMore;
    @Bind(R.id.item_name)
    TextView mFilterView;
    @Bind(R.id.toolbar_layout)
    RelativeLayout mToolbarLayout;
    @Bind(R.id.select_icon)
    ImageView mSelectIcon;
    @Bind(R.id.select_layout)
    FrameLayout mSelectLayout;
    @Bind(R.id.doctor_recycle)
    RecyclerView mDoctorRecycle;
    @Bind(R.id.department_recycle)
    RecyclerView mDepartmentRecycle;
    @Bind(R.id.nothing)
    View mNothing;
    @Bind(R.id.department_select)
    LinearLayout mDepartmentLayout;
    @Bind(R.id.refreshLayout)
    RefreshLayout mRefreshLayout;
    private BaseRecyclerAdapter mDoctorAdapter;
    private BaseRecyclerAdapter mDepartmentAdapter;
    ArrayList<Doctors> doctorList = new ArrayList<Doctors>();
    ArrayList<DepartmentResponseBean> departList = new ArrayList<DepartmentResponseBean>();
    private String filter = "";
    private int page = 1;
    private boolean isLoadingMore = false;

    @Override
    public BasePresenter getPresenter() {
        return new SecondPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_second;
    }

    @Override
    protected void initEventAndData() {
        initWidget();
        initRefresh();
        initDoctroRecyclerView();
        initDeparmentRecyclerView();
    }

    @Override
    protected void lazyLoadData() {
        if (NetUtils.hasNetwork(mContext)) {
            requestDoctorList();
            requestDepartmentList();
        } else {
            String response = SharedPreferencesHelper.getString(mContext, "DOCTOR_DATA", "");
            if (TextUtils.isEmpty(response))
                return;
            DoctorResponseBean bean = new Gson().fromJson(response, DoctorResponseBean.class);
            getDoctorPageSuccess(bean.getDoctors());
        }
    }

    @Override
    protected void initInjector() {
        mContext = getActivity();
    }

    @OnClick({R.id.title_back, R.id.more, R.id.image_more, R.id.select_layout, R.id.nothing})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                break;
            case R.id.image_more:
                SearchActivity.startAction(mContext, SecondFragment.TAG);
                break;
            case R.id.select_layout:
                if (departList.size() == 0)
                    return;
                if (mDepartmentLayout.getVisibility() == View.INVISIBLE) {
                    mDepartmentLayout.setVisibility(View.VISIBLE);
                    mSelectIcon.setImageResource(R.drawable.doctor_up);
                } else {
                    mSelectIcon.setImageResource(R.drawable.doctor_down);
                    mDepartmentLayout.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.nothing:
                mDepartmentLayout.setVisibility(View.INVISIBLE);
                mSelectIcon.setImageResource(R.drawable.doctor_down);
                break;
        }
    }

    private void initWidget() {
        mTitleTv.setText("医生");
        mFilterView.setText("全部");
        mMore.setVisibility(View.INVISIBLE);
        mTitleBack.setVisibility(View.INVISIBLE);
        mFilterView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                page = 1;
            }
        });
    }

    private void initRefresh() {
        mRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                isLoadingMore = true;
                page++;
                requestDoctorList();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                isLoadingMore = false;
                page = 1;
                requestDoctorList();
            }
        });
    }


    private void initDoctroRecyclerView() {
        LinearLayoutManager mgrDoctor = new LinearLayoutManager(mContext);
        mgrDoctor.setOrientation(LinearLayoutManager.VERTICAL);
        mDoctorRecycle.setLayoutManager(mgrDoctor);
        setDoctorAdapter();
    }

    private void initDeparmentRecyclerView() {
        LinearLayoutManager mgrDepart = new LinearLayoutManager(mContext);
        mgrDepart.setOrientation(LinearLayoutManager.VERTICAL);
        mDepartmentRecycle.setLayoutManager(mgrDepart);
        mDepartmentRecycle.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        setDepartmentAdapter();
    }

    private void requestDepartmentList() {
        ((SecondPresenter) mPresenter).getStandardDepartments(mContext);
    }

    private void requestDoctorList() {
        if (departList.size() == 0)
            requestDepartmentList();
        String jsonBean = new Gson().toJson(new DoctorRequestBean(filter, String.valueOf(page), String.valueOf(page 
                == 1 ? 20 : 10)));
        ((SecondPresenter) mPresenter).getDoctorsPage(mContext, jsonBean);
    }

    public void setDoctorAdapter() {
        if (mDoctorRecycle == null)
            return;
        if (mDoctorAdapter == null) {
            mDoctorAdapter = new BaseRecyclerAdapter(mContext, doctorList, R.layout.adapter_doctor_item, 
                    DoctorListHolder.class, new ItemListener<Doctors>() {
                @Override
                public void onItemClick(View view, int position, Doctors mData) {
                    ButtonTools.disabledView(view, 1);
                    DoctorActivity.startAction(mContext, SecondFragment.TAG, doctorList.get(position));
                }
            });
            mDoctorRecycle.setAdapter(mDoctorAdapter);
        }
    }

    public void setDepartmentAdapter() {
        if (mDepartmentRecycle == null)
            return;
        if (mDepartmentAdapter == null) {
            mDepartmentAdapter = new BaseRecyclerAdapter(mContext, departList, R.layout.adapter_depart_item, 
                    DepartListHolder.class, new ItemListener<DepartmentResponseBean>() {
                @Override
                public void onItemClick(View view, int position, DepartmentResponseBean mData) {
                    mSelectIcon.setImageResource(R.drawable.doctor_down);
                    mDepartmentLayout.setVisibility(View.INVISIBLE);
                    mFilterView.setText(mData.getCn_name());
                    if (0 == position) {
                        filter = "";
                    } else {
                        filter = mData.getCn_name();
                    }
                    isLoadingMore = false;
                    requestDoctorList();
                }
            });
            mDepartmentRecycle.setAdapter(mDepartmentAdapter);
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
    public void getDoctorPageSuccess(ArrayList<Doctors> mData) {
        mRefreshLayout.finishLoadmore(1000);
        mRefreshLayout.finishRefresh(1000);
        if (mData == null || mData.size() == 0) {
            page--;
            return;
        }
        if (doctorList.size() != 0 && !isLoadingMore)
            doctorList.clear();
        doctorList.addAll(mData);
        mDoctorAdapter.notifyDataSetChanged();
    }

    @Override
    public void getStandardDepartmentsSuccess(ArrayList<DepartmentResponseBean> mData) {
        if (mData == null || mData.size() == 0) {
            return;
        }
        if (departList.size() == 0)
            departList.add(new DepartmentResponseBean("全部", "all"));
        departList.addAll(mData);
        mDepartmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void getDoctorPageFailed() {
        mRefreshLayout.finishLoadmore(1000, false);
        mRefreshLayout.finishRefresh(1000, false);
    }
}
