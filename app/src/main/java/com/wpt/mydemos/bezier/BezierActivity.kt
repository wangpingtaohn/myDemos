package com.wpt.mydemos.bezier

import android.os.Bundle
import com.wpt.mydemos.R
import com.wpt.mydemos.widget.BaseActivity
import kotlinx.android.synthetic.main.activity_bezier.*

class BezierActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bezier)

        bezierRoot.setOnClickListener {
            myGift.addImageView()
        }
    }
}