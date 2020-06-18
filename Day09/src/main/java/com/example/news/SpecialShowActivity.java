package com.example.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.news.adapter.RlvSpecialShowAdapter;
import com.example.news.bean.GoldTabBean;
import com.example.news.widget.SimpleTouchCallBack;

import java.util.ArrayList;

public class SpecialShowActivity extends AppCompatActivity {

    public static final String DATA = "data";
    private RecyclerView mRlv;
    private ArrayList<GoldTabBean> mTitles;
    private RlvSpecialShowAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_show);
        mTitles = (ArrayList<GoldTabBean>) getIntent().getSerializableExtra(DATA);
        initView();
    }

    private void initView() {
        mRlv = (RecyclerView) findViewById(R.id.rlv);
        mAdapter = new RlvSpecialShowAdapter(mTitles);
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        mRlv.setAdapter(mAdapter);

        SimpleTouchCallBack simpleTouchCallBack = new SimpleTouchCallBack(mAdapter);
        simpleTouchCallBack.setSwipeEnable(false);
        ItemTouchHelper helper = new ItemTouchHelper(simpleTouchCallBack);
        helper.attachToRecyclerView(mRlv);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(DATA,mAdapter.mTitles);
        setResult(RESULT_OK,intent);
        super.onBackPressed();
    }
}
