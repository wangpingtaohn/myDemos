package com.wpt.mydemos.edit

import android.content.ClipboardManager
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.ViewConfiguration
import android.widget.Toast
import com.wpt.mydemos.R
import com.wpt.mydemos.widget.BaseActivity
import kotlinx.android.synthetic.main.activity_variable.*
import kotlinx.android.synthetic.main.layout_publish_vertical_item.*
import android.content.ClipData











class VariableColorEditTextActivity : BaseActivity() {

    private val MSG_TINT = 1
    private val MSG_TINT_2 = 2
    private val TAP_TIMEOUT = ViewConfiguration.getTapTimeout().toLong()
    private var downX: Float = 0.toFloat()
    private var downY: Float = 0.toFloat()
    private var touchSlop: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_variable)

        initView()

    }

    override fun onResume() {
        super.onResume()
        this.window.decorView.post {
            val manager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val mClipData = ClipData.newPlainText("Label", "哈哈哈哈哈")
            manager.primaryClip = mClipData
            if (manager.hasPrimaryClip() &&
                manager.primaryClip != null && manager.primaryClip!!.itemCount > 0
                && manager.primaryClip!!.getItemAt(0).text != null
            ) {
                val mStr = manager.primaryClip!!.getItemAt(0).text.toString()
                textview.text = mStr
            }
        }
    }


    private fun initView(){



        val handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                Log.d("===wpt===", "handleMessage=" + msg.what)
                when(msg.what){
                    MSG_TINT -> {
                        vertical_add_pic.setBackgroundColor(resources.getColor(R.color.color_DFDFDF))
                    }
                    MSG_TINT_2 -> {
                        vertical_add_pic.setBackgroundColor(resources.getColor(R.color.color_ffffff))
                    }
                }
            }
        }
        tv_red.setOnClickListener {
            et_content.setTextColor(resources.getColor(R.color.red))
        }
        tv_blue.setOnClickListener {
            et_content.setTextColor(resources.getColor(R.color.blue))
        }
        tv_green.setOnClickListener {
            et_content.setTextColor(resources.getColor(R.color.green))
        }

//        vertical_add_pic.setOnTouchListener { v, event ->
//            Log.d("===wpt===", "setOnTouchListener=" + event.action)
//            when(event.action){
//                MotionEvent.ACTION_DOWN -> {
//                    downX = event.getX();
//                    downY = event.getY();
//                    handler.sendEmptyMessageDelayed(MSG_TINT, TAP_TIMEOUT)
//                }
//                MotionEvent.ACTION_MOVE -> {
//                    val dx = event.x - downX
//                    val dy = event.y - downY
//                    if (touchSlop == 0) {
//                        touchSlop = ViewConfiguration.get(this).scaledTouchSlop
//                    }
//                    if (dy > touchSlop) {
//                        handler.sendEmptyMessageDelayed(MSG_TINT_2, TAP_TIMEOUT)
//                        handler.removeMessages(MSG_TINT)
//                    }
//                }
//                MotionEvent.ACTION_UP,MotionEvent.ACTION_CANCEL -> {
//                    handler.sendEmptyMessageDelayed(MSG_TINT_2, TAP_TIMEOUT)
//                }
//            }
//            false
//        }
        vertical_add_pic.setOnClickListener {
            Log.d("===wpt===", "setOnClickListener")
        }

        vertical_add_face.setOnClickListener {
        }
        textview.setOnClickListener {
        }
//
        vertical_add_link.setOnClickListener {
        }
        vertical_add_loc.setOnClickListener {
        }

        vertical_add_at.setOnClickListener {
            Log.d("===wpt===*","@@@@")
        }
        view_empty.setOnClickListener {
            Log.d("===wpt===*","view_empty")
        }

        vertical_add_vote.setOnClickListener {
        }


        et_content2.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus){
                Toast.makeText(this,"我获得焦点了",Toast.LENGTH_SHORT).show()
            }
        }

    }

//    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        return true
//    }
}
