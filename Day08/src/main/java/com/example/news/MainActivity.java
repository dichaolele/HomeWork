package com.example.news;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.news.base.BaseActivity;
import com.example.news.base.BaseApp;
import com.example.news.base.BaseFragment;
import com.example.news.fragment.AboutFragment;
import com.example.news.fragment.CollectFragment;
import com.example.news.fragment.GankFragment;
import com.example.news.fragment.GoldFragment;
import com.example.news.fragment.SettingsFragment;
import com.example.news.fragment.V2exFragment;
import com.example.news.fragment.WechatFragment;
import com.example.news.fragment.ZhihuFragment;
import com.example.news.presenter.MainPresenter;
import com.example.news.view.MainView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

//打包:将我们写好的应用代码打包成一个可以在Android设备上运行的程序,.apk结尾的文件
//Android设备区分不同程序,通过包名区分的
//打包的时候需要 签名文件
public class MainActivity extends BaseActivity<MainPresenter>
        implements MainView {

    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.fl_container)
    FrameLayout mFlContainer;
    @BindView(R.id.nv)
    NavigationView mNv;
    @BindView(R.id.dl)
    DrawerLayout mDl;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;
    private ArrayList<BaseFragment> mFragments;
    private FragmentManager mManager;
    //fragment的类型,同时也是索引
    private static final int TYPE_ZHIHU = 0;
    private static final int TYPE_WECHAT = 1;
    private static final int TYPE_GANK = 2;
    private static final int TYPE_GOLD = 3;
    private static final int TYPE_V2EX = 4;
    private static final int TYPE_COLLECT = 5;
    private static final int TYPE_SETTINGS = 6;
    private static final int TYPE_ABOUT = 7;
    private int mLastFragmentPosition = 0;
    private ArrayList<String> mTitles;
    private  MenuItem item;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;

    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void initView() {
        mManager = getSupportFragmentManager();
        mToolBar.setTitle(BaseApp.getRes().getString(R.string.zhihu));
        setSupportActionBar(mToolBar);

        //旋转开关
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDl, mToolBar, R.string.about, R.string.about);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorAccent));
        mDl.addDrawerListener(toggle);
        toggle.syncState();

        initTitles();
        //初始化Fragment
        initFragments();
        //一上来显示知乎fragment
        addZhihuFragment();
    }

    private void initTitles() {
        mTitles = new ArrayList<>();
        mTitles.add(BaseApp.getRes().getString(R.string.zhihu));
        mTitles.add(BaseApp.getRes().getString(R.string.wechat));
        mTitles.add(BaseApp.getRes().getString(R.string.gank));
        mTitles.add(BaseApp.getRes().getString(R.string.gold));
        mTitles.add(BaseApp.getRes().getString(R.string.v2ex));
        mTitles.add(BaseApp.getRes().getString(R.string.collect));
        mTitles.add(BaseApp.getRes().getString(R.string.settings));
        mTitles.add(BaseApp.getRes().getString(R.string.about));
    }

    private void addZhihuFragment() {
        mManager.beginTransaction()
                .add(R.id.fl_container, mFragments.get(0))
                .commit();
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(ZhihuFragment.newInstance());
        mFragments.add(WechatFragment.newInstance());
        mFragments.add(GankFragment.newInstance());
        mFragments.add(GoldFragment.newInstance());
        mFragments.add(V2exFragment.newInstance());
        mFragments.add(CollectFragment.newInstance());
        mFragments.add(SettingsFragment.newInstance());
        mFragments.add(AboutFragment.newInstance());
    }

    @Override
    protected void initListener() {
        //navigationView给他包含的menu设置点击监听
        mNv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                //避免资讯和选项选中
                if (itemId != R.id.item_info && itemId != R.id.item_option) {
                    menuItem.setChecked(true);
                }

                //切换Fragment
                switch (itemId) {
                    case R.id.zhihu:
                        switchFragments(TYPE_ZHIHU);
                        break;
                    case R.id.wechat:
                        switchFragments(TYPE_WECHAT);
                        break;
                    case R.id.gank:
                        switchFragments(TYPE_GANK);
                        break;
                    case R.id.gold:
                        switchFragments(TYPE_GOLD);
                        break;
                    case R.id.v2ex:
                        switchFragments(TYPE_V2EX);
                        break;
                    case R.id.collect:
                        switchFragments(TYPE_COLLECT);
                        break;
                    case R.id.settings:
                        switchFragments(TYPE_SETTINGS);
                        break;
                    case R.id.about:
                        switchFragments(TYPE_ABOUT);
                        break;
                }
                //关闭侧滑
                mDl.closeDrawer(Gravity.LEFT);
                return false;
            }
        });
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //显示
            }

            @Override
            public void onSearchViewClosed() {
                //隐藏
            }
        });

    }

    private void switchFragments(int type) {
        FragmentTransaction transaction = mManager.beginTransaction();
        BaseFragment showFragment = mFragments.get(type);
        //fragment只能添加一次,除非移除了
        if (!showFragment.isAdded()) {
            transaction.add(R.id.fl_container, showFragment);
        }

        BaseFragment hideFragment = mFragments.get(mLastFragmentPosition);
        transaction.hide(hideFragment);
        //显示
        transaction.show(showFragment);
        transaction.commit();

        //当前显示的Fragment就是下次需要隐藏的
        mLastFragmentPosition = type;

        //切换标题
        mToolBar.setTitle(mTitles.get(type));


        if(type==TYPE_WECHAT || type==TYPE_GANK){
            item.setVisible(true);
        }else{
            item.setVisible(false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);

         item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        item.setVisible(false);

        return true;


    }
    @Override
    public void onBackPressed() {
        /*if (搜索框显示){
            关闭搜索框
        }else {
            super.onBackPressed();
        }*/

        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }
}
