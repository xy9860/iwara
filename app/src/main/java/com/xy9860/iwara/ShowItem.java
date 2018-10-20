package com.xy9860.iwara;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.MediaController;
import android.widget.VideoView;

import com.xy9860.iwara.data.Common;

import org.json.JSONException;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.TreeMap;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class ShowItem extends SwipeBackActivity {
    private VideoView videoView;

    private ShowItem.MyHandler mHandler;
    private Common common;
    private TreeMap playurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_item);

        /*初始化*/
        Init();
        /*视频播放*/
        Intent item = getIntent();
        final String uri = item.getStringExtra("URI");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = Message.obtain();
                try {
                    playurl = common.GetPlayUrl(uri);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
                msg.what = 1;
                mHandler.sendMessage(msg);
            }
        });
        thread.start();
    }
    public void Init() {
        WeakReference<ShowItem> activity = new WeakReference<>(ShowItem.this);
        this.videoView = (VideoView) findViewById(R.id.videoplayer);
        this.mHandler = new MyHandler(activity);
        this.common = new Common(this);
        /*设置状态栏透明*/
        common.SetStatusBar();
        /*滑动返回*/
        setSwipeBackEnable(true);
        SwipeBackLayout mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        mSwipeBackLayout.setEdgeSize(50);
    }
    static class MyHandler extends Handler {
        private ShowItem activity;
        MyHandler(WeakReference<ShowItem> ref) {
            activity = ref.get();
        }
        @Override
        public void handleMessage(Message msg) {
            if(activity != null) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        activity.videoView.setMediaController(new MediaController(activity));
                        TreeMap definition = (TreeMap) activity.playurl.get("0");
                        if (definition != null) {
                            String purl = (String) definition.get("uri");
                            activity.videoView.setVideoURI(Uri.parse(purl));
                            activity.videoView.start();
                        }
                        break;
                }
            }
        }
    }
    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.index_in,R.anim.show_item_out);
    }
}