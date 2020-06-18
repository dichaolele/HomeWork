package com.example.news.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.news.R;
import com.example.news.adapter.VpZhihuAdapter;
import com.example.news.base.BaseApp;
import com.example.news.base.BaseFragment;
import com.example.news.presenter.ZhihuPresenter;
import com.example.news.view.ZhihuView;


import java.util.ArrayList;

import butterknife.BindView;

public class ZhihuFragment extends BaseFragment<ZhihuPresenter> implements ZhihuView {

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.vp)
    ViewPager mVp;
    private ArrayList<String> mTitles;
    private ArrayList<BaseFragment> mFragments;

    public static BaseFragment newInstance() {
        ZhihuFragment fragment = new ZhihuFragment();
        return fragment;
    }

    @Override
    protected ZhihuPresenter initPresenter() {
        return new ZhihuPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu;
    }

    @Override
    protected void initView() {
        initTitles();
        initFragments();

        VpZhihuAdapter adapter = new VpZhihuAdapter(getChildFragmentManager(), mFragments, mTitles);
        mVp.setAdapter(adapter);

        mTabLayout.setupWithViewPager(mVp);
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(DailyNewsFragment.newInstance());
        mFragments.add(SectionFragment.newInstance());
        mFragments.add(HotFragment.newInstance());
    }

    private void initTitles() {
        mTitles = new ArrayList<>();
        mTitles.add(BaseApp.getRes().getString(R.string.daily_news));
        mTitles.add(BaseApp.getRes().getString(R.string.section));
        mTitles.add(BaseApp.getRes().getString(R.string.hot));
    }
}
