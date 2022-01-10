package com.wpt.mydemos.webview

import android.annotation.SuppressLint
import android.net.http.SslError
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tencent.smtt.export.external.interfaces.SslErrorHandler
import com.tencent.smtt.export.external.interfaces.WebResourceError
import com.tencent.smtt.export.external.interfaces.WebResourceRequest
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        val setting = webview.settings
        setting.domStorageEnabled = true
        setting.javaScriptEnabled = true
        setting.userAgentString = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:54.0) Gecko/20100101 Firefox/54.0";
//        webview.loadUrl("file:///android_asset/jsTest.html")
        webview.loadUrl("https://www.zhihu.com/question/509128730/answer/2292756343")

        webview.webViewClient = object : WebViewClient(){

            override fun onReceivedSslError(
                p0: WebView?,
                p1: SslErrorHandler?,
                p2: com.tencent.smtt.export.external.interfaces.SslError?
            ) {
                super.onReceivedSslError(p0, p1, p2)
                p1?.proceed()
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                Log.d("===wpt===","onReceivedError=${request?.url}")
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                Log.d("===wpt===","onPageFinished_url=${url}")
            }
        }
//        webview.loadUrl("https://www.baidu.com")

        load_btn.setOnClickListener {


//            webview.loadUrl("https://mtest.fantuan.cn/articleContent/fC_EajeVm")
            var count = 0
            while (count < 5){
                webview.loadUrl("https://www.bai777du.com")
                count++
            }
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
