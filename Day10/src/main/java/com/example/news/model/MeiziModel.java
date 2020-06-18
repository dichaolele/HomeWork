package com.example.news.model;

import com.example.news.base.BaseModel;
import com.example.news.bean.MeiziBean;
import com.example.news.net.ApiService;
import com.example.news.net.BaseObserver;
import com.example.news.net.HttpUtil;
import com.example.news.net.ResultCallBack;
import com.example.news.net.RxUtils;

public class MeiziModel extends BaseModel {
    public void getData(int page,final ResultCallBack<MeiziBean> resultCallBack){
        HttpUtil.getInstance()
                .getApiserver(ApiService.sGankUrl, ApiService.class)
                .getMeiziList(page)
                .compose(RxUtils.<MeiziBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MeiziBean>(this) {
                    @Override
                    protected void onSuccess(MeiziBean meiziBean) {
                        resultCallBack.onSuccess(meiziBean);
                    }

                    @Override
                    protected void error(String err) {
                        resultCallBack.onFail(err);
                    }
                });
                
    }



}
