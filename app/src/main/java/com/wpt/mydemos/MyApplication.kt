package com.wpt.mydemos

import android.app.Application
import androidx.emoji.text.EmojiCompat
import androidx.emoji.bundled.BundledEmojiCompatConfig
import com.idlefish.flutterboost.FlutterBoost
import com.idlefish.flutterboost.interfaces.INativeRouter
import com.idlefish.flutterboost.Utils
import com.wpt.mydemos.flutter.PageRouter
import io.flutter.embedding.android.FlutterView






/**
 *    author : wpt
 *    date   : 2019-10-19 10:41
 *    desc   :
 */
class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        val config:EmojiCompat.Config = BundledEmojiCompatConfig(this)
        EmojiCompat.init(config)

//        initFlutter()
    }

    private fun initFlutter(){
        val router =
            INativeRouter { context, url, urlParams, _, _ ->
                val assembleUrl = Utils.assembleUrl(url, urlParams)
                PageRouter.openPageByUrl(context, assembleUrl, urlParams)
            }

        val boostLifecycleListener = object : FlutterBoost.BoostLifecycleListener {

            override fun beforeCreateEngine() {

            }

            override fun onEngineCreated() {

            }

            override fun onPluginsRegistered() {

            }

            override fun onEngineDestroy() {

            }

        }

        //
        // AndroidManifest.xml 中必须要添加 flutterEmbedding 版本设置
        //
        //   <meta-data android:name="flutterEmbedding"
        //               android:value="2">
        //    </meta-data>
        // GeneratedPluginRegistrant 会自动生成 新的插件方式　
        //
        //
        val platform = FlutterBoost.ConfigBuilder(this, router)
            .isDebug(true)
            .whenEngineStart(FlutterBoost.ConfigBuilder.ANY_ACTIVITY_CREATED)
            .renderMode(FlutterView.RenderMode.texture)
            .lifecycleListener(boostLifecycleListener)
            .build()

        FlutterBoost.instance().init(platform)
    }
}