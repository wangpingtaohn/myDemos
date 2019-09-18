package com.wpt.mydemos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("wpt_log",letTest("wpt"))
        Log.d("wpt_log",withTest(MyBean("wpt",22)))
        Log.d("wpt_log",runTest(MyBean("wpt",26)))
    }


    /**
     * 1.可空的判断
     * 2.使用it调用对象的属性
     */
    private fun letTest(str:String?): String? {
        var result = str?.let {
            Log.d("wpt_log",it)
            Log.d("wpt_log", it.length.toString())
            "return let result"
        }
        return result
    }

    /**
     * 1.没有可空的判断
     * 2.相对于let，不用使用it来调用
     */
    private fun withTest(bean:MyBean?):String?{
        if (bean == null){
            return ""
        }
        var result = with(bean){
            Log.d("wpt_log","name=$name")
            Log.d("wpt_log","age=$age")
            "return with result"
        }

        return result
    }

    /**
     * 是let于with的结合体
     * 1.去掉了let的it调用
     * 2.不用对传入的参数null判断
     */
    private fun runTest(bean:MyBean?):String?{
        var result = bean?.run {
            Log.d("wpt_log","run_name=$name")
            Log.d("wpt_log", "run_age=$age")
            "return run result"
        }

        return result
    }


    class MyBean(name:String,age:Int){
        var name = name
        var age = age
    }
}
