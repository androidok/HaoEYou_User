package com.haoeyou.user.mvp.view;

import com.haoeyou.user.bean.CategoryResponseBean;

/**
 * Created by Mou on 2017/5/24.
 */

public interface CareDepartmentView extends BaseView {
    void getCategoryListSucceed(CategoryResponseBean bean);

    void getCategoryListFailed(String message);
    
    void getFilterCategoryListSucceed(CategoryResponseBean bean);

    void getFilterCategoryListFailed(String message);
}
