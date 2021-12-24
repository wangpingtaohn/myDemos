package com.wpt.mydemos.webview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.webkit.WebViewClient
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        webview.webViewClient = WebViewClient()
        val setting = webview.settings
        setting.domStorageEnabled = true
        setting.javaScriptEnabled = true
        webview.loadUrl("file:///android_asset/jsTest.html");

        load_btn.setOnClickListener {


//            webview.loadUrl("https://mtest.fantuan.cn/articleContent/fC_EajeVm")
            webview.loadUrl("http://172.29.151.140/pinhn-apph5/appNewsDetails?articleId=6&uid=1245923&token=78574d8c43c3106124719b7b7c845569&share=2")
        }

        call_js_btn.setOnClickListener { callJs("哈哈哈","我们就是了") }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun callJs(vararg params: String){
        var temp = ""
        for (value in params){
            temp += ",$value"
        }
        val callName = "javascript:test('${temp}')"
        webview.loadUrl(callName)
    }

}
