package com.example.news.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.news.R;
import com.example.news.base.BaseFragment;
import com.example.news.presenter.AboutPresenter;
import com.example.news.util.LogUtil;
import com.example.news.view.AboutView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.provider.MediaStore.MediaColumns.TITLE;


public class AboutFragment extends BaseFragment<AboutPresenter> implements AboutView {

    @BindView(R.id.tv)
    TextView tv;
    Unbinder unbinder;
    private String mTitle;

    public static BaseFragment newInstance() {
        AboutFragment fragment = new AboutFragment();
        return fragment;
    }

    public static BaseFragment newInstance(String title) {
        AboutFragment fragment = new AboutFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected AboutPresenter initPresenter() {
        return new AboutPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about;
    }

    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            mTitle = arguments.getString(TITLE);
        }
        tv.setText(mTitle);

        LogUtil.print("onCreateView:" + mTitle);
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.print("onPause:" + mTitle);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.print("onDestroyView:" + mTitle);
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.print("onDestroy:" + mTitle);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtil.print("onDetach:" + mTitle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
