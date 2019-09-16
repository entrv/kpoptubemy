package com.suncaption.kpoptubemy;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Videos extends RealmObject {


    @PrimaryKey
    private String id;
    private String title;
    private String channel;
    private String img;

    private String time;

    public Videos() {

    }

    public Videos(String id, String title, String channel, String img, String time, long date) {
        this.id = id;
        this.title = title;
        this.channel = channel;
        this.img = img;
        this.time = time;
        this.date = date;
    }

    private long date;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
