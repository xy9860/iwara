package com.xy9860.iwara.data;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.xy9860.iwara.R;

public class Common {
    private Activity activity;
    private Integer Color;
    public Common(Activity activity){
        this.activity = activity;
        Color = activity.getResources().getColor(R.color.colorPrimary);
    }
    public void SetStatusBar() {
        StatusBarUtil.setColor(activity,Color,0);
    }
    public void SetStatusBar(View view) {
        StatusBarUtil.setColorForDrawerLayout(activity, (DrawerLayout) view, Color,0);
    }
    public void SetNavBar() {
        activity.getWindow().setNavigationBarColor(Color);
    }
    public void PlayVideo() {

    }
}
