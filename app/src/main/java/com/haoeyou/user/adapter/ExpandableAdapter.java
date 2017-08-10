package com.haoeyou.user.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.donkingliang.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.haoeyou.user.R;
import com.haoeyou.user.entity.ChildEntity;
import com.haoeyou.user.entity.ExpandableGroupEntity;
import com.haoeyou.user.widget.smoothcheck.SmoothCheckBox;

import java.util.ArrayList;

/**
 * 可展开收起的Adapter。他跟普通的GroupedListAdapter基本是一样的。
 * 它只是利用了{@link GroupedRecyclerViewAdapter}的
 * 删除一组里的所有子项{@link GroupedRecyclerViewAdapter#removeChildren(int)} 和
 * 插入一组里的所有子项{@link GroupedRecyclerViewAdapter#insertChildren(int)}
 * 两个方法达到列表的展开和收起的效果。
 * 这种列表类似于{@link ExpandableListView}的效果。
 * 这里我把列表的组尾去掉是为了效果上更像ExpandableListView。
 */
public class ExpandableAdapter extends GroupedRecyclerViewAdapter {
    private ArrayList<ExpandableGroupEntity> mGroups;
    public boolean[] flag = new boolean[100];


    public ExpandableAdapter(Context context, ArrayList<ExpandableGroupEntity> groups) {
        super(context);
        mGroups = groups;
    }

    @Override
    public int getGroupCount() {
        return mGroups == null ? 0 : mGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        //如果当前组收起，就直接返回0，否则才返回子项数。这是实现列表展开和收起的关键。
        if (!isExpand(groupPosition)) {
            return 0;
        }
        ArrayList<ChildEntity> children = mGroups.get(groupPosition).getChildren();
        return children == null ? 0 : children.size();
    }

    @Override
    public boolean hasHeader(int groupPosition) {
        return true;
    }

    @Override
    public boolean hasFooter(int groupPosition) {
        return false;
    }

    @Override
    public int getHeaderLayout(int viewType) {
        return R.layout.adapter_expandable_header;
    }

    @Override
    public int getFooterLayout(int viewType) {
        return 0;
    }

    @Override
    public int getChildLayout(int viewType) {
        return R.layout.adapter_child;
    }

    @Override
    public void onBindHeaderViewHolder(BaseViewHolder holder, final int groupPosition) {
        ExpandableGroupEntity entity = mGroups.get(groupPosition);
        holder.setText(R.id.tv_expandable_header, entity.getHeader());
        ImageView ivState = holder.get(R.id.iv_state);
        SmoothCheckBox mCheck = holder.get(R.id.checkbox);
        //        if (mGroups.size() - 1 == groupPosition) {
        //            holder.get(R.id.line).setVisibility(View.INVISIBLE);
        //        }

        if (groupPosition == 0) {
            mCheck.setVisibility(View.GONE);
            ivState.setVisibility(View.VISIBLE);
        } else {
            mCheck.setVisibility(View.VISIBLE);
            ivState.setVisibility(View.GONE);
        }
        if (entity.isExpand()) {
            ivState.setRotation(0);
        } else {
            ivState.setRotation(180);
        }
        mCheck.setOnCheckedChangeListener(null);//先设置一次CheckBox的选中监听器，传入参数null 
        //mCheck.setChecked(flag[groupPosition]);//用数组中的值设置CheckBox的选中状态  
        mCheck.setChecked(false);
        if (fatherTag.containsKey(groupPosition) && fatherTag.get(groupPosition)) {
            mCheck.setChecked(true);
        }

        mCheck.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                fatherTag.put(groupPosition, isChecked);
            }
        });

    }

    @Override
    public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {
        
    }

    @Override
    public void onBindChildViewHolder(BaseViewHolder holder, int groupPosition, final int childPosition) {
        ChildEntity entity = mGroups.get(groupPosition).getChildren().get(childPosition);
        holder.setText(R.id.tv_child, entity.getChild());
        SmoothCheckBox mCheck = holder.get(R.id.child_box);
        mCheck.setOnCheckedChangeListener(null);
        mCheck.setChecked(false);
        if (childTag.containsKey(childPosition) && childTag.get(childPosition)) {
            mCheck.setChecked(true);
        }
        mCheck.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                childTag.put(childPosition, isChecked);
            }

        });
    }

    public void changeIconState(String value) {
        for (int i = 0; i < mGroups.size(); i++) {
            if (mGroups.get(i).getChildren() != null && mGroups.get(i).getChildren().size() > 0) {
                for (int j = 0; j < mGroups.get(i).getChildren().size(); j++) {
                    if (value.equals(mGroups.get(i).getChildren().get(j).getChild())) {
                        if (childTag.containsKey(j)) {
                            childTag.put(j, !childTag.get(j));
                        } else {
                            childTag.put(j, true);
                        }
                        notifyDataSetChanged();
                        return;
                    }
                }
            } else {
                if (value.equals(mGroups.get(i).getHeader())) {
                    if (fatherTag.containsKey(i)) {
                        fatherTag.put(i, !fatherTag.get(i));
                    } else {
                        fatherTag.put(i, true);
                    }
                    notifyDataSetChanged();
                    return;
                }
            }
        }
    }


    /**
     * 判断当前组是否展开
     *
     * @param groupPosition
     * @return
     */
    public boolean isExpand(int groupPosition) {
        ExpandableGroupEntity entity = mGroups.get(groupPosition);
        return entity.isExpand();
    }

    /**
     * 展开一个组
     *
     * @param groupPosition
     */
    public void expandGroup(int groupPosition) {
        expandGroup(groupPosition, false);
    }

    /**
     * 展开一个组
     *
     * @param groupPosition
     * @param animate
     */
    public void expandGroup(int groupPosition, boolean animate) {
        ExpandableGroupEntity entity = mGroups.get(groupPosition);
        entity.setExpand(true);
        if (animate) {
            insertChildren(groupPosition);
        } else {
            changeDataSet();
        }
    }

    /**
     * 收起一个组
     *
     * @param groupPosition
     */
    public void collapseGroup(int groupPosition) {
        collapseGroup(groupPosition, false);
    }

    /**
     * 收起一个组
     *
     * @param groupPosition
     * @param animate
     */
    public void collapseGroup(int groupPosition, boolean animate) {
        ExpandableGroupEntity entity = mGroups.get(groupPosition);
        entity.setExpand(false);
        if (animate) {
            removeChildren(groupPosition);
        } else {
            changeDataSet();
        }
    }

    public void changeContent(ArrayList<ExpandableGroupEntity> groups) {
        this.mGroups.clear();
        this.mGroups = groups;
        notifyDataSetChanged();
    }

    public ArrayList<ExpandableGroupEntity> getGroups() {
        return mGroups;
    }

}
