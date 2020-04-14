package com.wpt.mydemos.animator

import android.os.Bundle
import android.view.ViewGroup
import android.view.animation.Animation
import com.wpt.mydemos.R
import com.wpt.mydemos.widget.BaseActivity
import kotlinx.android.synthetic.main.activity_scale_animation.*
import android.view.animation.ScaleAnimation


class ScaleAnimationActivity : BaseActivity() {

    private lateinit var animation: ScaleAnimation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scale_animation)

        animation = ScaleAnimation(1f, 1.5f, 1f, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, 1, 0.5f)
        //设置持续时间
        animation.duration = 2000
        //设置动画结束之后的状态是否是动画的最终状态，true，表示是保持动画结束时的最终状态
        animation.fillAfter = true
        //设置循环次数，0为1次
        animation.repeatCount = 10

        initView()
    }

    private fun initView() {

        up_btn.setOnClickListener {
            iv_cover.startAnimation(animation)
        }

        up_btn2.setOnClickListener {
            var count = 0
            while (count < 10){
//                iv_cover.scaleX = (1 + (count / 10.0)).toFloat()
//                iv_cover.scaleY = (1 + (count / 10.0)).toFloat()
                count++
                val lp = iv_cover.layoutParams;
                lp.height = lp.height + count
                lp.width = lp.width + count
                iv_cover.layoutParams = lp
            }

        }

        down_btn.setOnClickListener {
            iv_cover.clearAnimation()
        }
    }
}
