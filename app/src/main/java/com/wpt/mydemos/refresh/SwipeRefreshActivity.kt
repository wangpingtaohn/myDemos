package com.wpt.mydemos.refresh

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.core.content.ContextCompat
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.activity_swipe_refresh.*

class SwipeRefreshActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_refresh)

        initView()
        swipe_layout.setProgressBackgroundColorSchemeColor(resources.getColor(R.color.translucent))
    }

    private fun initView(){
        val content = "但各家力能扛鼎恩静冷"
        val ssb = SpannableStringBuilder(content)
        val styleSpan = StyleSpan(Typeface.NORMAL)
        ssb.setSpan(styleSpan, 3, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//        val colorSpan = ForegroundColorSpan(ContextCompat.getColor(this,R.color.color_1CA0F1))
//        ssb.setSpan(colorSpan, 3, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        tv_swipe.text = ssb

        tv_black.typeface = Typeface.DEFAULT_BOLD
    }
}
