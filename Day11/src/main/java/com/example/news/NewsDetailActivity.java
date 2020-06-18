package com.example.news;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;


import com.example.news.base.BaseActivity;
import com.example.news.presenter.NewsDetailPresenter;
import com.example.news.util.LogUtil;
import com.example.news.view.NewsDetailView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailActivity extends BaseActivity<NewsDetailPresenter> implements NewsDetailView {
    public static final String NEWS_ID = "newsId";
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.appBar)
    AppBarLayout mAppBar;
    @BindView(R.id.cl)
    CoordinatorLayout mCl;
    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.ctl)
    CollapsingToolbarLayout mCtl;

    @Override
    protected NewsDetailPresenter initPresenter() {
        return new NewsDetailPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void initView() {
        setSupportActionBar(mToolBar);
        //appbarLayout 的滑动偏移监听
        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
                //offset 滑动后的偏移量:200dp, 0 到-600
                LogUtil.print("offset:" + offset);
            }
        });

        //扩张时候的title颜色
        mCtl.setExpandedTitleColor(getResources().getColor(R.color.colorAccent));
        //收缩后显示title的颜色
        mCtl.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
        //设置toolBar标题
        mCtl.setTitle("折叠ToolBar");
    }

}
