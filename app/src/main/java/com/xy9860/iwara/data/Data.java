package com.xy9860.iwara.data;

import android.graphics.Bitmap;

public class Data {
    private String Auther;
    private String Title;
    private Integer Like;
    private Integer PlayTimes;
    private String Uri;
    private Bitmap Thumbnail;

    public Data() {}
    public Data(String Auther, String Title, Integer Like, Integer PlayTimes, String uri, Bitmap Thumbnail) {
        this.Auther = Auther;
        this.Title = Title;
        this.Like = Like;
        this.PlayTimes = PlayTimes;
        this.Uri = uri;
        this.Thumbnail = Thumbnail;
    }

    public String getaAuther() {
        return Auther;
    }
    public String getaTitle() {
        return Title;
    }
    public Integer getaLike() {
        return Like;
    }
    public Integer getaPlayTimes() {
        return PlayTimes;
    }
    public String getaUri() {
        return Uri;
    }
    public Bitmap getaThumbnail() { return Thumbnail;}

    public void setAuther(String Auther) {
        this.Auther = Auther;
    }
    public void setaTitle(String Title) {
        this.Title = Title;
    }
    public void setaLike(Integer Like) {
        this.Like = Like;
    }
    public void setaPlayTimes(Integer PlayTimes) { this.PlayTimes = PlayTimes; }
    public void setaUri(String Uri) {
        this.Uri = Uri;
    }
    public void setaThumbnail(Bitmap Thumbnail) {
        this.Thumbnail = Thumbnail;
    }
}