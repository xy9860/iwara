package com.xy9860.iwara.data;

public class Data {
    private String Auther;
    private String Title;
    private Integer Thumbnail;

    public Data(String Auther, String Title, Integer Thumbnail) {
        this.Auther = Auther;
        this.Title = Title;
        this.Thumbnail = Thumbnail;
    }

    String getaAuther() {
        return Auther;
    }

    String getaTitle() {
        return Title;
    }

    Integer getaThumbnail() { return Thumbnail;}

    public void setaAuther(String Auther) {
        this.Auther = Auther;
    }

    public void setaTitle(String Title) {
        this.Title = Title;
    }

    public void setaThumbnail(Integer Thumbnail) {
        this.Thumbnail = Thumbnail;
    }
}