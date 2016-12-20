package com.cheng.rvadapter.rvadapter.manage;

import android.support.v4.util.SparseArrayCompat;

/**
 * Created by shucheng.qu on 2016/12/16.
 */

public class TypeViewManage<T> {


    SparseArrayCompat<ITypeView<T>> manageMap = new SparseArrayCompat<ITypeView<T>>();
    int key = -1;

    /**
     * @param typeView
     * @return
     */
    public TypeViewManage addTypeView(ITypeView typeView) {
        ++key;
        if (manageMap.get(key) == null) {
            manageMap.put(key, typeView);
        } else {
            addTypeView(typeView);
        }
        return this;
    }

    /**
     * @param layoutId
     * @param typeView
     * @return 不为空的时候以最后一次添加为准
     */
    public TypeViewManage addTypeView(ITypeView typeView, int layoutId) {
        ITypeView<T> tempTypeView = manageMap.valueAt(layoutId);
        manageMap.put(layoutId, typeView);
        return this;
    }

    public TypeViewManage<T> removeTypeView(ITypeView<T> typeView) {
        if (typeView == null) {
            throw new NullPointerException("typeView is null");
        }
        int indexToRemove = manageMap.indexOfValue(typeView);

        if (indexToRemove >= 0) {
            manageMap.removeAt(indexToRemove);
        }
        return this;
    }

    public TypeViewManage<T> removeTypeView(int layoutId) {
        int indexToRemove = manageMap.indexOfKey(layoutId);
        if (indexToRemove >= 0) {
            manageMap.removeAt(indexToRemove);
        }
        return this;
    }

    public int getItemViewType(T item, int position) {
        int typeViewCount = manageMap.size();
        for (int i = typeViewCount - 1; i >= 0; i--) {
            ITypeView<T> typeView = manageMap.valueAt(i);
            if (typeView.isForViewType(item, position)) {
                return manageMap.keyAt(i);
            }
        }
        throw new IllegalArgumentException("not found specifies the layout corresponding to the data for :" + position);
    }


    public int getManageSize() {
        return manageMap.size();
    }

    public ITypeView<T> getITypeView(int typeView) {
        return manageMap.get(typeView);
    }

    public int getITypeViewLayoutId(int typeView) {
        return manageMap.get(typeView).getItemViewLayoutId();
    }


}
