package com.wpt.mydemos.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * author : wpt
 * date   : 2020-12-31 10:16
 * desc   :
 */
public class Utils {

    public static int getScreenW(Context mContext) {
        Resources resources = mContext.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();

        return dm.widthPixels;
    }

    public static int getScreenH(Context mContext) {
        Resources resources = mContext.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();

        return dm.heightPixels;
    }
}
