package com.wpt.mydemos

import android.app.Activity
import android.os.Bundle
import android.util.Log

class MainActivity : Activity() {

    private val mainBean = MainBean()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

    }

    private fun initView(){
        var fragment = MainFragment()
        var ft = fragmentManager.beginTransaction()
        mainBean.name = "Jim"
        fragment.mainBean = mainBean
        ft.add(R.id.frg_content,fragment)
        ft.commitAllowingStateLoss()

    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("MainActivity","onSaveInstanceState")
    }

    fun setMainBean(){
        mainBean.name = "Tom"
    }
}
