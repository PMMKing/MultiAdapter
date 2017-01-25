package com.cheng.rvadapter.recyclerviewlibrary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cheng.rvadapter.holder.BaseViewHolder;


/**
 * Created by shucheng.qu on 2016/12/13.
 */

public class ViewHolder extends BaseViewHolder {


    TextView tvItem;

    public ViewHolder(Context context, View itemView) {
        super(context, itemView);
        tvItem = (TextView) itemView.findViewById(R.id.tv_item);
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, Object data, int position) {
        tvItem.setText("这是第"  + position + "个item,值为：" + ((World)data).random);
    }
}