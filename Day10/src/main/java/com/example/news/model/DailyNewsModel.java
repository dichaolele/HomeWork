package com.example.news.model;

import com.example.news.base.BaseModel;
import com.example.news.bean.DailyNewsBean;
import com.example.news.net.ApiService;
import com.example.news.net.BaseObserver;
import com.example.news.net.HttpUtil;
import com.example.news.net.ResultCallBack;
import com.example.news.net.RxUtils;


public class DailyNewsModel extends BaseModel {
    public void getLastNews(final ResultCallBack<DailyNewsBean> callBack) {
        HttpUtil.getInstance()
                .getApiserver(ApiService.sZhihuUrl, ApiService.class)
                .getLastNews()
                .compose(RxUtils.<DailyNewsBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<DailyNewsBean>(this) {
                    @Override
                    protected void onSuccess(DailyNewsBean dailyNewsBean) {
                        callBack.onSuccess(dailyNewsBean);
                    }

                    @Override
                    protected void error(String err) {
                        callBack.onFail(err);
                    }
                });
    }

    public void getOldNews(String time, final ResultCallBack<DailyNewsBean> callBack) {
        HttpUtil.getInstance()
                .getApiserver(ApiService.sZhihuUrl, ApiService.class)
                .getOldNews(time)
                .compose(RxUtils.<DailyNewsBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<DailyNewsBean>(this) {
                    @Override
                    protected void onSuccess(DailyNewsBean dailyNewsBean) {
                        callBack.onSuccess(dailyNewsBean);
                    }

                    @Override
                    protected void error(String err) {
                        callBack.onFail(err);
                    }
                });
    }
}
