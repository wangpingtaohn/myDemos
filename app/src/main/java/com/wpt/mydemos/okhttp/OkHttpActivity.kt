package com.wpt.mydemos.okhttp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.wpt.mydemos.R
import com.wpt.mydemos.widget.BaseActivity
import kotlinx.android.synthetic.main.activity_ok_http.*
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit

class OkHttpActivity : BaseActivity() {

    private var okHttpClient: OkHttpClient? = null

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

        val request = Request.Builder().url("https://www.baifubao.com/callback?cmd=1059&callback=phone&phone=13700449338")
            .build()
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.sslSocketFactory(SSLContextUtil.getDefaultSLLContext().socketFactory)
        okHttpClient = okHttpBuilder.build()
        okHttpBuilder.connectTimeout(30, TimeUnit.SECONDS)
        okHttpBuilder.readTimeout(30, TimeUnit.SECONDS)
        okHttpBuilder.writeTimeout(30, TimeUnit.SECONDS)
        val call = okHttpClient?.newCall(request)
        call?.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    tv_respond.text = e.message
                }

            }

            override fun onResponse(call: Call, response: Response) {
                Handler(Looper.getMainLooper()).post {
                    tv_respond.text = response.toString()
                }
            }

        })
    }
}
