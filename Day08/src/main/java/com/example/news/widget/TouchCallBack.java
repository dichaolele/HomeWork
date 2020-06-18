package com.example.news.widget;

public interface TouchCallBack {
    //条目交换的回调
    void onItemMove(int fromPosition, int toPosition);

    //移除条目的回调
    void onItemDelete(int position);
}
