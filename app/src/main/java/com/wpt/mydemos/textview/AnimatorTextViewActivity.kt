package com.wpt.mydemos.textview

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
    }
}
