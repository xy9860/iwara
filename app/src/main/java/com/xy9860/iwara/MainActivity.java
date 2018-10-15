package com.xy9860.iwara;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.xy9860.iwara.data.Common;
import com.xy9860.iwara.data.Data;
import com.xy9860.iwara.data.ItemDecoration;
import com.xy9860.iwara.data.MyAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private List<Data> mData = null;
    private Context mContext;
    private MyAdapter mAdapter = null;
    private Intent intent;
    private Common common;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*初始化*/
        InitData();
        Init();
        /*标题栏*/
        Toolbar toolbar = findViewById(R.id.header);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        /*设置状态栏颜色*/
        common.SetStatusBar(mContext,findViewById(R.id.drawer_main),50);
        //Integer mStatusBarColor = getResources().getColor(R.color.colorPrimary);
        //StatusBarUtil.setColorForDrawerLayout(MainActivity.this,,mStatusBarColor);
        /*抽屉*/
        DrawerLayout drawer = findViewById(R.id.drawer_main);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.ic_menu_drawer);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = findViewById(R.id.drawer_main);
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
        this.mContext = MainActivity.this;
        this.common = new Common();
        this.intent = new Intent(mContext,ShowItem.class);
        this.mAdapter = new MyAdapter(mData);
    }
    public void InitData(){
        AssetManager am = getAssets();
        InputStream is = null;
        try {
            is = am.open("p1.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap pic = BitmapFactory.decodeStream(is);
        List<Data> data = new LinkedList<>();
        data.add(new Data("狗说", "你是狗么?", pic));
        data.add(new Data("牛说", "你是牛么?", pic));
        data.add(new Data("鸭说", "你是鸭么?", pic));
        data.add(new Data("鱼说", "你是鱼么?", pic));
        data.add(new Data("马说", "你是马么?", pic));
        this.mData = data;
    }
    public void ShowItem(String uri) {
        intent.putExtra("URI",uri);
        startActivity(intent);
        overridePendingTransition(R.anim.show_item_in,R.anim.index_out);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_main);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
        DrawerLayout drawer = findViewById(R.id.drawer_main);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}