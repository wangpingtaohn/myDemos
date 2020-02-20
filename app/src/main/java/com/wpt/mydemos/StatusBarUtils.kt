package com.wpt.mydemos

import android.app.Activity
import android.graphics.Color
import android.os.Build
import androidx.core.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager

/**
 *    author : wpt
 *    date   : 2019-09-27 14:56
 *    desc   :
 */

//用object 修饰的类为静态类，里面的方法和变量都为静态的。
object StatusBarUtils {

    fun setStatusBarTransparent(activity: Activity,colorId:Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //            StatusBarUtil.setTransparent(activity);
            //            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            activity.window.statusBarColor = Color.TRANSPARENT
            activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

            //            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            val decorView = activity.window.decorView
            var vis = decorView.systemUiVisibility
            vis = vis or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vis = vis or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            }
            decorView.systemUiVisibility = vis
            //            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //                vis |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
            //                decorView.setSystemUiVisibility(vis);
            //                activity.getWindow().setNavigationBarColor(ContextCompat.getColor(activity, R.color.head_bg_cocor));
            //            } else {
            activity.window.navigationBarColor = ContextCompat.getColor(activity, colorId)
            //            }
            setRootView(activity)
        }

    }

    private fun setRootView(activity: Activity) {
        val parent = activity.findViewById<ViewGroup>(android.R.id.content)
        var i = 0
        val count = parent.childCount
        while (i < count) {
            val childView = parent.getChildAt(i)
            if (childView is ViewGroup) {
                childView.setFitsSystemWindows(true)
                childView.clipToPadding = true
            }
            i++
        }
    }
}