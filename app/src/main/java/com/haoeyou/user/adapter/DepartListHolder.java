package com.haoeyou.user.adapter;

import android.view.View;
import android.widget.TextView;

import com.haoeyou.user.R;
import com.haoeyou.user.bean.DepartmentResponseBean;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/7.
 */
public class DepartListHolder extends BaseHolder<DepartmentResponseBean> {
    @Bind(R.id.name)
    TextView mName;

    public DepartListHolder(View view) {
        super(view);
    }

    @Override
    public void setData(DepartmentResponseBean mData) {
        super.setData(mData);
        mName.setText(mData.getCn_name());
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
}
