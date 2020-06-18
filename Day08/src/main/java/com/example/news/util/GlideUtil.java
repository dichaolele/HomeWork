package com.example.news.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class GlideUtil {
    /**
     * 设置网络图片/SD卡图片
     * @param context
     * @param url 图片链接
     * @param iv
     */
    public static void setImage(Context context, String url, ImageView iv){
        Glide.with(context).load(url).into(iv);
    }
    /**
     * 设置网络图片
     * @param context
     * @param resId 图片链接
     * @param iv
     */
    public static void setImage(Context context, int resId, ImageView iv){
        Glide.with(context).load(resId).into(iv);
    }

    /**
     * 加载圆形图片
     * @param context
     * @param url
     * @param iv
     */
    public static void setImageCircle(Context context, String url, ImageView iv){
        RequestOptions options = new RequestOptions().circleCrop();
        Glide.with(context).load(url).apply(options).into(iv);
    }

    /**
     * 设置图片
     * @param context
     * @param url
     * @param resId 占位图片资源id
     * @param iv
     */
    public static void setImage(Context context,String url,int resId,ImageView iv){
        RequestOptions options = new RequestOptions().placeholder(resId);
        Glide.with(context).load(url).apply(options).into(iv);
    }
}
