package com.wpt.mydemos.keyboard

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.activity_keyboard2.*

class Keyboard2Activity : AppCompatActivity() {

    var handler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keyboard2)

        rl_edit.viewTreeObserver.addOnGlobalLayoutListener {
            if (!isSHowKeyboard(this,et_forward_content)){
                ll_share_btn.visibility = View.VISIBLE
            }
        }

        et_forward_content.setOnTouchListener { view: View, motionEvent: MotionEvent ->
            handler.postDelayed(this::hideView,200)
            false
        }
    }

    private fun hideView(){
        ll_share_btn.visibility = View.GONE
    }


    /**
     * 判断软键盘是否弹出
     */
    private fun isSHowKeyboard(context: Context, v: View):Boolean  {
        var imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm.hideSoftInputFromWindow(v.getWindowToken(), 0)) {
            imm.showSoftInput(v, 0)
            return true
        } else {
            return false
        }
    }


}
