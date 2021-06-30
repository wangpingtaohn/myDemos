///*
//package com.wpt.mydemos.flutter
//
//import android.graphics.Color
//import android.os.Bundle
//import android.util.Log
//import com.idlefish.flutterboost.containers.BoostFlutterActivity
//import android.view.View
//import android.view.ViewGroup
//import android.view.WindowManager
//import android.os.Build
//import com.wpt.mydemos.statusbar.MyStatusBarUtil.setStatusBarColor
//import androidx.core.content.ContextCompat.getSystemService
//import android.icu.lang.UCharacter.GraphemeClusterBreak.T
//import androidx.core.content.ContextCompat
//import com.wpt.mydemos.R
//
//
//class FlutterBaseActivity : BoostFlutterActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
////        setContentView(R.layout.activity_base_flutter);
//
//        Log.d("===wpt===","FlutterBaseActivity")
//
////        val window = window
////        val params = window.attributes
////        params.systemUiVisibility =
////            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE
////        window.attributes = params
//
////        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
////                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
////                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
////                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
////
////                or View.SYSTEM_UI_FLAG_IMMERSIVE)
////        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
//
////        val contentView = this.findViewById<View>(android.R.id.content) as ViewGroup
////        contentView.fitsSystemWindows = false
//        //给contentView上背景色 不上色会导致无法看到侧滑
////        val contentView = this.findViewById(android.R.id.content) as ViewGroup
////        contentView.setBackgroundColor(Color.parseColor("#ffffff"))
////        contentView.fitsSystemWindows = true
////
////        window.statusBarColor = Color.TRANSPARENT
////        MyStatusBarUtil.setRNBar(this,true)
////        StatusBarUtils.setStatusBarTransparent(this, R.color.blue)
//
////        val parent = activity.findViewById<ViewGroup>(android.R.id.content)
////        parent.fitsSystemWindows = true
////        var i = 0
////        val count = parent.childCount
////        while (i < count) {
////            val childView = parent.getChildAt(i)
////            if (childView is ViewGroup) {
////                childView.setFitsSystemWindows(true)
////                childView.clipToPadding = true
////            }
////            i++
////        }
////
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
////            // Android 5.0 以上 全透明
////            val window = window
////            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
////            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
////                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
////                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
////            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
////            // 状态栏（以上几行代码必须，参考setStatusBarColor|setNavigationBarColor方法源码）
////            window.statusBarColor = Color.TRANSPARENT
////            // 虚拟导航键
////            window.navigationBarColor = -0xf0f10
////        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
////            // Android 4.4 以上 半透明
////            val window = window
////            // 状态栏
////            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
////            // 虚拟导航键
////            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
////        }
////        window.decorView.fitsSystemWindows = true
////        window.navigationBarColor = ContextCompat.getColor(activity, R.color.blue)
//    }
//
//}
//*/
