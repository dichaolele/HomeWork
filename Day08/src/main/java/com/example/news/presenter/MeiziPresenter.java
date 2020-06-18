package com.example.news.presenter;

import com.example.news.base.BasePresenter;
import com.example.news.bean.MeiziBean;
import com.example.news.model.MeiziModel;
import com.example.news.net.ResultCallBack;
import com.example.news.view.MeiziView;

public class MeiziPresenter extends BasePresenter<MeiziView> {
    private MeiziModel mMeiziModel;


    @Override
    protected void initModel() {
        mMeiziModel = new MeiziModel();
        /*addModel(mMeiziModel);*/
    }

    public void getData(int page) {
        mMeiziModel.getData(page, new ResultCallBack<MeiziBean>() {
            @Override
            public void onSuccess(MeiziBean meiziBean) {
                if (meiziBean != null && mView != null){
                    mView.setData(meiziBean);
                    mView.finishRereshAndLoadMore();
                }
            }

            @Override
            public void onFail(String msg) {
                if(mView != null){
                    mView.showToast(msg);
                    mView.finishRereshAndLoadMore();
                }
            }
        });
    }

}
