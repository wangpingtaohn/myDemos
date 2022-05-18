package com.wpt.mydemos.webview

import android.annotation.SuppressLint
import android.net.http.SslError
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tencent.smtt.export.external.interfaces.SslErrorHandler
import com.tencent.smtt.export.external.interfaces.WebResourceError
import com.tencent.smtt.export.external.interfaces.WebResourceRequest
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.activity_web.*
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient


class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        val setting = webview.settings
        setting.domStorageEnabled = true
        setting.javaScriptEnabled = true
//        setting.userAgentString = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:54.0) Gecko/20100101 Firefox/54.0";
//        webview.loadUrl("file:///android_asset/jsTest.html")
//        webview.loadUrl("https://6tv.eu/static/app.apk")

        webview.webViewClient = object : WebViewClient(){

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                Log.d("===wpt===","onPageStarted=${url}")
            }

            //点击页面中的链接才会执行，第一次加载的url不执行
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: android.webkit.WebResourceRequest?
            ): Boolean {
                Log.d("===wpt===","shouldOverrideUrlLoadingNew=${request?.url}")
                return true
            }

            override fun onReceivedSslError(
                view: WebView?,
                handler: android.webkit.SslErrorHandler?,
                error: SslError?
            ) {
                super.onReceivedSslError(view, handler, error)
                handler?.proceed()
            }

            override fun onReceivedError(
                view: WebView?,
                request: android.webkit.WebResourceRequest?,
                error: android.webkit.WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                Log.d("===wpt===","onReceivedError=${request?.url}")
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                Log.d("===wpt===","onPageFinished_url=${url}")
            }
        }

        webview.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                Log.d("===wpt===","onProgressChanged_url=${view?.url},progress=$newProgress")
            }
        }
        webview.loadUrl("https://coronavirus.1point3acres.com/en")

        load_btn.setOnClickListener {


            webview.loadUrl("https://6tv.eu/static/app.apk")
            /*var count = 0
            while (count < 5){
                webview.loadUrl("https://www.bai777du.com")
                count++
            }*/
        }

        webview.setDownloadListener { url, userAgent, contentDisposition, mimetype, contentLength ->
            val currentUrl = url.toString()
            if (currentUrl.endsWith(".apk")) {
                val uri: Uri = Uri.parse(currentUrl)
                val viewIntent = Intent(Intent.ACTION_VIEW, uri)
                this@WebActivity.startActivity(viewIntent)
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
