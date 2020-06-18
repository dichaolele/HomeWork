package com.example.news.view;


import com.example.news.base.BaseView;
import com.example.news.bean.DailyNewsBean;

public interface DailyNewsView extends BaseView {
    void setData(DailyNewsBean dailyNewsBean);
}
