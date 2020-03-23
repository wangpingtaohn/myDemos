package com.wpt.mydemos.webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)


        load_btn.setOnClickListener {
            webview.settings.javaScriptEnabled = true
            webview.webViewClient = WebViewClient()
            webview.loadUrl("https://mtest.fantuan.cn/articleContent/fC_EajeVm")
//            webview.loadUrl("https://www.baidu.com/")
        }
    }
}
