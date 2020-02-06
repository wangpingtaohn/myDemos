package com.wpt.mydemos.edit

import android.os.Bundle
import android.text.Html
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
        val content = "<h1>胜<font color=\"#ff0000\">多负少的发顺丰水电费水电费</font></h1>"
        textview.text = Html.fromHtml(content)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        et_topic_name.setOnTouchListener { v, event ->
            Toast.makeText(this, "####", Toast.LENGTH_SHORT).show()
        false
        }
    }
}
