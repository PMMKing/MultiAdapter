package com.cheng.rvadapter.click;

import android.view.View;

import com.cheng.rvadapter.holder.BaseViewHolder;


/**
 * Created by shucheng.qu on 2016/12/16.
 */

public interface OnItemClickListener {
    void onItemClickListener(View view, BaseViewHolder viewHolder, int position);
}
