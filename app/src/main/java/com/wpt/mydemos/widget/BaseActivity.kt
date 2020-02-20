package com.wpt.mydemos.widget

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;


open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
    }

    companion object {
        val LOG_TAG = javaClass.simpleName!!
    }

    //解决字体随系统调节而变化的问题
//    override fun getResources(): Resources {
//        val res = super.getResources()
//        val config = Configuration()
//        config.setToDefaults()
//        res.updateConfiguration(config,res.displayMetrics)
//        return res
//    }


}
