package com.cheng.rvadapter.recyclerviewlibrary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cheng.rvadapter.rvadapter.holder.BaseViewHolder;

/**
 * Created by shucheng.qu on 2016/12/13.
 */

public class ViewHolder extends BaseViewHolder {


    TextView tvItem;

    public ViewHolder(Context context, View itemView) {
        super(context, itemView);
    }

    public static ViewHolder createViewHolder(Context context, View itemView) {
        return new ViewHolder(context, itemView);
    }

    public static ViewHolder createViewHolder(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new ViewHolder(context, itemView);
    }

    @Override
    public void onCreateViewHolder(BaseViewHolder holder) {
        ViewHolder viewHolder = (ViewHolder)holder;
        View convertView = viewHolder.getConvertView();
        viewHolder.tvItem = (TextView) convertView.findViewById(R.id.tv_item);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder viewHolder, int position) {
//        tvItem = (TextView) viewHolder.getConvertView().findViewById(R.id.tv_item);
        ViewHolder holder = (ViewHolder)viewHolder;
        holder.tvItem.setText("这是第 ：" + position + "个Item。");
    }


}