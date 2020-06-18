package com.example.news.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.news.R;
import com.example.news.base.BaseRlvAdapter;
import com.example.news.bean.DailyNewsBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.net.SocketTimeoutException;
import java.security.spec.EllipticCurve;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvDailyNewsAdapter extends BaseRlvAdapter {
    private final Context mContext;
    public final ArrayList<DailyNewsBean.StoriesBean> mNewsList;
    private final ArrayList<DailyNewsBean.TopStoriesBean> mBannerList;
    private static final int TYPE_BANNER = 0;
    private static final int TYPE_DATE = 1;
    private static final int TYPE_NEWS = 2;
    private String mDate = "今日新闻";

    public RlvDailyNewsAdapter(Context context, ArrayList<DailyNewsBean.StoriesBean> newsList, ArrayList<DailyNewsBean.TopStoriesBean> bannerList) {

        mContext = context;
        mNewsList = newsList;
        mBannerList = bannerList;
    }

    @Override
    public int getItemViewType(int position) {
        if (mBannerList.size()>0){
            if (position == 0){
                return TYPE_BANNER;
            }else if (position == 1){
                return TYPE_DATE;
            }else {
                return TYPE_NEWS;
            }
        }else {
            if (position == 0){
                return TYPE_DATE;
            }else {
                return TYPE_NEWS;
            }
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        //viewType 其实是系统帮我们调用了getItemViewType()之后的返回值
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (viewType == TYPE_BANNER){
            //banner
            View inflate = inflater.inflate(R.layout.item_banner, null);
            return new BannerVh(inflate);
        }else if (viewType == TYPE_DATE){
            //日期
            View inflate = inflater.inflate(R.layout.item_date, null);
            return new DateVh(inflate);
        }else {
            //新闻
            View inflate = inflater.inflate(R.layout.item_date, null);
            return new NewsVh(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == TYPE_BANNER){
            BannerVh bannerVh = (BannerVh) viewHolder;
            bannerVh.mBanner.setImages(mBannerList)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            DailyNewsBean.TopStoriesBean bean = (DailyNewsBean.TopStoriesBean) path;
                            Glide.with(mContext).load(bean.getImage()).into(imageView);
                        }
                    })
                    .start();
        }else if (viewType == TYPE_DATE){
            DateVh dateVh = (DateVh) viewHolder;
            dateVh.mTvDate.setText(mDate);
        }else {
            NewsVh newsVh = (NewsVh) viewHolder;
            int newPosition = position-1;//-1,减去的是日期
            if (mBannerList.size()>0){
                newPosition -= 1;
            }

            DailyNewsBean.StoriesBean storiesBean = mNewsList.get(newPosition);
            newsVh.mTvDate.setText(storiesBean.getTitle());

            final int finalNewPosition = newPosition;
            newsVh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null){
                        mItemClickListener.onItemClick(v, finalNewPosition);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        //如果bannerList.size>0,代表有Banner
        if (mBannerList.size()>0){
            return 1+1+mNewsList.size();
        }else {
            return 1+mNewsList.size();
        }
    }

    public void setData(DailyNewsBean dailyNewsBean) {
        mDate = dailyNewsBean.getDate();
        //banner
        List<DailyNewsBean.TopStoriesBean> top_stories = dailyNewsBean.getTop_stories();
        //news
        List<DailyNewsBean.StoriesBean> stories = dailyNewsBean.getStories();

        mBannerList.clear();
        mNewsList.clear();

        if (top_stories != null && top_stories.size()>0){
            mBannerList.addAll(top_stories);
        }

        if (stories != null && stories.size()>0){
            mNewsList.addAll(stories);
        }
        notifyDataSetChanged();
    }

    class BannerVh extends RecyclerView.ViewHolder{
        @BindView(R.id.banner)
        Banner mBanner;

        public BannerVh(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    class DateVh extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_date)
        TextView mTvDate;

        public DateVh(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    class NewsVh extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_date)
        TextView mTvDate;

        public NewsVh(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
