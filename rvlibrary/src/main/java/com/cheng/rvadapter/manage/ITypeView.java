package com.cheng.rvadapter.manage;


import android.content.Context;
import android.view.ViewGroup;

import com.cheng.rvadapter.holder.BaseViewHolder;


/**
 * Created by shucheng.qu on 2016/12/16.
 */

public interface ITypeView<T> {

    boolean isForViewType(T item, int position);

    BaseViewHolder createViewHolder(Context mContext, ViewGroup parent);
}
