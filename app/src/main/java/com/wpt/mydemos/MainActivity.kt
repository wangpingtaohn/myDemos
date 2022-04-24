package com.wpt.mydemos

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.util.Log
import androidx.core.content.ContextCompat
import com.wpt.mydemos.utils.AppUtils
import com.wpt.mydemos.widget.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    private val mainBean = MainBean()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("===wpt===","onCreate_versionCode=${AppUtils.getVersionCode(this)}")

        initView()
        if(Build.VERSION.SDK_INT>=23){
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 11)
            }
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 11)
            }

        }

        val homeFilter = IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)

        val myReceiver = MyReceiver()

        registerReceiver(myReceiver, homeFilter)

        shimmerLayout.setShimmerColor(ContextCompat.getColor(this,R.color.white))

        enter_pip.setOnClickListener {
            if(Build.VERSION.SDK_INT>=24){
                enterPictureInPictureMode()
            }
        }

        var isStarAnimation = false
        skeleton.setOnClickListener {
            if (isStarAnimation) {
                shimmerLayout.stopShimmerAnimation()
            } else {
                shimmerLayout.startShimmerAnimation()
            }
            isStarAnimation = !isStarAnimation
        }

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        Log.d("===wpt===","onPostCreate")
    }

    class MyReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val reason = intent!!.getStringExtra("reason")
            Log.d("===wpt===","reason=$reason")
        }

    }

    private fun initView(){
        var fragment = MainFragment()
        var ft = supportFragmentManager.beginTransaction()
        mainBean.name = "Jim"
        fragment.mainBean = mainBean
        ft.add(R.id.frg_content,fragment)
        ft.commitAllowingStateLoss()

    }
    //跳转其他Activity、home键时都执行了
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("===wpt===","onSaveInstanceState")
    }

    //跳转其他Activity、home键时都执行了
    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        Log.d("===wpt===","onUserLeaveHint")
    }

    fun setMainBean(){
        mainBean.name = "Tom"
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("===wpt===","onRestart")
    }


    override fun onResume() {
        super.onResume()
        Log.d("===wpt===","onResume")
    }

    override fun finish() {
        super.finish()
        Log.d("===wpt===","finish")
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        Log.d("===wpt===","onUserInteraction")
    }

}
