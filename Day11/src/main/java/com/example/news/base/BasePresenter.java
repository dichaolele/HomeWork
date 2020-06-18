package com.example.news.base;


import com.example.news.model.DailyNewsModel;

import java.util.ArrayList;

public abstract class BasePresenter<V extends BaseView> {
    protected V mView;
    private ArrayList<BaseModel> mModels = new ArrayList<>();

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    protected void bindView(V view) {
        mView = view;
    }

    public void addModel(DailyNewsModel model){
        if (model != null){
            mModels.add(model);
        }
    }

    public void onDestory() {
        //1.打断网络请求
        //怎样获取到字类中的M层对象
        for (int i = 0; i < mModels.size(); i++) {
            BaseModel baseModel = mModels.get(i);
            baseModel.onDestory();
        }
        mModels.clear();
        //2.打断V层和P层的关联
        mView = null;
    }
}
