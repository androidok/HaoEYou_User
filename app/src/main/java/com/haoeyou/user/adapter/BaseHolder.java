package com.haoeyou.user.adapter;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.haoeyou.user.event.ItemListener;

import butterknife.ButterKnife;

public abstract class BaseHolder<T> extends RecyclerView.ViewHolder {
    public View mView;
    public T mData;
    public int position;
    public Context mContext;
    public ItemListener listener;

    public View getView() {
        return mView;
    }

    public BaseHolder(View view) {
        super(view);
        this.mView = view;
        ButterKnife.bind(this, mView);
        init();
    }


    public void init() {

    }

    public void setData(T mData) {
        this.mData = mData;
    }


    public void setPosition(int position) {
        this.position = position;
    }

    public void setData(int position, T mData) {
        this.mData = mData;
    }

    public void setListener(ItemListener listener) {
        this.listener = listener;
    }

    public void setContext(Context context) {
        mContext = context;
    }
}