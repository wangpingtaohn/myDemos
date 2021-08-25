package com.wpt.mydemos.radar

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.appcompat.app.AppCompatActivity
import com.wpt.mydemos.R
import com.wpt.mydemos.widget.radar.RadarView
import kotlinx.android.synthetic.main.activity_radar.*

class RadarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_radar)
        initView()
    }

    private fun initView() {
       /* val rotateAnimation = RotateAnimation(
            0F, 360F,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 1f
        )
        rotateAnimation.duration = 2000
        rotateAnimation.repeatCount = Animation.INFINITE

        iv_scan.animation = rotateAnimation
        rotateAnimation.start()*/

        radar.setDirection(RadarView.ANTI_CLOCK_WISE)
        radar.start()

    }

    override fun onDestroy() {
        super.onDestroy()
        radar.stop()
    }
}