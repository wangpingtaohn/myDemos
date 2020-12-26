package com.wpt.mydemos.fling

import android.os.Bundle
import com.wpt.mydemos.R
import android.view.GestureDetector
import android.view.MotionEvent
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class  RightToJumpActivity : AppCompatActivity() {

    companion object {
        const val FLING_MIN_DISTANCE = 50//滑动最小的距离
        const val FLING_MIN_VELOCITY = 0//滑动最小的速度
    }

    var mGestureDetector: GestureDetector? = null  //手势检测对象

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_right_to_jump)

        mGestureDetector = GestureDetector(this, MyGestureListener())
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        mGestureDetector!!.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    inner class MyGestureListener : GestureDetector.SimpleOnGestureListener(){
        override fun onFling(
            e1: MotionEvent,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
                Toast.makeText(this@RightToJumpActivity, "向左手势", Toast.LENGTH_SHORT).show()
                val leftIntent = Intent(this@RightToJumpActivity, LeftActivity::class.java)
                startActivity(leftIntent)
                //滑动动画
                overridePendingTransition(
                    R.anim.out_to_right,
                    R.anim.out_to_left
                )
            } else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
                Toast.makeText(this@RightToJumpActivity, "向右手势", Toast.LENGTH_SHORT).show()
                val rightIntent = Intent(this@RightToJumpActivity, RightActivity::class.java)
                startActivity(rightIntent)
                //滑动动画
                overridePendingTransition(
                    R.anim.in_from_left,
                    R.anim.out_to_right
                )
            }
            return false
        }
    }
}
