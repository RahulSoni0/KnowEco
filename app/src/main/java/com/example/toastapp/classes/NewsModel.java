package com.example.toastapp.classes;

public class NewsModel {
    String imageAdr;
    int uid;
    String describe;

    public String getImageAdr() {
        return imageAdr;
    }

    public void setImageAdr(String imageAdr) {
        this.imageAdr = imageAdr;
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

    public NewsModel(String imageAdr, int uid, String describe) {
        this.imageAdr = imageAdr;
        this.uid = uid;
        this.describe = describe;
    }
}