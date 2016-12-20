package com.cheng.rvadapter.rvadapter.click;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cheng.rvadapter.rvadapter.holder.BaseViewHolder;

/**
 * Created by shucheng.qu on 2016/12/16.
 */

public interface OnItemClickListener {
    void onItemClickListener(View view, BaseViewHolder viewHolder, int position);
}
