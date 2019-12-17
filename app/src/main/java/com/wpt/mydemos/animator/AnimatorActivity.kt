package com.wpt.mydemos.animator

import android.os.Bundle
import com.wpt.mydemos.widget.BaseActivity
import kotlinx.android.synthetic.main.activity_animator.*
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.animation.LinearInterpolator
import com.wpt.mydemos.R


class AnimatorActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animator)

        initView()
    }

    private fun initView(){
        val rotate = ObjectAnimator.ofFloat(img_animator, "rotation", 0f, -359f)
            .setDuration(2000)
        rotate.interpolator = LinearInterpolator()
        rotate.repeatCount = ValueAnimator.INFINITE
        rotate.repeatMode = ValueAnimator.RESTART
        btn_animator_start.setOnClickListener {
            rotate.start()
        }

        btn_animator_stop.setOnClickListener {
            rotate.cancel()
        }
    }
}
