package com.example.news.model;

import com.example.news.base.BaseModel;
import com.example.news.bean.VerifyBean;
import com.example.news.net.ApiService;
import com.example.news.net.BaseObserver;
import com.example.news.net.HttpUtil;
import com.example.news.net.ResultCallBack;
import com.example.news.net.RxUtils;


public class MainModel extends BaseModel {

    public void getVerify(final ResultCallBack<VerifyBean> callBack) {
        /*Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ApiService.sBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        Retrofit retrofit = builder
                .build();

        ApiService apiService = retrofit.create(ApiService.class);*/

        /*ApiService apiService = HttpUtil.getInstance().getApiserver(ApiService.sBaseUrl, ApiService.class);
        apiService.getVerify()
             *//*   .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())*//*
             //切换线程
                .compose(RxUtils.<VerifyBean>rxObserableSchedulerHelper())
                .subscribe(new Observer<VerifyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //订阅的时候调用
                        //Disposable d,打断网络请求
                        //当页面关闭的时候,如果网络请求没有完成,这个时候
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(VerifyBean verifyBean) {
                        //成功
                        callBack.onSuccess(verifyBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("tag", "onError: "+e.toString());
                        //失败
                        callBack.onFail(e.toString());
                    }

                    @Override
                    public void onComplete() {
                        //事件流结束的方法
                    }
                });*/

        ApiService apiService = HttpUtil.getInstance().getApiserver(ApiService.sBaseUrl, ApiService.class);
        apiService.getVerify()
             //切换线程
                .compose(RxUtils.<VerifyBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<VerifyBean>(this) {

                    @Override
                    protected void onSuccess(VerifyBean verifyBean) {
                        callBack.onSuccess(verifyBean);
                    }

                    @Override
                    protected void error(String err) {
                        callBack.onFail(err);
                    }
                });
    }

}
