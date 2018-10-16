package com.xy9860.iwara;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xy9860.iwara.data.Common;
import com.xy9860.iwara.data.Data;
import com.xy9860.iwara.data.ItemDecoration;
import com.xy9860.iwara.data.MyAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static boolean isExit=false;
    private MyHandler mHandler;
    private DrawerLayout drawer;

    private List<Data> mData;
    private MyAdapter mAdapter;
    private Context mContext;
    private Intent intent;
    private Common common;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*初始化*/
        Init();
        /*标题栏*/
        Toolbar toolbar = findViewById(R.id.header);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        /*设置状态栏颜色*/
        common.SetStatusBar(mContext,findViewById(R.id.drawer_main),50);
        /*抽屉*/
        drawer = findViewById(R.id.drawer_main);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.ic_menu_drawer);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.drawer_nav);
        navigationView.setNavigationItemSelectedListener(this);
        /*首页*/
        FrameLayout items_content = findViewById(R.id.items_content);
        RecyclerView list_items = LayoutInflater.from(mContext).inflate(R.layout.content_items,items_content,true).findViewById(R.id.list_items);
        list_items.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        list_items.addItemDecoration(new ItemDecoration(mContext));
        list_items.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                //start activity
                Data item = mData.get(position);
                String uri = item.getaTitle();
                ShowItem(uri);
                //Toast.makeText(mContext, "您点击了" + position + "行", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void Init() {
        WeakReference<MainActivity> activity = new WeakReference<>(MainActivity.this);
        this.mHandler = new MyHandler(activity);
        this.mData = InitData();
        this.mAdapter = new MyAdapter(mData);
        this.mContext = MainActivity.this;
        this.common = new Common();
        this.intent = new Intent(mContext,ShowItem.class);
    }
    public List<Data> InitData(){
        AssetManager am = getAssets();
        InputStream is = null;
        try {
            is = am.open("p1.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap pic = BitmapFactory.decodeStream(is);
        List<Data> data = new LinkedList<>();
        Data mdata = new Data();
        mdata.setAuther("xy9860");
        mdata.setaTitle("标题");
        mdata.setaLike(0);
        mdata.setaPlayTimes(0);
        mdata.setaUri("0");
        mdata.setaThumbnail(pic);
        data.add(mdata);
        data.add(new Data("狗说", "你是狗么?",1,1,"1", pic));
        data.add(new Data("牛说", "你是牛么?",2,2,"2", pic));
        data.add(new Data("鸭说", "你是鸭么?",3,3,"3", pic));
        data.add(new Data("鱼说", "你是鱼么?",4,4,"4", pic));
        data.add(new Data("马说", "你是马么?",5,5,"5", pic));
        return data;
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
                isExit = false;
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        TextView title = findViewById(R.id.title);
        if (id == R.id.video) {
            title.setText(R.string.video);
        } else if (id == R.id.picture) {
            title.setText(R.string.picture);
        } else if (id == R.id.subscribe) {
            title.setText(R.string.subscribe);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}