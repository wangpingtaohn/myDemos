package com.wpt.mydemos.animator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wpt.mydemos.R

class Fragment3DActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment3_dactivity)

        initView()
    }

    private fun initView(){
        var fragment = My3DFragment()
        var ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.fl_frg,fragment)
        ft.commitAllowingStateLoss()

    }
}