package com.haoeyou.user.mvp.view;

import com.haoeyou.user.bean.AddMedicalResponseBean;
import com.haoeyou.user.bean.AllMedicalResponseBean;

/**
 * Created by Mou on 2017/7/7.
 */

public interface HistoryView extends BaseView {
    void getMedicalHistoryListSuccesse(AllMedicalResponseBean mData);
}
