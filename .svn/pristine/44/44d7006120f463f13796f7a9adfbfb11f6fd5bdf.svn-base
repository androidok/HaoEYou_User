package com.haoeyou.user.mvp.presenter;

import android.content.Context;

import com.haoeyou.user.bean.CategoryResponseBean;
import com.haoeyou.user.event.MVPCallBack;
import com.haoeyou.user.mvp.model.CareDepartmentModel;
import com.haoeyou.user.mvp.view.CareDepartmentView;

/**
 * Created by Mou on 2017/5/24.
 */

public class CareDepartmentPresenter extends BasePresenter<CareDepartmentView> {
    CareDepartmentModel mModel = new CareDepartmentModel();

    public void getCategoryList(Context context, String jsonBean) {
        mModel.getCategoryList(context, jsonBean, new MVPCallBack<CategoryResponseBean>() {
            @Override
            public void succeed(CategoryResponseBean bean) {
                if (mView != null) {
                    mView.getCategoryListSucceed(bean);
                }
            }

            @Override
            public void failed(String message) {
                if (mView != null) {
                    mView.getCategoryListFailed(message);
                }
            }
        });
    }

    public void getFilterCategoryList(Context context, String jsonBean) {
        mModel.getCategoryList(context, jsonBean, new MVPCallBack<CategoryResponseBean>() {
            @Override
            public void succeed(CategoryResponseBean bean) {
                if (mView != null) {
                    mView.getFilterCategoryListSucceed(bean);
                }
            }

            @Override
            public void failed(String message) {
                if (mView != null) {
                    mView.getFilterCategoryListFailed(message);
                }
            }
        });
    }
}
