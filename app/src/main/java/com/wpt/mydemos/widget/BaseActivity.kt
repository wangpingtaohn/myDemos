package com.wpt.mydemos.widget

import android.content.res.Configuration
import android.content.res.Resources
import android.support.v7.app.AppCompatActivity;


open class BaseActivity : AppCompatActivity() {

    companion object {
        var LOG_TAG = javaClass.simpleName
    }

    override fun getResources(): Resources {
        val res = super.getResources()
        val config = Configuration()
        config.setToDefaults()
        res.updateConfiguration(config,res.displayMetrics)
        return res
    }


}
