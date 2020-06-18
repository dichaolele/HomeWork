package com.example.news.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.news.util.ToastUtil;
import com.example.news.widget.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {
    public P mPresenter;
    private Unbinder mUnbinder;
    private LoadingDialog mLoadingDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayoutId(), null);
        mPresenter = initPresenter();
        if (mPresenter != null){
            mPresenter.bindView(this);
        }

        //和activity用法不太一样
        mUnbinder = ButterKnife.bind(this, inflate);
        initView();
        initData();
        initListener();
        return inflate;
    }

    protected void initListener() {

    }

    protected void initData(){

    };

    protected void initView() {

    }

    protected abstract P initPresenter();

    protected abstract int getLayoutId();

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        //1.打断网络请求
        //2.打断V层和P层的关联
        mPresenter.onDestory();
        mPresenter = null;
    }

    @Override
    public void showToast(String msg) {
        //开发的时候尽可能少的使用Activity的上下文,因为容易内存泄漏
        //上下文有几种:Application,Activity,Service
        //几个上下文:1 + activity的数量 + service数量
        //有时候Activity退出 了,但是土司还在弹
        ToastUtil.showToastShort(msg);
    }

    @Override
    public void showLoading() {
        if (mLoadingDialog == null){
            mLoadingDialog = new LoadingDialog(getContext());
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
