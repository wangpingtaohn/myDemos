package com.wpt.mydemos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        letTest("wpt")
        withTest(MyBean("wpt",22))
    }


    private fun letTest(str:String?){
        str?.let {
            Log.d("wpt_log",it)
            Log.d("wpt_log", it.length.toString())
        }
    }

    private fun withTest(bean:MyBean?){
        if (bean == null){
            return
        }
        with(bean){
            Log.d("wpt_log","name=$name")
            Log.d("wpt_log","age=$age")
        }
    }


    class MyBean(name:String,age:Int){
        var name = name
        var age = age
    }
}
