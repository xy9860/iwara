package com.xy9860.iwara.data;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.xy9860.iwara.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Common {
    private Activity activity;
    private Integer Color;
    public Common(Activity activity){
        this.activity = activity;
        this.Color = activity.getResources().getColor(R.color.colorPrimary);
    }
    public void GetData(List<Data> mData, Integer mContent, Integer page) {
        String content = null;
        //File file = new File(activity.getFilesDir(),"info");
        if (mContent == 1){
            content = "videos";
        } else if (mContent == 2){
            content = "images";
        } else if (mContent == 3){
            content = "subscriptions";
        }
        webCrawler(mData,content,page);
    }
    private void webCrawler(List<Data> mData,String content,Integer page) {
        String url = "https://ecchi.iwara.tv/"+content+"?page="+page;
        Log.i("url","请求结果:" + url);
        try {
            Document document = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36")
                    .timeout(20000).get();
            Elements items = document.select("div[class*=node-video]");
            AssetManager am = activity.getAssets();
            InputStream is = null;
            try {
                is = am.open("p1.jpg");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Bitmap pic = BitmapFactory.decodeStream(is);
            for (Element item : items) {
                Data mdata = new Data();
                mdata.setAuther("xy9860");
                mdata.setaUri("0");
                mdata.setaThumbnail(pic);
                String title = item.select("h3.title").select("a").first().text();
                String like = item.select("div[class*=right-icon]").first().text();
                String playtimes = item.select("div[class*=left-icon]").first().text();
                mdata.setaLike(like);
                mdata.setaPlayTimes(playtimes);
                mdata.setaTitle(title);
                mData.add(mdata);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
