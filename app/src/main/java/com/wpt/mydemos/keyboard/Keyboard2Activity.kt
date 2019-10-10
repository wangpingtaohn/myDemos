package com.wpt.mydemos.keyboard

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import com.wpt.mydemos.R
import com.wpt.mydemos.StatusBarUtils
import kotlinx.android.synthetic.main.activity_keyboard2.*

class Keyboard2Activity : AppCompatActivity() {

    var handler = Handler()

    var OnGlobalLayoutListener:MyOnGlobalLayoutListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keyboard2)

        StatusBarUtils.setStatusBarTransparent(this,R.color.background_material_dark)

//        rl_edit.viewTreeObserver.addOnGlobalLayoutListener {
//            if (!isSHowKeyboard(this,et_forward_content)){
//                ll_share_btn.visibility = View.VISIBLE
//            }
//        }
        //OnGlobalLayoutListener = MyOnGlobalLayoutListener()
        //rl_edit.viewTreeObserver.addOnGlobalLayoutListener(OnGlobalLayoutListener)

        et_forward_content.setOnTouchListener { view: View, motionEvent: MotionEvent ->
//            handler.postDelayed(this::hideView,200)
            if (motionEvent.action == MotionEvent.ACTION_UP){
                if (shareLayoutHeight == 0){
                    shareLayoutHeight = ll_share_btn.height
                }
                hideView()
                //rl_edit.viewTreeObserver.addOnGlobalLayoutListener(OnGlobalLayoutListener)
            }
            false
        }
//        et_forward_content.setOnClickListener {
//            if (shareLayoutHeight == 0){
//                shareLayoutHeight = ll_share_btn.height
//            }
//            hideView()
//            rl_edit.viewTreeObserver.addOnGlobalLayoutListener(OnGlobalLayoutListener)
//        }
//        et_forward_content.setOnFocusChangeListener { view, b ->
//            if (shareLayoutHeight == 0){
//                shareLayoutHeight = ll_share_btn.height
//            }
//            hideView()
//            rl_edit.viewTreeObserver.addOnGlobalLayoutListener(OnGlobalLayoutListener)
//        }
        et_forward_content.setLayerType(View.LAYER_TYPE_SOFTWARE,null)
        var listener = ResizelayoutListener()
        resizelayout.setInputSoftListener(listener)
    }

    private fun hideView(){
//        ll_share_btn.visibility = View.GONE
        ll_share_btn.layoutParams.height = 0
    }

    private fun showView(){
//                ll_share_btn.visibility = View.VISIBLE
        var lp = ll_share_btn.layoutParams
        lp.height = shareLayoutHeight
        ll_share_btn.layoutParams = lp
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


    var shareLayoutHeight = 0
    inner class MyOnGlobalLayoutListener : ViewTreeObserver.OnGlobalLayoutListener {

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        override fun onGlobalLayout() {
//            if (shareLayoutHeight == 0){
//                shareLayoutHeight = ll_share_btn.height
//                rl_edit.viewTreeObserver.removeOnGlobalLayoutListener(OnGlobalLayoutListener)
//            }
            if (!isSHowKeyboard(applicationContext,et_forward_content)){
                rl_edit.viewTreeObserver.removeOnGlobalLayoutListener(OnGlobalLayoutListener)
                showView()
            }
        }
    }

    inner class ResizelayoutListener : ResizeLayout.OnInputSoftListener {
        override fun onSoftInputShow() {
            hideView()
        }

        override fun onSoftInputHide() {
            showView()
        }

    }

}
