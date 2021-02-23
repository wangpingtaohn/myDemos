package com.wpt.mydemos.okhttp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.wpt.mydemos.R
import com.wpt.mydemos.widget.BaseActivity
import kotlinx.android.synthetic.main.activity_ok_http.*
import okhttp3.*
import java.io.IOException

class OkHttpActivity : BaseActivity() {

    private val okHttpClient = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ok_http)

        initView()
    }

    private fun initView(){
        request_btn.setOnClickListener {
            getData()
        }
    }

    private fun getData(){

        val request = Request.Builder().url("https://www.baidu.com/")
            .build()
        val call = okHttpClient.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                tv_respond.text = e.message
            }

            override fun onResponse(call: Call, response: Response) {
                Handler(Looper.getMainLooper()).post {
                    tv_respond.text = response.message()
                }
            }

        })
    }
}
