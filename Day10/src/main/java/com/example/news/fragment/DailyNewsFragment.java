package com.example.news.fragment;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.news.CalendarActivity;
import com.example.news.NewsDetailActivity;
import com.example.news.R;
import com.example.news.adapter.RlvDailyNewsAdapter;
import com.example.news.base.BaseApp;
import com.example.news.base.BaseFragment;
import com.example.news.base.BaseRlvAdapter;
import com.example.news.bean.DailyNewsBean;
import com.example.news.presenter.DailyNewsPresenter;
import com.example.news.util.LogUtil;
import com.example.news.util.SystemUtil;
import com.example.news.util.TimeUtil;
import com.example.news.view.DailyNewsView;
import com.example.news.widget.ColorDividerItemDecoration;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

public class DailyNewsFragment extends BaseFragment<DailyNewsPresenter> implements DailyNewsView {
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    private RlvDailyNewsAdapter mAdapter;

    public static BaseFragment newInstance(){
        DailyNewsFragment fragment = new DailyNewsFragment();
        return fragment;
    }

    @Override
    protected DailyNewsPresenter initPresenter() {
        return new DailyNewsPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily_news;
    }

    @Override
    protected void initView() {
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<DailyNewsBean.StoriesBean> newsList = new ArrayList<>();
        ArrayList<DailyNewsBean.TopStoriesBean> bannerList = new ArrayList<>();

        mAdapter = new RlvDailyNewsAdapter(getContext(), newsList, bannerList);
        mRlv.setAdapter(mAdapter);

        mRlv.addItemDecoration(new ColorDividerItemDecoration(
                BaseApp.getRes().getColor(R.color.transparent),
                SystemUtil.dp2px(10)));

        mAdapter.setOnItemClickListener(new BaseRlvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                DailyNewsBean.StoriesBean bean = mAdapter.mNewsList.get(position);

                Intent intent = new Intent(getContext(), NewsDetailActivity.class);
                intent.putExtra(NewsDetailActivity.NEWS_ID,bean.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getLastNews();
    }

    @Override
    public void setData(DailyNewsBean dailyNewsBean) {
        mAdapter.setData(dailyNewsBean);
    }

    @OnClick({R.id.fab})
    public void click(View v){
        switch (v.getId()) {
            case R.id.fab:
                go2Calendar();
                break;
        }
    }

    private void go2Calendar() {
        startActivity(new Intent(getContext(), CalendarActivity.class));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void receiveDate(CalendarDay date){
        //选中的日期:20191205
        String time = TimeUtil.time2String(date.getCalendar());
        LogUtil.print(time);
        //
        //判断选中的日期是今天还是旧的日期
        String currentTime = TimeUtil.time2String(Calendar.getInstance());

        //选中的是今天
        if (time.equals(currentTime)){
            LogUtil.print("请求今天新闻");
            mPresenter.getLastNews();
        }else {
            //因为接口我们给定20191204,请求到的是20191203,也就是前一天的新闻,所以需要把请求
            //旧新闻的日期+1
            Calendar calendar = date.getCalendar();
            calendar.add(Calendar.DAY_OF_MONTH,1);
            time = TimeUtil.time2String(calendar);
            LogUtil.print("请求旧新闻:"+time);
            mPresenter.getOldNews(time);

        }
    }
}
