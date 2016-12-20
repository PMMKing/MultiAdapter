package com.cheng.rvadapter.rvadapter.manage;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.cheng.rvadapter.rvadapter.holder.BaseViewHolder;

/**
 * Created by shucheng.qu on 2016/12/16.
 */

public interface ITypeView<T> {

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    BaseViewHolder createViewHolder(Context mContext, ViewGroup parent, int layoutId);
}
