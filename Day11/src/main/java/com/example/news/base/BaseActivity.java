package com.example.news.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.news.util.ToastUtil;
import com.example.news.widget.LoadingDialog;

import butterknife.ButterKnife;


public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {

    protected P mPresenter = null;
    private LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mPresenter = initPresenter();
        if (mPresenter != null){
            mPresenter.bindView(this);
        }

        ButterKnife.bind(this);
        initView();
        initData();
        initListener();
    }

    protected abstract P initPresenter();

    //点击/长按之类的监听
    protected void initListener() {

    }

    protected void initData() {

    }

    protected void initView() {

    }

    //获取布局id
    protected abstract int getLayoutId();

    @Override
    public void showToast(String msg) {
        //开发的时候尽可能少的使用Activity的上下文,因为容易内存泄漏
        //上下文有几种:Application,Activity,Service
        //几个上下文:1 + activity的数量 + service数量
        //有时候Activity退出 了,但是土司还在弹
        ToastUtil.showToastShort(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //1.打断网络请求
        //2.打断V层和P层的关联
        mPresenter.onDestory();
        mPresenter = null;
    }

    @Override
    public void showLoading() {
        if (mLoadingDialog == null){
            mLoadingDialog = new LoadingDialog(this);
        }
        if (!mLoadingDialog.isShowing()){
            mLoadingDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()){
            mLoadingDialog.dismiss();
        }
    }
}
