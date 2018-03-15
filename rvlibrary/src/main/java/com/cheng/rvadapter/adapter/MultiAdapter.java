package com.cheng.rvadapter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


import com.cheng.rvadapter.click.OnItemClickListener;
import com.cheng.rvadapter.click.OnItemLongClickListener;
import com.cheng.rvadapter.holder.BaseViewHolder;
import com.cheng.rvadapter.manage.ITypeView;
import com.cheng.rvadapter.manage.TypeViewManage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shucheng.qu on 2016/12/19.
 */

public class MultiAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected List<T> mDatas;
    private Context mContext;
    protected TypeViewManage mTypeViewManage;
    private OnItemClickListener itemClickListener;
    private OnItemLongClickListener itemLongClickListener;


    public MultiAdapter(Context context, List<T> datas) {
        mContext = context;
        mDatas = datas;
        if (mDatas == null) {
            mDatas = new ArrayList<T>();
        }
        mTypeViewManage = new TypeViewManage();
    }

    public MultiAdapter(Context context) {
        mContext = context;
        mDatas = new ArrayList<T>();
        mTypeViewManage = new TypeViewManage();
    }

    private boolean multiLayout() {
        return mTypeViewManage.getManageSize() > 0;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ITypeView iTypeView = mTypeViewManage.getITypeView(viewType);
        if (iTypeView == null) {
            return null;
        }
//        int layoutId = iTypeView.getItemViewLayoutId();
        BaseViewHolder viewHolder = iTypeView.createViewHolder(mContext, parent);
        viewHolder.setmDatas(mDatas);
        setClickListener(parent, viewHolder, viewType);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBindViewHolder(holder, mDatas.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (!multiLayout())
            return super.getItemViewType(position);
        return mTypeViewManage.getItemViewType(mDatas.get(position), position);
    }

    public MultiAdapter<T> addTypeView(ITypeView iTypeView) {
        mTypeViewManage.addTypeView(iTypeView);
        return this;
    }

    public MultiAdapter<T> addTypeView(ITypeView iTypeView, int layoutId) {
        mTypeViewManage.addTypeView(iTypeView, layoutId);
        return this;
    }


    private void setClickListener(ViewGroup parent, final BaseViewHolder viewHolder, int viewType) {

        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    try {//当调用notifyDataSetChanged();d的瞬间触发onclick事件，recycleview为null，position返回为-1
                        itemClickListener.onItemClickListener(view, mDatas.get(position), position);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (itemLongClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    try {
                        return itemLongClickListener.onItemLongClickListener(view, mDatas.get(position), position);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });

    }

    public void setData(List<T> mDatas) {
        setData(mDatas, true);
    }

    public void setData(List<T> mDatas, boolean refresh) {
        this.mDatas.clear();
        this.mDatas.addAll(mDatas);
        if (refresh) {
            notifyDataSetChanged();
        }
    }

    public void addData(T t) {
        this.mDatas.add(t);
    }

    public void addData(T t, int index) {
        this.mDatas.add(index, t);
    }

    public void addData(List<T> mDatas) {
        addData(mDatas, true);
    }

    public void addData(List<T> mDatas, Boolean refresh) {
        if (mDatas == null) return;
        this.mDatas.addAll(mDatas);
        if (refresh) {
            notifyDataSetChanged();
        }
    }

    public void removeData(int index) {
        if (mDatas.size() <= index) return;
        this.mDatas.remove(index);
    }


    public List<T> getData() {
        return mDatas;
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setOnLongItemClickListener(OnItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }

    public Context getContext() {
        return mContext;
    }

}
