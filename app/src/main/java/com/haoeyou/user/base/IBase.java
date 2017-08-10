package com.haoeyou.user.base;


import com.haoeyou.user.mvp.presenter.BasePresenter;

public interface IBase {
    BasePresenter getPresenter();

    //    View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
    //
    //    void bindView(Bundle savedInstanceState);


    //    int getContentLayout();
}
