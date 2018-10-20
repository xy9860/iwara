package com.xy9860.iwara.data;

public class Data {
    private String Auther;
    private String Title;
    private String Like;
    private String PlayTimes;
    private String Uri;
    private String Thumbnail;

    Data() {}

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
    public String getaUri() { return Uri; }
    public String getaThumbnail() { return Thumbnail;}

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
    void setaThumbnail(String Thumbnail) {
        this.Thumbnail = Thumbnail;
    }
}