package com.wpt.mydemos.animator

import android.os.Bundle
import com.wpt.mydemos.widget.BaseActivity
import kotlinx.android.synthetic.main.activity_animator.*
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.animation.*
import com.wpt.mydemos.R


class AnimatorActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animator)

        initView()
    }


    private fun initLottieView(){
       lottieView.imageAssetsFolder = "haipin3X"
        lottieView.setAnimation("haipin.json")
        lottieView.loop(true)
        lottieView.playAnimation()
    }

    private fun initView(){
        initLottieView()
        val rotate = ObjectAnimator.ofFloat(img_animator, "rotation", 0f, -359f)
            .setDuration(2000)
        rotate.interpolator = LinearInterpolator()
        rotate.repeatCount = ValueAnimator.INFINITE
        rotate.repeatMode = ValueAnimator.RESTART

        val rotateAnimation: AnimationSet = AnimationUtils.loadAnimation(this, R.anim.rotate_anim_list) as AnimationSet
        rotateAnimation.interpolator = AccelerateDecelerateInterpolator()

        val rotateAnimation1: Animation = RotateAnimation(
            0f,
            45f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        val rotateAnimation2: Animation = RotateAnimation(
            45f,
            0f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        val rotateAnimation3: Animation = RotateAnimation(
            0f,
            -45f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        val rotateAnimation4: Animation = RotateAnimation(
            -45f,
            0f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )

        rotateAnimation1.duration = 200
        rotateAnimation1.fillAfter = true
        rotateAnimation1.setInterpolator(
            this,
            android.R.anim.accelerate_decelerate_interpolator
        ) //设置动画插入器
        rotateAnimation2.duration = 100
        rotateAnimation2.fillAfter = true
        rotateAnimation2.setInterpolator(
            this,
            android.R.anim.accelerate_decelerate_interpolator
        ) //
        rotateAnimation3.duration = 200
        rotateAnimation3.fillAfter = true
        rotateAnimation3.setInterpolator(
            this,
            android.R.anim.accelerate_decelerate_interpolator
        ) //
        rotateAnimation4.duration = 100
        rotateAnimation4.fillAfter = true
        rotateAnimation4.setInterpolator(
            this,
            android.R.anim.accelerate_decelerate_interpolator
        ) //

        rotateAnimation1.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(animation: Animation?) {
                img_animator2.startAnimation(rotateAnimation2)
            }

            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }

        })


        rotateAnimation2.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(animation: Animation?) {
                img_animator2.startAnimation(rotateAnimation3)
            }

            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }

        })
        rotateAnimation3.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(animation: Animation?) {
                img_animator2.startAnimation(rotateAnimation4)
            }

            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }

        })
        rotateAnimation4.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(animation: Animation?) {
                img_animator2.startAnimation(rotateAnimation1)
            }

            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }

        })


        btn_animator_start.setOnClickListener {
            rotate.start()
            img_animator2.startAnimation(rotateAnimation)
//            img_animator2.startAnimation(rotateAnimation1)
        }

        btn_animator_stop.setOnClickListener {
            rotate.cancel()

        }

    }
}
