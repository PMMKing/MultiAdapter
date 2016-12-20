package com.cheng.rvadapter.rvadapter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.cheng.rvadapter.rvadapter.click.OnItemClickListener;
import com.cheng.rvadapter.rvadapter.click.OnItemLongClickListener;
import com.cheng.rvadapter.rvadapter.holder.BaseViewHolder;
import com.cheng.rvadapter.rvadapter.manage.ITypeView;
import com.cheng.rvadapter.rvadapter.manage.TypeViewManage;

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
        int layoutId = iTypeView.getItemViewLayoutId();
        BaseViewHolder viewHolder = iTypeView.createViewHolder(mContext, parent, layoutId);
        viewHolder.onCreateViewHolder(viewHolder);
        setClickListener(parent, viewHolder, viewType);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBindViewHolder(holder,position);
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


    public TypeViewManage addTypeView(ITypeView iTypeView){
        return mTypeViewManage.addTypeView(iTypeView);
    }

    public TypeViewManage addTypeView(ITypeView iTypeView , int layoutId) {
        return mTypeViewManage.addTypeView(iTypeView,layoutId);
    }


    private void setClickListener(ViewGroup parent, final BaseViewHolder viewHolder, int viewType) {

        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    itemClickListener.onItemClickListener(view, viewHolder, position);
                }
            }
        });

        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (itemLongClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    return itemLongClickListener.onItemLongClickListener(view, viewHolder, position);
                }
                return false;
            }
        });

    }

    public void setData(List<T> mDatas){
        this.mDatas.clear();
        this.mDatas.addAll(mDatas);
        notifyDataSetChanged();
    }

    public void addData(List<T> mDatas){
        this.mDatas.addAll(mDatas);
        notifyDataSetChanged();
    }



    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setOnLongItemClickListener(OnItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }
}
