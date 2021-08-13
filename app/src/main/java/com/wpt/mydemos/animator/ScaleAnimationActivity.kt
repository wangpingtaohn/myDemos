package com.wpt.mydemos.animator

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.view.animation.Animation
import com.wpt.mydemos.R
import com.wpt.mydemos.widget.BaseActivity
import kotlinx.android.synthetic.main.activity_scale_animation.*
import android.view.animation.ScaleAnimation
import me.imid.swipebacklayout.lib.SwipeBackLayout
import me.imid.swipebacklayout.lib.app.SwipeBackActivity


class ScaleAnimationActivity : SwipeBackActivity() {

    private lateinit var animation: ScaleAnimation

    private var mSwipeBackLayout: SwipeBackLayout? = null

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

        mSwipeBackLayout = swipeBackLayout
        mSwipeBackLayout!!.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_ALL)
    }

    private fun initView() {


        val frameAnim = iv_frame.drawable as AnimationDrawable
        frameAnim.isOneShot = false

        var isStart = false


        frame_btn.setOnClickListener {
            if (isStart){
                frameAnim.stop()
            } else {
                frameAnim.start()
            }
            isStart = !isStart
        }

        var isStart2 = false


        faiv.setImageResource(R.drawable.animlist_video_love)
        faiv.setAnimDrawable(faiv.drawable as AnimationDrawable)
        frame_btn2.setOnClickListener {
            if (isStart2){
                faiv.stopAnimation()
            } else {
                faiv.startAnimation()
            }
            isStart2 = !isStart2
        }

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
