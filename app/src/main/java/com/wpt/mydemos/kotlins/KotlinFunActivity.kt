package com.wpt.mydemos.kotlins

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.activity_kotlin_fun.*
import java.lang.StringBuilder

class KotlinFunActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_fun)

        initView()
    }

    private fun initView(){
        val content = letTest("let") + "\n" + withTest(MyBean("with", 22)) + "\n" +
        runTest(MyBean("run", 26)) + "\n" + applyTest(MyBean("apply", 26)) + "\n" +
                alsoTest("also")
        textview.text = content

        var text2Str = StringBuilder()
        var bean = TestBean()
        text2Str.append("TestBean::class=")
            .append(TestBean::class)
            .append("\n")
            .append("TestBean::class.java=")
            .append(TestBean::class.java)
            .append("\n")
            .append("bean::class=")
            .append(bean::class)
            .append("\n")
            .append("bean.javaClass=")
            .append(bean.javaClass)
        textview2.text = text2Str
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

    /**
     * 1.去掉了let的it调用
     * 2.不用对传入的参数null判断
     * 3.run函数返回本身
     */
    private fun alsoTest(str: String?): String? {
        var result = str?.also {
            Log.d("wpt_log", it)
            Log.d("wpt_log", it.length.toString())
            "return also result"
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
