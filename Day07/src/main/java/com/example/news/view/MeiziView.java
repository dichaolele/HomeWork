package com.example.news.view;

import com.example.news.base.BaseView;
import com.example.news.bean.MeiziBean;

public interface MeiziView extends BaseView {
    void setData(MeiziBean bean);
    void finishRereshAndLoadMore();
}
