package com.androidimptchanges.model;

/**
 * Created by android on 11/25/2016.
 */

public class CornTab {

    public String tags;
    public int count;

    public CornTab() {
    }

    public CornTab(String tags, int count) {
        this.tags = tags;
        this.count = count;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
