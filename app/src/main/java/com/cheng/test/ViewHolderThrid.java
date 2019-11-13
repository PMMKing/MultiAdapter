package com.cheng.test;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.cheng.rvadapter.holder.BaseViewHolder;

/**
 * Created by shucheng.qu on 2018/3/15
 */

public class ViewHolderThrid extends BaseViewHolder<Integer> {


    private TextView tvItem;

    public ViewHolderThrid(Context context, int layoutId) {
        super(context, layoutId);
        tvItem = itemView.findViewById(R.id.tv_item);
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, Integer data, int position) {
        tvItem.setText("布局2的item的position是：" + position + "    data是：" + data);
    }
}
