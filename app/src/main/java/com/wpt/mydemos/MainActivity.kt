package com.wpt.mydemos

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import com.wpt.mydemos.widget.BaseActivity

class MainActivity : BaseActivity() {

    private val mainBean = MainBean()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        if(Build.VERSION.SDK_INT>=23){
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 11)
            }
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
        Log.d("MainActivity","onSaveInstanceState")
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        Log.d("MainActivity","onUserLeaveHint")
    }

    fun setMainBean(){
        mainBean.name = "Tom"
    }

}
