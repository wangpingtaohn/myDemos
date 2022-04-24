package com.zhongke.videoplayer.ui.utils;

import android.content.Context;

/**
 * Created by cxf on 2017/8/9.
 * dp转px工具类
 */

public class DkDpUtil {

    private static float scale;

    public static int dp2px(Context context,int dpVal) {
        scale = context.getResources().getDisplayMetrics().density;
        return (int) (scale * dpVal + 0.5f);
    }

    public static int dp2px(Context context,float dp) {
        scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * 获取屏幕高度(px)
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕宽度(px)
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}
