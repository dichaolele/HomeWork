package com.example.zy.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.zy.R;
import com.example.zy.ui.fragments.Contact_person;
import com.example.zy.ui.fragments.Dynamic;
import com.example.zy.ui.fragments.Message_Shou;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mVp;
    private TabLayout mTab;
    private DrawerLayout mDrawer;
    private NavigationView mNavigation;
    private Toolbar mToolb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
        mTab = (TabLayout) findViewById(R.id.tab);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer);
        mNavigation = (NavigationView) findViewById(R.id.navigation);
        mToolb = (Toolbar) findViewById(R.id.toolb);



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer,mToolb, R.string.open, R.string.close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        Navigation_linner();


        //绑定
        List<Fragment> list = new ArrayList<>();
        list.add(new Message_Shou().getThisAns());
        list.add(new Contact_person().getThisAns().getThisAns());
        list.add(new Dynamic().getThisAns());

        View viewtab1 = LayoutInflater.from(this).inflate(R.layout.tabview, null);
        View viewtab2 = LayoutInflater.from(this).inflate(R.layout.tabviewt, null);
        View viewtab3 = LayoutInflater.from(this).inflate(R.layout.tabviewtr, null);

        MyVp_Ada myVp_ada = new MyVp_Ada(getSupportFragmentManager(), list);
        mVp.setAdapter(myVp_ada);
        mTab.setupWithViewPager(mVp);

        mTab.getTabAt(0).setCustomView(viewtab1);
        mTab.getTabAt(1).setCustomView(viewtab2);
        mTab.getTabAt(2).setCustomView(viewtab3);


    }

    public class MyVp_Ada extends FragmentPagerAdapter {

        List<Fragment> list;

        public MyVp_Ada(@NonNull FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

    public void Navigation_linner (){
        mNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                mDrawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

}
