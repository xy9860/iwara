package com.xy9860.iwara.data;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.xy9860.iwara.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public class Common {
    private Activity activity;
    private Integer Color;
    public Common(Activity activity){
        this.activity = activity;
        this.Color = activity.getResources().getColor(R.color.colorPrimary);
    }
    public List<Data> GetData(Integer mContent, Boolean refresh, Integer page) {
        List<Data> listData = new LinkedList<>();
        File file = new File(activity.getFilesDir(),"info");
        Data mdata = new Data();
        if (mContent == 1 || refresh || page == 1){
            AssetManager am = activity.getAssets();
            InputStream is = null;
            try {
                is = am.open("p1.jpg");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Bitmap pic = BitmapFactory.decodeStream(is);
            mdata.setAuther("xy9860");
            mdata.setaTitle("标题");
            mdata.setaLike(0);
            mdata.setaPlayTimes(0);
            mdata.setaUri("0");
            mdata.setaThumbnail(pic);
            listData.add(mdata);
            listData.add(new Data("狗说", "你是狗么?",1,1,"1", pic));
            listData.add(new Data("牛说", "你是牛么?",2,2,"2", pic));
            listData.add(new Data("鸭说", "你是鸭么?",3,3,"3", pic));
            listData.add(new Data("鱼说", "你是鱼么?",4,4,"4", pic));
            listData.add(new Data("马说", "你是马么?",5,5,"5", pic));
        } else if (mContent == 2){
            AssetManager am = activity.getAssets();
            InputStream is = null;
            try {
                is = am.open("p1.jpg");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Bitmap pic = BitmapFactory.decodeStream(is);
            mdata.setAuther("dsp");
            mdata.setaTitle("图片");
            mdata.setaLike(0);
            mdata.setaPlayTimes(0);
            mdata.setaUri("0");
            mdata.setaThumbnail(pic);
            listData.add(mdata);
            listData.add(new Data("狗说", "你是狗么?",6,6,"1", pic));
            listData.add(new Data("牛说", "你是牛么?",7,7,"2", pic));
            listData.add(new Data("鸭说", "你是鸭么?",8,8,"3", pic));
            listData.add(new Data("鱼说", "你是鱼么?",9,9,"4", pic));
            listData.add(new Data("马说", "你是马么?",10,10,"5", pic));
        }
        return listData;
    }
    public void SetStatusBar() {
        StatusBarUtil.setColorForSwipeBack(activity,Color,0);
    }
    public void SetStatusBar(View view) {
        StatusBarUtil.setColorForDrawerLayout(activity, (DrawerLayout) view, Color,0);
    }

    public void PlayVideo() {

    }
}
