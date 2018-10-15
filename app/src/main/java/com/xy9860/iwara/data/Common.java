package com.xy9860.iwara.data;

import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.xy9860.iwara.R;

public class Common {
    public void SetStatusBar(Context mContext,int alpha) {
        Integer mStatusBarColor = mContext.getResources().getColor(R.color.colorPrimary);
        StatusBarUtil.setColor((Activity) mContext,mStatusBarColor,alpha);
    }
    public void SetStatusBar(Context mContext, View view,int alpha) {
        Integer mStatusBarColor = mContext.getResources().getColor(R.color.colorPrimary);
        StatusBarUtil.setColorForDrawerLayout((Activity) mContext, (DrawerLayout) view, mStatusBarColor,alpha);
    }
    public void PlayVideo() {

    }
}
