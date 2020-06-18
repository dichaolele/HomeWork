package com.example.news.bean;

import java.io.Serializable;

public class GoldTabBean implements Serializable {
    public String title;
    public boolean isChecked;

    public GoldTabBean(String title, boolean isChecked) {
        this.title = title;
        this.isChecked = isChecked;
    }
}
