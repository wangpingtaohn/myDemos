package com.wpt.mydemos.elevation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.activity_elevation.*

class ElevationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_elevation)

        val ssb  = SpannableString("5位")
        ssb.setSpan(RelativeSizeSpan(0.5f),1,2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_elevation.text = ssb
    }
}
