package com.example.news.net;

public interface ResultCallBack<T> {
    void onSuccess(T t);
    void onFail(String msg);
}
