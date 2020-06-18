package com.example.news.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.news.R;
import com.example.news.base.BaseRlvAdapter;
import com.example.news.bean.GoldTabBean;
import com.example.news.widget.TouchCallBack;

import java.util.ArrayList;
import java.util.Collections;

public class RlvSpecialShowAdapter extends BaseRlvAdapter implements TouchCallBack {
    public ArrayList<GoldTabBean> mTitles;

    public RlvSpecialShowAdapter(ArrayList<GoldTabBean> titles) {

        mTitles = titles;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new VH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_special,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        VH vh = (VH) viewHolder;
        final GoldTabBean bean = mTitles.get(position);
        //根据数据设置界面
        vh.mTvTitle.setText(bean.title);
        vh.mSc.setChecked(bean.isChecked);
        //根据界面改变设置数据
        vh.mSc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //这个方法使用代码设置状态也会回调
                //在这里需要判断是否为用户行为,如果是,修改数据
                if (buttonView.isPressed()){
                    bean.isChecked = isChecked;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        //交换集合中两个位置的数据
        Collections.swap(mTitles,fromPosition,toPosition);
        notifyItemMoved(fromPosition,toPosition);
    }

    @Override
    public void onItemDelete(int position) {
        mTitles.remove(position);
        notifyDataSetChanged();
    }

    class VH extends RecyclerView.ViewHolder{

        private final TextView mTvTitle;
        private final SwitchCompat mSc;

        public VH(@NonNull View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tv_title);
            mSc = itemView.findViewById(R.id.sc);
        }
    }
}
