package com.cheng.rvadapter.recyclerviewlibrary;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.cheng.rvadapter.adapter.MultiAdapter;
import com.cheng.rvadapter.click.OnItemClickListener;
import com.cheng.rvadapter.click.OnItemLongClickListener;
import com.cheng.rvadapter.holder.BaseViewHolder;
import com.cheng.rvadapter.manage.ITypeView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements OnItemClickListener, OnItemLongClickListener {

    private MultiAdapter<World> adapter;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rlvTest = (RecyclerView) findViewById(R.id.rlv_test);
        rlvTest.setLayoutManager(new LinearLayoutManager(this));
        List<World> worlds = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            worlds.add(new World(random.nextInt(100)));
        }
        adapter = new MultiAdapter<World>(getApplicationContext(), worlds);
        adapter.setOnItemClickListener(this);
        adapter.setOnLongItemClickListener(this);
        adapter.addTypeView(new ITypeView() {
            @Override
            public boolean isForViewType(Object item, int position) {
                World world = (World) item;
                return world.random > 50;
            }

            @Override
            public BaseViewHolder createViewHolder(Context mContext, ViewGroup parent) {
                return new ViewHolder(mContext, LayoutInflater.from(mContext).inflate(R.layout.item_layout , parent , false));
            }

        }).addTypeView(new ITypeView() {
            @Override
            public boolean isForViewType(Object item, int position) {
                World world = (World) item;
                return world.random <= 50;
            }

            @Override
            public BaseViewHolder createViewHolder(Context mContext, ViewGroup parent) {
                return new ViewHolder2(mContext, LayoutInflater.from(mContext).inflate(R.layout.item2_layout , parent , false));
            }

        });
        rlvTest.setAdapter(adapter);

    }

    @Override
    public void onItemClickListener(View view, BaseViewHolder viewHolder, int position) {
        Toast.makeText(getApplicationContext(), "点击了第 ： " + position + " 个item ", Toast.LENGTH_SHORT).show();
//        List<World> worlds = new ArrayList<>();
//        for (int i = 0; i < position; i++) {
//            worlds.add(new World(random.nextInt()));
//        }
//        adapter.addData(worlds);//在原有数据的基础上添加数据
//        adapter.setData(worlds);//把原有数据清空，替换新数据

    }

    @Override
    public boolean onItemLongClickListener(View view, BaseViewHolder viewHolder, int position) {
        return false;
    }
}
