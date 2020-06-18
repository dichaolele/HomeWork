package com.example.news.base;

import android.app.Application;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class BaseApp extends Application {
    public static BaseApp sContext;
    public static int mWidthPixels;
    public static int mHeightPixels;
    public static Resources getRes() {
        return sContext.getResources();


    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.sContext=this;

        getScreenWH();

    }

    private void getScreenWH() {
        WindowManager systemService = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display defaultDisplay =systemService.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);

        mWidthPixels=displayMetrics.widthPixels;
        mHeightPixels=displayMetrics.heightPixels;


    }

}
