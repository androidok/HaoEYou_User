package com.haoeyou.user.adapter;

import android.view.View;
import android.widget.TextView;

import com.haoeyou.user.R;
import com.haoeyou.user.bean.HealthDocState;

import butterknife.Bind;


/**
 * Created by Administrator on 2017/3/7.
 */
public class NameListHolder extends BaseHolder<HealthDocState> {

    @Bind(R.id.name)
    TextView mName;

    public NameListHolder(View view) {
        super(view);
    }

    @Override
    public void setData(HealthDocState mData) {
        super.setData(mData);
        mName.setText(mData.getName());
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
