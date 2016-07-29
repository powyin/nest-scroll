package com.powyin.nestscroll;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import com.powyin.nestscroll.adapter.TypeViewHolder_Pic_1;
import com.powyin.nestscroll.adapter.TypeViewHolder_Pic_4;
import com.powyin.nestscroll.adapter.TypeViewHolder_Text;
import com.powyin.nestscroll.net.DataModel;
import com.powyin.nestscroll.refresh.SwipeControlStyle_Horizontal;
import com.powyin.scroll.adapter.MultiAdapter;
import com.powyin.scroll.widget.SwipeRefresh;

/**
 * Created by powyin on 2016/7/27.
 */
public class SimpleSwipeRefresh extends Activity implements View.OnClickListener {
    SwipeRefresh swipeRefresh;
    RecyclerView mRecyclerView;
    ListView listView;
    MultiAdapter<DataModel> multiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_swipe_refresh);
        findView();
        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.click_me_to_set_swipe_control:
                swipeRefresh.setSwipeControl(new SwipeControlStyle_Horizontal(this));
                break;
            case R.id.click_me_to_stop_head:
                swipeRefresh.finishRefresh();
                break;
            case R.id.click_me_to_stop_foot_fresh:
                multiAdapter.addLast(new DataModel(2));
                multiAdapter.addLast(new DataModel(3));
                swipeRefresh.hiddenLoadMore();
                break;
            case R.id.click_me_to_stop_foot_over:
                swipeRefresh.setIsLoadComplete(true);
                break;
        }
    }


    private void findView(){
        findViewById(R.id.click_me_to_set_swipe_control).setOnClickListener(this);
        findViewById(R.id.click_me_to_stop_head).setOnClickListener(this);
        findViewById(R.id.click_me_to_stop_foot_fresh).setOnClickListener(this);
        findViewById(R.id.click_me_to_stop_foot_over).setOnClickListener(this);
        listView = (ListView)findViewById(R.id.my_list);
        mRecyclerView = (RecyclerView)findViewById(R.id.my_recycle);
        swipeRefresh = (SwipeRefresh)findViewById(R.id.re);
    }

    private void init(){
    //    initRecycle();
        initListView();
        swipeRefresh.setOnRefreshListener(new SwipeRefresh.OnRefreshListener() {
            @Override
            public void onRefresh() {
                System.out.println("------------------------------------------------onRefresh----------------------->>>>>>>>");
            }

            @Override
            public void onLoading() {
                System.out.println("------------------------------------------------onLoading----------------------->>>>>>>>");
            }
        });

    }

    private void initListView(){
        multiAdapter = MultiAdapter.getByClass(this, TypeViewHolder_Text.class, TypeViewHolder_Pic_1.class , TypeViewHolder_Pic_4.class);
        listView.setAdapter(multiAdapter);
        for(int i=0;i<3;i++){
            int rad = (int)(Math.random()*10)%3+1;
            System.out.println(rad+":::::::::::");
            multiAdapter.addLast(new DataModel(rad));
        }
    }

    private void initRecycle(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new RecyclerAdapter(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }






}