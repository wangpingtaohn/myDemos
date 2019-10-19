package com.wpt.mydemos

import android.app.Application
import android.support.text.emoji.EmojiCompat
import android.support.text.emoji.bundled.BundledEmojiCompatConfig


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
    }
}