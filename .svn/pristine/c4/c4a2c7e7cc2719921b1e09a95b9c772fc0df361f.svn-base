package com.haoeyou.user.adapter;

import android.view.View;
import android.widget.TextView;

import com.haoeyou.user.R;

import butterknife.Bind;


/**
 * Created by Administrator on 2017/3/7.
 */
public class YearsListHolder extends BaseHolder<String> {

    @Bind(R.id.year)
    TextView mYear;

    public YearsListHolder(View view) {
        super(view);
    }

    @Override
    public void setData(String mData) {
        super.setData(mData);
        mYear.setText(mData);
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
