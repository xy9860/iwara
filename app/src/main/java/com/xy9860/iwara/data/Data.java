package com.xy9860.iwara.data;

import android.graphics.Bitmap;

public class Data {
    private String Auther;
    private String Title;
    private String Like;
    private String PlayTimes;
    private String Uri;
    private Bitmap Thumbnail;

    Data() {}

    public Data(String Auther, String Title, String Like, String PlayTimes, String uri, Bitmap Thumbnail) {
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
    public String getaLike() {
        return Like;
    }
    public String getaPlayTimes() {
        return PlayTimes;
    }
    public String getaUri() {
        return Uri;
    }
    public Bitmap getaThumbnail() { return Thumbnail;}

    void setAuther(String Auther) {
        this.Auther = Auther;
    }
    void setaTitle(String Title) {
        this.Title = Title;
    }
    void setaLike(String Like) {
        this.Like = Like;
    }
    void setaPlayTimes(String PlayTimes) { this.PlayTimes = PlayTimes; }
    void setaUri(String Uri) {
        this.Uri = Uri;
    }
    void setaThumbnail(Bitmap Thumbnail) {
        this.Thumbnail = Thumbnail;
    }
}