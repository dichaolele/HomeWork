package com.example.news.fragment;

import com.example.news.R;
import com.example.news.base.BaseFragment;
import com.example.news.presenter.CollectPresenter;
import com.example.news.view.CollectView;


public class CollectFragment extends BaseFragment<CollectPresenter> implements CollectView {

    public static BaseFragment newInstance(){
        CollectFragment fragment = new CollectFragment();
        return fragment;
    }

    @Override
    protected CollectPresenter initPresenter() {
        return new CollectPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect;
    }
}
