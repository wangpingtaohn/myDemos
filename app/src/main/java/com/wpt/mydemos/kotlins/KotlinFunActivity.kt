package com.wpt.mydemos.kotlins

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.activity_kotlin_fun.*

class KotlinFunActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_fun)

        initView()
    }

    private fun initView(){
        val content = letTest("let") + "\n" + withTest(MyBean("with", 22)) + "\n" +
        runTest(MyBean("run", 26)) + "\n" + applyTest(MyBean("apply", 26))
        textview.text = content
    }


    /**
     * 1.可空的判断
     * 2.使用it调用对象的属性
     * 3.let函数返回最后一行代码的值或return值
     */
    private fun letTest(str: String?): String? {
        var result = str?.let {
            Log.d("wpt_log", it)
            Log.d("wpt_log", it.length.toString())
            "return let result=$it"
        }
        return result
    }

    /**
     * 1.没有可空的判断
     * 2.相对于let，不用使用it来调用
     * 3.with函数返回最后一行代码的值或return值
     */
    private fun withTest(bean: MyBean?): String? {
        if (bean == null) {
            return ""
        }
        var result = with(bean) {
            Log.d("wpt_log", "name=$name")
            Log.d("wpt_log", "age=$age")
            "return with result_name=$name ,age=$age"
        }

        return result
    }

    /**
     * 是let于with的结合体
     * 1.去掉了let的it调用
     * 2.不用对传入的参数null判断
     * 3.run函数返回最后一行代码的值或return值
     */
    private fun runTest(bean: MyBean?): String? {
        var result = bean?.run {
            Log.d("wpt_log", "run_name=$name")
            Log.d("wpt_log", "run_age=$age")
            "return run result_name=$name ,age=$age"
        }

        return result
    }

    /**
     * 1.去掉了let的it调用
     * 2.不用对传入的参数null判断
     * 3.run函数返回本身
     */
    private fun applyTest(bean: MyBean?):MyBean? {
        var result = bean?.apply {
            Log.d("wpt_log", "run_name=$name")
            Log.d("wpt_log", "run_age=$age")
        }

        return result
    }


    class MyBean(name: String, age: Int) {
        var name = name
        var age = age

        override fun toString(): String {
            return "【MyBean name=" + name + ",age=" + age + "】"

        }
    }
}
