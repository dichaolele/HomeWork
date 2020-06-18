package com.example.news.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.news.R;
import com.example.news.base.BaseApp;
import com.example.news.base.BaseRlvAdapter;
import com.example.news.bean.MeiziBean;
import com.example.news.util.SystemUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvMeiziAdater extends BaseRlvAdapter {
    private final Context mContext;
    public final ArrayList<MeiziBean.ResultsBean> mList;

    public RlvMeiziAdater(Context context, ArrayList<MeiziBean.ResultsBean> list) {

        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MeiziVH(LayoutInflater.from(mContext).inflate(R.layout.item_meizi,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MeiziVH meiziVH = (MeiziVH) viewHolder;
        MeiziBean.ResultsBean bean = mList.get(i);
        //图片不能直接通过glide加载进ImageView,需要先计算图片的宽高
        //Glide.with(mContext).load(bean.getUrl()).into(meiziVH.mTv);

        //宽:690 高:827,  图片显示到屏幕上宽度(1080) :540,高度:??
        /// scale = 690 / 827 = 540 / x ,x = 540 / (690 / 827)
        //动态计算宽高
        int width = BaseApp.mWidthPixels/2 - SystemUtil.dp2px(6);
        int height = (int) (width/bean.getScale());

        //子view的布局参数,由父容器控制的
        RelativeLayout.LayoutParams layoutParams =
                (RelativeLayout.LayoutParams) meiziVH.mIv.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        layoutParams.leftMargin = SystemUtil.dp2px(3);
        layoutParams.topMargin = SystemUtil.dp2px(4);

        meiziVH.mIv.setLayoutParams(layoutParams);
        Glide.with(mContext).load(bean.getUrl()).into(meiziVH.mIv);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(List<MeiziBean.ResultsBean> results) {
        mList.addAll(results);
        //notifyDataSetChanged();
        //先计算图片的宽高比
        calculateScale();
    }

    private void calculateScale() {
        for (int i = 0; i < mList.size(); i++) {
            final MeiziBean.ResultsBean resultsBean = mList.get(i);
            if (resultsBean.getScale() == 0){
                //没有计算过宽高比,需要计算
                Glide.with(mContext).load(resultsBean.getUrl())
                        .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource,
                                                @Nullable Transition<? super Drawable> transition) {
                        //宽高比
                        float scale = resource.getIntrinsicWidth() * 1.0f / resource.getIntrinsicHeight();
                        resultsBean.setScale(scale);
                        notifyDataSetChanged();
                    }
                });
            }else{
                notifyDataSetChanged();
            }
        }
    }

    class MeiziVH extends RecyclerView.ViewHolder{
        @BindView(R.id.iv)
        ImageView mIv;
        public MeiziVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
