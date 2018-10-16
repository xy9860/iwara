package com.xy9860.iwara;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.xy9860.iwara.data.Common;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class ShowItem extends SwipeBackActivity {
    private Context mContext;
    private Common common;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_item);
        /*初始化*/
        Init();
        /*设置状态栏颜色*/
        common.SetStatusBar(mContext,30);
        common.SetNavBar(this);
        /*滑动返回*/
        setSwipeBackEnable(true);
        SwipeBackLayout mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        mSwipeBackLayout.setEdgeSize(50);

        Intent item = getIntent();
        String uri = item.getStringExtra("URI");
        TextView textView = (TextView) findViewById(R.id.show_item_title);
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