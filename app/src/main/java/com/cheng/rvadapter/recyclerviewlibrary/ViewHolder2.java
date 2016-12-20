package com.cheng.rvadapter.recyclerviewlibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cheng.rvadapter.rvadapter.holder.BaseViewHolder;

/**
 * Created by shucheng.qu on 2016/12/13.
 */

public class ViewHolder2 extends BaseViewHolder {


    TextView tvItem;

    public ViewHolder2(Context context, View itemView) {
        super(context, itemView);
    }

    public static ViewHolder2 createViewHolder(Context context, View itemView) {
        return new ViewHolder2(context, itemView);
    }

    public static ViewHolder2 createViewHolder(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new ViewHolder2(context, itemView);
    }

    @Override
    public void onCreateViewHolder(BaseViewHolder holder) {
        ViewHolder2 viewHolder = (ViewHolder2)holder;
        View convertView = viewHolder.getConvertView();
        viewHolder.tvItem = (TextView) convertView.findViewById(R.id.tv_item);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder viewHolder, int position) {
//        tvItem = (TextView) viewHolder.getConvertView().findViewById(R.id.tv_item);
        ViewHolder2 holder = (ViewHolder2)viewHolder;
        holder.tvItem.setText("这是第 ：" + position + "个Item。 红的");
    }


}