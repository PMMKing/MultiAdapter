package com.cheng.test;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.cheng.rvadapter.holder.BaseViewHolder;

/**
 * Created by shucheng.qu on 2018/3/15
 */

public class ViewHolderOne extends BaseViewHolder<Integer> {


    private final TextView tvItem;

    public ViewHolderOne(Context context, View itemView) {
        super(context, itemView);
        tvItem = itemView.findViewById(R.id.tv_item);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, Integer data, int position) {
        tvItem.setText("布局1的item的position是：" + position + "    data是：" + data);
    }
}
