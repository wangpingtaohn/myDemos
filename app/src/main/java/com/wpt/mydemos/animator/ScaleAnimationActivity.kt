package com.wpt.mydemos.animator

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.ScaleAnimation
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.activity_scale_animation.*
import kotlinx.android.synthetic.main.sample_video_land.view.*
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


        val alphaAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.alpha_anim)
        tweenBtn.setOnClickListener {
            iv_cover.startAnimation(alphaAnimation)
        }

        val alphaAnim = ObjectAnimator.ofFloat(iv_cover, "alpha", 1.0f, 0.5f, 0.8f, 1.0f)
        val scaleXAnim = ObjectAnimator.ofFloat(iv_cover, "scaleX", 0.0f, 1.0f)
        val scaleYAnim = ObjectAnimator.ofFloat(iv_cover, "scaleY", 0.0f, 2.0f)
        val rotateAnim = ObjectAnimator.ofFloat(iv_cover, "rotation", 0f, 360f)
        val transXAnim = ObjectAnimator.ofFloat(iv_cover, "translationX", 100f, 400f)
        val transYAnim = ObjectAnimator.ofFloat(iv_cover, "tranlsationY", 100f, 750f)
        val set = AnimatorSet()
        set.playTogether(alphaAnim, scaleXAnim, scaleYAnim, rotateAnim, transXAnim, transYAnim)
//                set.playSequentially(alphaAnim, scaleXAnim, scaleYAnim, rotateAnim, transXAnim, transYAnim);
        //                set.playSequentially(alphaAnim, scaleXAnim, scaleYAnim, rotateAnim, transXAnim, transYAnim);
        set.duration = 3000

        objectBtn.setOnClickListener {
            set.start()
        }

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
            iv_cover.startAnimation(alphaAnimation)
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
