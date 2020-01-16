package com.wpt.mydemos.edit

import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.wpt.mydemos.R
import com.wpt.mydemos.widget.BaseActivity
import kotlinx.android.synthetic.main.activity_small_group_topic_edit.*

class SorfKeyActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_small_group_topic_edit)

//        et_topic_name.setOnFocusChangeListener { v, hasFocus ->
//            run {
//                Toast.makeText(this, hasFocus.toString(), Toast.LENGTH_SHORT).show()
//            }
//        }
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        et_topic_name.setOnTouchListener { v, event ->
            Toast.makeText(this, "####", Toast.LENGTH_SHORT).show()
        false
        }
    }
}
