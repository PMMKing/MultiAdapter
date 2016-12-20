package com.cheng.rvadapter.rvadapter.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by shucheng.qu on 2016/12/15.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    private View mConvertView;
    private Context mContext;

    public BaseViewHolder(Context context, View itemView) {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
    }

    public abstract void onCreateViewHolder(BaseViewHolder holder);

    public abstract void onBindViewHolder(BaseViewHolder holder, int position);

    public View getConvertView() {
        return mConvertView;
    }


}
