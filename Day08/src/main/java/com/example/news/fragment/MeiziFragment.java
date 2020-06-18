package com.example.news.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.news.R;
import com.example.news.adapter.RlvMeiziAdater;
import com.example.news.base.BaseFragment;
import com.example.news.bean.MeiziBean;
import com.example.news.presenter.MeiziPresenter;
import com.example.news.util.LogUtil;
import com.example.news.view.MeiziView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import butterknife.BindView;

public class MeiziFragment extends BaseFragment<MeiziPresenter> implements MeiziView {

    @BindView(R.id.rlv)
    RecyclerView mRlv;
    @BindView(R.id.srl)
    SmartRefreshLayout mSrl;
    private int mPage = 1;
    private RlvMeiziAdater mAdapter;

    public static MeiziFragment newInstance() {
        MeiziFragment fragment = new MeiziFragment();
        return fragment;
    }

    @Override
    protected MeiziPresenter initPresenter() {
        return new MeiziPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_meizi;
    }

    @Override
    protected void initData() {
        mPresenter.getData(mPage);
    }

    @Override
    public void setData(MeiziBean bean) {
        LogUtil.print(bean.getResults().toString());
        if (bean.getResults() != null && bean.getResults().size()>0){
            mAdapter.addData(bean.getResults());
        }
    }

    @Override
    public void finishRereshAndLoadMore() {
        mSrl.finishLoadMore();
        mSrl.finishRefresh();
    }

    @Override
    protected void initView() {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //防止图片在上下滑动的时候移动.
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRlv.setLayoutManager(manager);

        ArrayList<MeiziBean.ResultsBean> list = new ArrayList<>();
        mAdapter = new RlvMeiziAdater(getContext(),list);
        mRlv.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {
        mSrl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //加载更多
                mPage++;
                mPresenter.getData(mPage);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //下拉刷新
                mAdapter.mList.clear();
                mPage = 1;
                mPresenter.getData(mPage);
            }
        });
    }
}
