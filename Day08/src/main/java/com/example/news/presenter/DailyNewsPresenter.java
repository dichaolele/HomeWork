package com.example.news.presenter;


import com.example.news.base.BasePresenter;
import com.example.news.bean.DailyNewsBean;
import com.example.news.model.DailyNewsModel;
import com.example.news.net.ResultCallBack;
import com.example.news.view.DailyNewsView;

public class DailyNewsPresenter extends BasePresenter<DailyNewsView> {

    private DailyNewsModel mDailyNewsModel;

    @Override
    protected void initModel() {
        mDailyNewsModel = new DailyNewsModel();
        addModel(mDailyNewsModel);
    }

    public void getLastNews() {
        mDailyNewsModel.getLastNews(new ResultCallBack<DailyNewsBean>() {
            @Override
            public void onSuccess(DailyNewsBean dailyNewsBean) {
                if (dailyNewsBean != null && mView != null){
                    mView.setData(dailyNewsBean);
                }
            }

            @Override
            public void onFail(String msg) {
                if (mView != null){
                    mView.showToast(msg);
                }
            }
        });

    }

    public void getOldNews(String time) {
        mDailyNewsModel.getOldNews(time, new ResultCallBack<DailyNewsBean>() {
            @Override
            public void onSuccess(DailyNewsBean dailyNewsBean) {
                if (dailyNewsBean != null && mView != null){
                    mView.setData(dailyNewsBean);
                }
            }

            @Override
            public void onFail(String msg) {
                if (mView != null){
                    mView.showToast(msg);
                }
            }
        });
    }
}
