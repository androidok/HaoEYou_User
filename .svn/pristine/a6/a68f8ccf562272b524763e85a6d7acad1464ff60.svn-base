package com.haoeyou.user.activity.chat;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.haoeyou.user.DemoHelper;
import com.haoeyou.user.R;
import com.haoeyou.user.activity.ChatActivity;
import com.haoeyou.user.activity.DoctorActivity;
import com.haoeyou.user.bean.Doctors;
import com.haoeyou.user.common.Common;
import com.haoeyou.user.widget.Constant;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.easeui.widget.chatrow.EaseChatRow;

/**
 * Created by Mou on 2017/6/29.
 */

public class CardChatRow extends EaseChatRow {
    private TextView name;
    private TextView zhuanye;
    private TextView arae;
    private TextView xuewei;
    private ImageView pic;


    public CardChatRow(Context context, EMMessage message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }

    @Override
    protected void onInflateView() {
        if (Constant.CARD_NAME.equals(message.getStringAttribute(Constant.CARD_NAME, "")))
            inflater.inflate(message.direct() == EMMessage.Direct.RECEIVE ? R.layout.ease_row_received_card : R
                    .layout.ease_row_sent_card, this);
    }

    @Override
    protected void onFindViewById() {
        name = (TextView) findViewById(R.id.name);
        xuewei = (TextView) findViewById(R.id.degree);
        zhuanye = (TextView) findViewById(R.id.department);
        arae = (TextView) findViewById(R.id.area);
        pic = (ImageView) findViewById(R.id.pic);

    }

    @Override
    protected void onSetUpView() {
        EMTextMessageBody textMessageBody = (EMTextMessageBody) message.getBody();
        name.setText(message.getStringAttribute("name", ""));
        xuewei.setText(message.getStringAttribute("rank", ""));
        zhuanye.setText(message.getStringAttribute("department", ""));
        arae.setText(message.getStringAttribute("place", ""));
        Glide.with(getContext()).load(Common.BASE_URL.replace("/api", message.getStringAttribute("image_url", "")))
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(pic);
    }

    @Override
    protected void onUpdateView() {
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onBubbleClick() {
        Doctors bean = new Doctors();
        bean.setCer_number(message.getStringAttribute("cer_number", ""));
        bean.setDepartment(message.getStringAttribute("department", ""));
        bean.setHtmlurl(message.getStringAttribute("htmlurl", ""));
        bean.setImage_url(message.getStringAttribute("image_url", ""));
        bean.setName(message.getStringAttribute("name", ""));
        bean.setPlace(message.getStringAttribute("place", ""));
        bean.setSex(message.getStringAttribute("sex", ""));
        bean.setRank(message.getStringAttribute("rank", ""));
        bean.setPesitionals(message.getStringAttribute("pesitionals", ""));
        bean.setService_projects(message.getStringAttribute("service_projects", ""));
        bean.setSpecial(message.getStringAttribute("special", ""));

        DoctorActivity.startAction(getContext(), ChatActivity.TAG, bean);
    }
}
