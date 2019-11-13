package com.cheng.test;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cheng.rvadapter.adapter.MultiAdapter;
import com.cheng.rvadapter.click.OnItemClickListener;
import com.cheng.rvadapter.click.OnItemLongClickListener;
import com.cheng.rvadapter.holder.BaseViewHolder;
import com.cheng.rvadapter.manage.ITypeView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by shucheng.qu on 2018/3/15
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rlvTest = findViewById(R.id.rlv_test);

        rlvTest.setLayoutManager(new LinearLayoutManager(this));
        MultiAdapter<Integer> adapter = new MultiAdapter<Integer>(this).addTypeView(new ITypeView<Integer>() {
            @Override
            public boolean isForViewType(Integer item, int position) {
                return true;
            }

            @Override
            public BaseViewHolder createViewHolder(Context mContext, ViewGroup parent) {
                return new ViewHolderOne(mContext, LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false));
            }
        }).addTypeView(new ITypeView<Integer>() {
            @Override
            public boolean isForViewType(Integer item, int position) {
                return position % 2 == 0;
            }

            @Override
            public BaseViewHolder createViewHolder(Context mContext, ViewGroup parent) {
                return new ViewHolderTwo(mContext, LayoutInflater.from(mContext).inflate(R.layout.item2_layout, parent, false));
            }
        }).addTypeView(new ITypeView() {
            @Override
            public boolean isForViewType(Object item, int position) {
                return position % 3 == 0;
            }

            @Override
            public BaseViewHolder createViewHolder(Context mContext, ViewGroup parent) {
                return new ViewHolderThrid(mContext,R.layout.item3_layout);
            }
        });
        rlvTest.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener<Integer>() {
            @Override
            public void onItemClickListener(View view, Integer data, int position) {
                Toast.makeText(MainActivity.this, "onClick position is " + position + "   data is " + data, Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnLongItemClickListener(new OnItemLongClickListener<Integer>() {
            @Override
            public boolean onItemLongClickListener(View view, Integer data, int position) {
                Toast.makeText(MainActivity.this, "onLongClick position is " + position + "   data is " + data, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        ArrayList<Integer> list = new ArrayList<>();
        Random rand = new Random();
        while (true) {
            list.add(rand.nextInt());
            if (list.size() > 30) {
                break;
            }
        }
        adapter.setData(list);

    }
}
