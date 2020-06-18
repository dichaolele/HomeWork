package com.example.news.fragment;

import com.example.news.R;
import com.example.news.base.BaseFragment;
import com.example.news.presenter.SettingsPresenter;
import com.example.news.view.SettingsView;


public class SettingsFragment extends BaseFragment<SettingsPresenter> implements SettingsView {

    public static BaseFragment newInstance(){
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    @Override
    protected SettingsPresenter initPresenter() {
        return new SettingsPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_settings;
    }
}
