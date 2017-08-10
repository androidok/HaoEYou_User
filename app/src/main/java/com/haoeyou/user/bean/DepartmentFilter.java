package com.haoeyou.user.bean;

/**
 * Created by Mou on 2017/7/3.
 */

public class DepartmentFilter {
    private String name;
    private int groupPosition;
    private int childPosition;
    private boolean isCheck;

    public DepartmentFilter(String name, boolean isCheck, int groupPosition, int childPosition) {
        this.name = name;
        this.groupPosition = groupPosition;
        this.childPosition = childPosition;
        this.isCheck = isCheck;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroupPosition() {
        return groupPosition;
    }

    public void setGroupPosition(int groupPosition) {
        this.groupPosition = groupPosition;
    }

    public int getChildPosition() {
        return childPosition;
    }

    public void setChildPosition(int childPosition) {
        this.childPosition = childPosition;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
