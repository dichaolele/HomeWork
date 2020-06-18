package com.example.news.fragment;


import com.example.news.R;
import com.example.news.base.BaseFragment;
import com.example.news.presenter.HotPresenter;
import com.example.news.view.HotView;

public class HotFragment extends BaseFragment<HotPresenter> implements HotView {

    public static HotFragment newInstance(){
        HotFragment fragment = new HotFragment();
        return fragment;
    }

    @Override
    protected HotPresenter initPresenter() {
        return new HotPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }
}
