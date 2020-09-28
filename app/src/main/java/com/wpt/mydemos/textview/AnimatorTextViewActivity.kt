package com.wpt.mydemos.textview

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.activity_animator_text_view.*

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

    private fun initView(){

        tv_2.typeface = Typeface.defaultFromStyle(Typeface.BOLD)

        tv_3.paint.isFakeBoldText = true

        tv_4.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
        tv_4.paint.isFakeBoldText = true


    }
}
