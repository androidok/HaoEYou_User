package com.haoeyou.user.mvp.model;


import com.haoeyou.user.bean.CategoryResponseBean;
import com.haoeyou.user.entity.ChildEntity;
import com.haoeyou.user.entity.ExpandableGroupEntity;
import com.haoeyou.user.entity.GroupEntity;

import java.util.ArrayList;

/**
 * Depiction:
 * Author: teach
 * Date: 2017/3/20 15:51
 */
public class GroupModel {

    /**
     * 获取组列表数据
     *
     * @param groupCount    组数量
     * @param childrenCount 每个组里的子项数量
     * @return
     */
    public static ArrayList<GroupEntity> getGroups(int groupCount, int childrenCount) {
        ArrayList<GroupEntity> groups = new ArrayList<>();
        for (int i = 0; i < groupCount; i++) {
            ArrayList<ChildEntity> children = new ArrayList<>();
            for (int j = 0; j < childrenCount; j++) {
                children.add(new ChildEntity("第" + (i + 1) + "组第" + (j + 1) + "项"));
            }
            groups.add(new GroupEntity("第" + (i + 1) + "组头部", "第" + (i + 1) + "组尾部", children));
        }
        return groups;
    }

    /**
     * 获取可展开收起的组列表数据(默认展开)
     *
     * @param groupCount    组数量
     * @param childrenCount 每个组里的子项数量
     * @return
     */
    public static ArrayList<ExpandableGroupEntity> getExpandableGroups(int groupCount, int childrenCount) {
        ArrayList<ExpandableGroupEntity> groups = new ArrayList<>();
        for (int i = 0; i < groupCount; i++) {
            ArrayList<ChildEntity> children = new ArrayList<>();
            if (i == 0) {

                for (int j = 0; j < childrenCount; j++) {
                    children.add(new ChildEntity("第" + (i + 1) + "组第" + (j + 1) + "项"));
                }
            }
            groups.add(new ExpandableGroupEntity("第" + (i + 1) + "组头部", "第" + (i + 1) + "组尾部", false, children));
        }
        return groups;
    }

    /**
     * 获取可展开收起的组列表数据(默认展开)
     *
     * @param bean 组数量
     * @return
     */
    public static ArrayList<ExpandableGroupEntity> getExpandableGroups(CategoryResponseBean bean) {
        ArrayList<ExpandableGroupEntity> groups = new ArrayList<>();
        for (int i = 0; i < bean.getCategoryList().size(); i++) {
            ArrayList<ChildEntity> children = new ArrayList<>();
            if (i == 0) {
                for (int j = 0; j < bean.getCategoryList().get(0).getChildNames().size(); j++) {
                    children.add(new ChildEntity(bean.getCategoryList().get(0).getChildNames().get(j).getName()));
                }
            }
            groups.add(new ExpandableGroupEntity(bean.getCategoryList().get(i).getName().toString(), "", false, 
                    children));
        }
        return groups;
    }

}