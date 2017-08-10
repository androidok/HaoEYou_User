package com.haoeyou.user.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.haoeyou.user.R;
import com.haoeyou.user.bean.CategoryResponseBean;
import com.haoeyou.user.bean.DepartmentFilter;
import com.haoeyou.user.bean.categoryList;
import com.haoeyou.user.widget.smoothcheck.SmoothCheckBox;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/7.
 */
public class FilterHolder extends BaseHolder<DepartmentFilter> {

    @Bind(R.id.iv_state)
    ImageView mIvState;
    @Bind(R.id.checkbox)
    SmoothCheckBox mCheckbox;
    @Bind(R.id.line)
    View mLine;
    @Bind(R.id.tv_expandable_header)
    TextView mText;

    public FilterHolder(View view) {
        super(view);
        mIvState.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setData(DepartmentFilter mData) {
        super.setData(mData);
        mText.setText(mData.getName());
        mCheckbox.setChecked(mData.isCheck());
        mCheckbox.setOnCheckedChangeListener(null);
    }

    @Override
    public void init() {
        super.init();
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCheckbox.setChecked(!mCheckbox.isChecked());
                listener.onItemClick(v, getPosition(), mData);
            }
        });
    }
}
