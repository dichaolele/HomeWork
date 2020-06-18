package com.example.news.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.news.R;
import com.example.news.adapter.VpZhihuAdapter;
import com.example.news.base.BaseFragment;
import com.example.news.presenter.GankPresenter;
import com.example.news.view.GankView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class GankFragment extends BaseFragment<GankPresenter> implements GankView {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.vp)
    ViewPager vp;
    Unbinder unbinder;
    private ArrayList<String> mTitles;
    private ArrayList<BaseFragment> mFragments;
    public static BaseFragment newInstance() {
        GankFragment fragment = new GankFragment();
        return fragment;
    }

    @Override
    protected GankPresenter initPresenter() {
        return new GankPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gank;
    }

    @Override
    protected void initView() {
        initTitles();
        initFragments();

        VpZhihuAdapter adapter = new VpZhihuAdapter(getChildFragmentManager(), mFragments, mTitles);
        vp.setAdapter(adapter);
        tabLayout.setupWithViewPager(vp);
    }

    private void initTitles() {
        mTitles = new ArrayList<>();
        mTitles.add("Android");
        mTitles.add("iOS");
        mTitles.add("前端");
        mTitles.add("福利");
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(GankItemFragment.newInstance(mTitles.get(0)));
        mFragments.add(GankItemFragment.newInstance(mTitles.get(1)));
        mFragments.add(GankItemFragment.newInstance(mTitles.get(2)));
        mFragments.add(MeiziFragment.newInstance());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
