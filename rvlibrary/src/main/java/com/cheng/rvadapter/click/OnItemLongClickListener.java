package com.cheng.rvadapter.click;

import android.view.View;

import com.cheng.rvadapter.holder.BaseViewHolder;


/**
 * Created by shucheng.qu on 2016/12/16.
 */

public interface OnItemLongClickListener<T> {

    boolean onItemLongClickListener(View view, T data, int position);

}
