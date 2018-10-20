package com.xy9860.iwara.data;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.xy9860.iwara.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.TreeMap;

import javax.net.ssl.HttpsURLConnection;

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
        try {
            Document document = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36")
                    .timeout(20000).get();
            Elements items = document.select("div[class*=node-video]");
            for (Element item : items) {
                Data mdata = new Data();
                Element title = item.select("h3.title").select("a").first();
                Element auther = item.select("a.username").first();
                Element like = item.select("div[class*=right-icon]").first();
                Element playtimes = item.select("div[class*=left-icon]").first();
                Element thumbnail = item.select("img[src*=/thumbnail/]").first();
                Element uri = item.select("h3.title").select("a").first();
                mdata.setaTitle(isEmpty(title,"title"));
                mdata.setAuther(isEmpty(auther,"auther"));
                mdata.setaLike(isEmpty(like,"like"));
                mdata.setaPlayTimes(isEmpty(playtimes,"playtimes"));
                mdata.setaThumbnail("https:"+isEmpty(thumbnail,"thumbnail"));
                mdata.setaUri(isEmpty(uri,"uri"));
                mData.add(mdata);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public TreeMap GetPlayUrl(String uri) throws IOException, JSONException {
        TreeMap<String,TreeMap<String,String>> playurls = new TreeMap<>();
        String url = "https://ecchi.iwara.tv/api/video/"+uri.replace("/videos/","");
        URL urlobj = new URL(url);
        HttpsURLConnection urlConnection = (HttpsURLConnection) urlobj.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setConnectTimeout(8 * 1000);
        urlConnection.setReadTimeout(8 * 1000);
        int responseCode = urlConnection.getResponseCode();
        if (responseCode == 200) { // 请求成功
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
            String result = reader.readLine();
            JSONArray definitions = new JSONArray(result);
            for (int i=0;i<definitions.length();i++){
                TreeMap<String,String> playurl = new TreeMap<>();
                playurl.put("mime",definitions.getJSONObject(i).getString("mime"));
                playurl.put("resolution",definitions.getJSONObject(i).getString("resolution"));
                playurl.put("uri","https:"+definitions.getJSONObject(i).getString("uri"));
                playurls.put(String.valueOf(i),playurl);
            }
            inputStream.close();
        }
        urlConnection.disconnect();
        return playurls;
    }
    public void SetStatusBar() {
        StatusBarUtil.setColorForSwipeBack(activity,Color,0);
    }
    public void SetStatusBar(View view) {
        StatusBarUtil.setColorForDrawerLayout(activity, (DrawerLayout) view, Color,0);
    }
    private String isEmpty(Element ele,String type){
        if (ele == null){
            return "";
        } else if (type.equals("uri")) {
            return ele.attr("href");
        } else if (type.equals("thumbnail")) {
            return ele.attr("src");
        } else{
            return ele.text();
        }
    }
}
