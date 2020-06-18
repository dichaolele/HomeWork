package com.example.news.fragment;

import com.example.news.R;
import com.example.news.base.BaseFragment;
import com.example.news.presenter.WechatPresenter;
import com.example.news.view.WechatView;


public class WechatFragment extends BaseFragment<WechatPresenter> implements WechatView {

    public static BaseFragment newInstance(){
        WechatFragment fragment = new WechatFragment();
        return fragment;
    }

    @Override
    protected WechatPresenter initPresenter() {
        return new WechatPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wechat;
    }
}
