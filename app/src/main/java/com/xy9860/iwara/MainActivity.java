package com.xy9860.iwara;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.xy9860.iwara.data.Common;
import com.xy9860.iwara.data.Data;
import com.xy9860.iwara.data.ItemDecoration;
import com.xy9860.iwara.data.MyAdapter;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static boolean isExit=false;
    private DrawerLayout drawer;
    private Toolbar mTitle;
    private ProgressBar loading;

    private MyHandler mHandler;
    private Common common;
    private List<Data> mData;
    private MyAdapter mAdapter;
    private MyAdapter.OnItemClickListener itemclick;
    private Context mContext;
    private Intent intent;
    private Thread thread;
    private Integer page=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*初始化*/
        Init();
        /*抽屉*/
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, mTitle, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.drawer_nav);
        navigationView.setNavigationItemSelectedListener(this);
        /*首页*/
        FrameLayout items_content = findViewById(R.id.items_content);
        RecyclerView list_items = LayoutInflater.from(mContext).inflate(R.layout.content_items, items_content, true).findViewById(R.id.list_items);
        list_items.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        list_items.addItemDecoration(new ItemDecoration(mContext));
        list_items.setAdapter(mAdapter);
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = Message.obtain();
                common.GetData(mData,1,page);
                msg.what = 1;
                mHandler.sendMessage(msg);
            }
        });
        thread.start();
    }

    public void Init() {
        WeakReference<MainActivity> activity = new WeakReference<>(MainActivity.this);
        this.mTitle = findViewById(R.id.header);
        this.loading = findViewById(R.id.loading);
        this.drawer = findViewById(R.id.drawer_main);
        this.mHandler = new MyHandler(activity);
        this.common = new Common(this);
        this.mData = new LinkedList<>();
        this.mAdapter = new MyAdapter(mData);
        this.itemclick = new MyAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                //start activity
                Data item = mData.get(position);
                String uri = item.getaUri();
                ShowItem(uri);
            }
        };
        this.mContext = this;
        this.intent = new Intent(mContext,ShowItem.class);
        /*设置状态栏透明*/
        common.SetStatusBar(findViewById(R.id.drawer_main));
        /*标题栏*/
        mTitle.setTitle(R.string.video);
        setSupportActionBar(mTitle);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.video) {
            page = 0;
            mData.clear();
            mAdapter.notifyDataSetChanged();
            //mAdapter.notifyDataSetChanged(0, mAdapter.getItemCount());
            loading.setVisibility(ProgressBar.VISIBLE);
            thread.start();
        } else if (id == R.id.picture) {
            page = 1;
            mData.clear();
            mAdapter.notifyDataSetChanged();
            loading.setVisibility(ProgressBar.VISIBLE);
            thread.start();
        } else if (id == R.id.subscribe) {
            page = 2;
            mData.clear();
            mAdapter.notifyDataSetChanged();
            loading.setVisibility(ProgressBar.VISIBLE);
            thread.start();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void ShowItem(String uri) {
        intent.putExtra("URI",uri);
        startActivity(intent);
        overridePendingTransition(R.anim.show_item_in,R.anim.index_out);
    }
    static class MyHandler extends Handler {
        private MainActivity activity;
        MyHandler(WeakReference<MainActivity> ref) {
            activity = ref.get();
        }
        @Override
        public void handleMessage(Message msg) {
            if(activity != null) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        isExit = false;
                        break;
                    case 1:
                        activity.mTitle.setTitle(R.string.subscribe);
                        activity.loading.setVisibility(ProgressBar.GONE);
                        activity.mAdapter.notifyDataSetChanged();
                        activity.mAdapter.setOnItemClickListener(activity.itemclick);
                }
            }
        }
    }
    private void exit(){
        if(!isExit){
            isExit=true;
            Toast.makeText(getApplicationContext(),"再按一次退出程序",Toast.LENGTH_SHORT).show();
            mHandler.sendEmptyMessageDelayed(0,2000);
        }
        else{
            finish();
            System.exit(0);
        }
    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            exit();
        }
    }
}