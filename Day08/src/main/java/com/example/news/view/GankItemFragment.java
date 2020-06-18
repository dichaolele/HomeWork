package com.example.news.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.widget.ImageView;

import com.example.news.R;
import com.example.news.base.BaseFragment;
import com.example.news.presenter.GankItemPresenter;
import com.example.news.util.SystemUtil;
import com.example.news.view.GankItemView;

import butterknife.BindView;

public class GankItemFragment extends BaseFragment<GankItemPresenter> implements GankItemView {
    public static final String TECH = "tech";
    @BindView(R.id.iv_blur)
    ImageView mIvBlur;
    @BindView(R.id.iv_origin)
    ImageView mIvOrigin;
    @BindView(R.id.ctl)
    CollapsingToolbarLayout mCtl;
    @BindView(R.id.appBar)
    AppBarLayout mAppBar;
    @BindView(R.id.cl)
    CoordinatorLayout mCl;
    private String mTech;

    public static GankItemFragment newInstance(String tech) {
        GankItemFragment fragment = new GankItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TECH, tech);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected GankItemPresenter initPresenter() {
        return new GankItemPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gank_item;
    }

    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            mTech = arguments.getString(TECH);
        }

        //高斯模糊
        /*RequestOptions options = new RequestOptions()
                .transform(new BlurTransformation());
        Glide.with(getContext()).load(R.drawable.meizi).apply(options).into(mIvBlur);
        Glide.with(getContext()).load(R.drawable.meizi).into(mIvOrigin);*/


        //appbar滑动偏移jiant
        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
                //offset 偏移量, 200dp  0 到 -600

                //一上来 offset 0 --- 图片完全清晰
                //滑动的过程中渐渐变模糊, 1.0f 完全不透明, 0.0f 完全透明
                // offset 0  ---->  1.0f
                //offset -600  --->  0.0f
                int height = SystemUtil.dp2px(200);
                float alpha = 1.0f + offset * 2.0f / height;
                if (alpha>=0){
                    mIvOrigin.setAlpha(alpha);
                }
            }
        });

    }
}
