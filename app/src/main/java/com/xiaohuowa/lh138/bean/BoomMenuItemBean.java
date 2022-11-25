package com.xiaohuowa.lh138.bean;

public class BoomMenuItemBean {
    private String title;
    private int imageId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public BoomMenuItemBean(String title, int imageId) {
        this.title = title;
        this.imageId = imageId;
    }
}
