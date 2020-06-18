package com.example.news.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseRlvAdapter extends RecyclerView.Adapter {

    public OnItemClickListener mItemClickListener;
    public OnItemLongClickListener mItemLongClickListener;

    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mItemClickListener = listener;
    }


    public interface OnItemLongClickListener{
        void onItemLongClick(View v, int position);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener){
        this.mItemLongClickListener = listener;
    }
}
