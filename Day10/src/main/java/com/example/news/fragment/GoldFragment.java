package com.example.news.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.news.R;
import com.example.news.SpecialShowActivity;
import com.example.news.adapter.VpZhihuAdapter;
import com.example.news.base.BaseFragment;
import com.example.news.bean.GoldTabBean;
import com.example.news.presenter.GoldPresenter;
import com.example.news.view.GoldView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.Unbinder;


public class GoldFragment extends BaseFragment<GoldPresenter> implements GoldView , View.OnClickListener {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.vp)
    ViewPager vp;
    Unbinder unbinder;
    private ArrayList<BaseFragment> baseFragments;
    private ArrayList<GoldTabBean> goldTabBeans;

    public static BaseFragment newInstance() {
        GoldFragment fragment = new GoldFragment();
        return fragment;
    }

    @Override
    protected GoldPresenter initPresenter() {
        return new GoldPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gold;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    protected void initView() {
        super.initView();
        iv.setOnClickListener(this);
        initTitles();
        baseFragments = new ArrayList<>();
        initFragments();


        setVp();
    }

    private void setVp() {
        //为了复用VpZhihuAdapter,标题需要处理成泛型为String的集合
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < goldTabBeans.size(); i++) {
            GoldTabBean bean = goldTabBeans.get(i);
            if (bean.isChecked){
                list.add(bean.title);
            }
        }
        VpZhihuAdapter adapter = new VpZhihuAdapter(getChildFragmentManager(), baseFragments, list);
        vp.setAdapter(adapter);
        tabLayout.setupWithViewPager(vp);

    }

    private void initFragments() {
        baseFragments.clear();

        for (int i = 0; i < goldTabBeans.size(); i++) {
            GoldTabBean bean = goldTabBeans.get(i);
            if (bean.isChecked){
                baseFragments.add(AboutFragment.newInstance(bean.title));
            }
        }
    }

    private void initTitles() {
        goldTabBeans = new ArrayList<>();
        goldTabBeans.add(new GoldTabBean("Android",true));
        goldTabBeans.add(new GoldTabBean("产品",true));
        goldTabBeans.add(new GoldTabBean("阅读",true));
        goldTabBeans.add(new GoldTabBean("后端",true));
        goldTabBeans.add(new GoldTabBean("设计",true));
        goldTabBeans.add(new GoldTabBean("iOS",true));
        goldTabBeans.add(new GoldTabBean("前端",true));
        goldTabBeans.add(new GoldTabBean("工具资源",true));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv:
                go2Special();
                break;
        }
    }

    private void go2Special() {

        Intent intent = new Intent(getContext(), SpecialShowActivity.class);
        intent.putExtra(SpecialShowActivity.DATA,goldTabBeans);

        //Fragment中需要Activity的回传值,启动Activity只能使用startActivityForResult()
        startActivityForResult(intent,100);
        //下面的调用方式,activity的onActivityResult()会回调
        //getActivity().startActivityForResult(intent,100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null){
            if (requestCode == 100 && resultCode == Activity.RESULT_OK){
                goldTabBeans = (ArrayList<GoldTabBean>) data.getSerializableExtra(SpecialShowActivity.DATA);


                //设置界面
                initFragments();
                setVp();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
