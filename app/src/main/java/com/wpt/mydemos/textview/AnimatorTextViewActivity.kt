package com.wpt.mydemos.textview

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wpt.mydemos.R
import com.wpt.mydemos.utils.MyStringUtil
import kotlinx.android.synthetic.main.activity_animator_text_view.*
import android.annotation.SuppressLint
import android.graphics.Rect
import android.text.TextPaint
import android.content.Context


class AnimatorTextViewActivity : AppCompatActivity() {


    var num = 111

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animator_text_view)

        tv_animator.setNum(num)

        btn_animator.setOnClickListener {
            tv_animator.setNum(num + 5)
        }

        initView()
    }

    @SuppressLint("NewApi")
    private fun initView(){

        tv_2.typeface = Typeface.defaultFromStyle(Typeface.BOLD)

        tv_3.paint.isFakeBoldText = true

        tv_4.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
        tv_4.paint.isFakeBoldText = true


        val bounds = Rect()
        btn_get.setOnClickListener {
            val content = tv_7.text
            val len_1 = content.length
            val len_2 = MyStringUtil.length(content.toString())
            val width = tv_7.width - tv_7.paddingLeft - tv_7.paddingRight
            val textSize = tv_7.textSize
            val len = len_2 * textSize
            val wd = getTextWidth(this,content.toString(),textSize)
            tv_7.text = "$content |len_1=$len_1 ,len_2=$len_2 ,width=${dip2px(this,width.toFloat())} ,len=$len" +
                    " ,textSize=$textSize ,wd=$wd"
        }


    }

    fun getTextWidth(Context: Context, text: String, textSize: Float): Float {
        val paint = TextPaint()
        val scaledDensity = resources.displayMetrics.scaledDensity
        paint.textSize = scaledDensity * textSize
        return paint.measureText(text)
    }

    fun dip2px(context: Context?, dipValue: Float): Int {
        if (context == null) {
            return 0
        } else {
            val scale = context.resources.displayMetrics.scaledDensity
            return (dipValue * scale).toInt()
        }

    }
}
