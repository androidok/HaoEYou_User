package com.haoeyou.user.mvp.view;

import com.haoeyou.user.bean.DepartmentResponseBean;
import com.haoeyou.user.bean.Doctors;

import java.util.ArrayList;

/**
 * Created by Mou on 2017/6/1.
 */

public interface SecondFragmentView extends BaseView {
    void getDoctorPageSuccess(ArrayList<Doctors> mData);

    void getStandardDepartmentsSuccess(ArrayList<DepartmentResponseBean> mData);

    void getDoctorPageFailed();
}
