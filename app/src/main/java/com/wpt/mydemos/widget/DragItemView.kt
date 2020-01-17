package com.wpt.mydemos.widget

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.widget.LinearLayout
import com.wpt.mydemos.R

/**
 * author : wpt
 * date   : 2020-01-16 17:12
 * desc   : 兼容滑动时不显示点击效果的ViewGroup
 */
class DragItemView : LinearLayout, View.OnTouchListener {

    private val MSG_TINT = 1
    private val MSG_TINT_2 = 2
    private val TAP_TIMEOUT = ViewConfiguration.getTapTimeout().toLong()
    private var downX: Float = 0.toFloat()
    private var downY: Float = 0.toFloat()
    private var touchSlop: Int = 0
    private var mHandler: Handler? = null


    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun init() {

        mHandler = Handler(Handler.Callback { msg ->
            when (msg.what) {
                MSG_TINT -> {
                    this@DragItemView.setBackgroundColor(resources.getColor(R.color.color_DFDFDF))
                }
                MSG_TINT_2 -> {
                    this@DragItemView.setBackgroundColor(resources.getColor(R.color.color_ffffff))
                }
            }
            false
        })
        setOnTouchListener(this)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y
                mHandler!!.sendEmptyMessageDelayed(MSG_TINT, TAP_TIMEOUT)
            }
            MotionEvent.ACTION_MOVE -> {
                val dx = event.x - downX
                val dy = event.y - downY
                if (touchSlop == 0) {
                    touchSlop = ViewConfiguration.get(context).scaledTouchSlop
                }
                if (dy > touchSlop) {
                    mHandler!!.sendEmptyMessageDelayed(MSG_TINT_2, TAP_TIMEOUT)
                    mHandler!!.removeMessages(MSG_TINT)
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                mHandler!!.sendEmptyMessageDelayed(MSG_TINT_2, TAP_TIMEOUT)
            }
        }
        return false
    }
}
