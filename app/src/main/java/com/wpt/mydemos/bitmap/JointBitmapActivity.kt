package com.wpt.mydemos.bitmap

import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.wpt.mydemos.R
import com.wpt.mydemos.widget.JointBitmapView

/**
 * 拼接图
 */
class JointBitmapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(joinBitmap())
    }

    private fun joinBitmap(): View{
        var dw1 = ContextCompat.getDrawable(this, R.drawable.bg_gh_cover)
        var bitmap1 = (dw1 as BitmapDrawable).bitmap

        var dw2 = ContextCompat.getDrawable(this, R.drawable.bg_splash)
        var bitmap2 = (dw2 as BitmapDrawable).bitmap
        return JointBitmapView(this,bitmap1,bitmap2)
    }
}