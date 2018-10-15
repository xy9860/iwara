package com.xy9860.iwara;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.xy9860.iwara.data.Common;

public class ShowItem extends AppCompatActivity {
    private Context mContext;
    private Common common;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_item);
        /*初始化*/
        Init();
        /*设置状态栏颜色*/
        common.SetStatusBar(mContext,50);
        Intent item = getIntent();
        String uri = item.getStringExtra("URI");
        TextView textView = findViewById(R.id.show_item_title);
        textView.setText(uri);
    }
    public void Init() {
        this.mContext = ShowItem.this;
        this.common = new Common();
    }
    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.index_in,R.anim.show_item_out);
    }
}