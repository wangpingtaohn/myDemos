package com.wpt.mydemos.kotlins

/**
 * Author: wpt
 * Time: 2022/1/20
 *
 * @Desc：
 */
class SingleInstance  //构造器私有化
private constructor() {

    companion object {
        fun getInstance(): SingleInstance {
            return SingletonHolder.mInstance
        }
    }

    private object SingletonHolder {
        //静态内部类
        val mInstance: SingleInstance = SingleInstance()
    }

    fun doSomeThings() {
        println("do some things")
    }


}