package com.example.toastapp.classes;

public class NewsModel {
    String imageAdr,title;
    int uid;
    String describe;

    public NewsModel(String imageAdr, String title, int uid, String describe) {
        this.imageAdr = imageAdr;
        this.title = title;
        this.uid = uid;
        this.describe = describe;
    }

    public String getImageAdr() {
        return imageAdr;
    }

    public void setImageAdr(String imageAdr) {
        this.imageAdr = imageAdr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}