package com.example.news.net;


import com.example.news.bean.DailyNewsBean;
import com.example.news.bean.MeiziBean;
import com.example.news.bean.NewsDetailBean;
import com.example.news.bean.VerifyBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    String sBaseUrl = "http://yun918.cn/study/public/index.php/";
    String sZhihuUrl = "http://news-at.zhihu.com/api/4/";
    String sGankUrl = "http://gank.io/";

    @GET("verify")
    Observable<VerifyBean> getVerify();

    //获取最新日报
    @GET("news/latest")
    Observable<DailyNewsBean> getLastNews();

    //获取旧新闻
    @GET("news/before/{date}")
    Observable<DailyNewsBean> getOldNews(@Path("date") String date);

    //新闻详情
    @GET("news/{news_id}")
    Observable<NewsDetailBean> getNewsDetail(@Path("news_id") String newsId);


    //福利接口
    @GET("api/data/%E7%A6%8F%E5%88%A9/10/{page}")
    Observable<MeiziBean> getMeiziList(@Path("page") int page);
}
