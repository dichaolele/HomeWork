package com.example.news.fragment;


import com.example.news.R;
import com.example.news.base.BaseFragment;
import com.example.news.presenter.SectionPresenter;
import com.example.news.view.SectionView;

public class SectionFragment extends BaseFragment<SectionPresenter> implements SectionView {

    public static SectionFragment newInstance(){
        SectionFragment fragment = new SectionFragment();
        return fragment;
    }

    @Override
    protected SectionPresenter initPresenter() {
        return new SectionPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_section;
    }
}
