package com.example.news.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseModel {
    //这个是rxjava 自带容器,里面专门存放Disposable
    CompositeDisposable mCompositeDisposable = null;
    //1.打断网络请求
    public void onDestory() {
        if (mCompositeDisposable != null){
            //将容器中所有的Disposable对象的网络请求打断
            mCompositeDisposable.dispose();
        }
    }

    public void addDisposable(Disposable disposable){
        if (mCompositeDisposable == null){
            mCompositeDisposable = new CompositeDisposable();
        }
        //添加
        mCompositeDisposable.add(disposable);
    }

    //移除某个Disposable对象
    public void removeDisposable(Disposable disposable) {
        if (mCompositeDisposable != null){
            mCompositeDisposable.remove(disposable);
        }
    }
}
