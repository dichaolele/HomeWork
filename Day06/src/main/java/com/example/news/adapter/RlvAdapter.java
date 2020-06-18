package com.example.news.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.news.R;
import com.example.news.bean.Car;

import java.util.ArrayList;

public class RlvAdapter extends RecyclerView.Adapter {
    private ArrayList<Car> mCars;

    public RlvAdapter(ArrayList<Car> cars) {
        mCars = cars;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_date, null);
        return new Vh(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Car car = mCars.get(i);
        Vh vh = (Vh) viewHolder;
        vh.mTvDate.setText(car.name);
    }

    @Override
    public int getItemCount() {
        return mCars.size();
    }

    class Vh extends RecyclerView.ViewHolder{

        private final TextView mTvDate;

        public Vh(@NonNull View itemView) {
            super(itemView);
            mTvDate = itemView.findViewById(R.id.tv_date);
        }
    }
}
