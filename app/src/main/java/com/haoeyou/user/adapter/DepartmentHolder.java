package com.haoeyou.user.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haoeyou.user.R;
import com.haoeyou.user.widget.expandview.ExpandableLayout;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/7.
 */
public class DepartmentHolder extends BaseHolder<String> {


    @Bind(R.id.item)
    TextView mItem;
    @Bind(R.id.icon)
    ImageView mIcon;
    @Bind(R.id.title_layout)
    LinearLayout mTitleLayout;
    @Bind(R.id.son_recycler)
    RecyclerView mSonRecycler;
    @Bind(R.id.expand)
    ExpandableLayout mExpand;

    public DepartmentHolder(View view) {
        super(view);
    }

    @Override
    public void setData(String mData) {
        super.setData(mData);
        mItem.setText(mData);
    }

    @Override
    public void init() {
        super.init();
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, getPosition(), mData);
            }
        });
    }

    @OnClick(R.id.icon)
    public void onViewClicked() {
        setLayout(mExpand, mIcon);
    }

    /**
     * 设置界面展开与关闭
     *
     * @param expLay   需要展开的界面
     * @param imageTip 按钮状态
     */
    private void setLayout(ExpandableLayout expLay, ImageView imageTip) {
        if (expLay.isExpanded()) {
            expLay.collapse();
            imageTip.setImageResource(R.drawable.drop_down);
        } else {
            expLay.expand();
            imageTip.setImageResource(R.drawable.drop_up);
        }
    }

}
