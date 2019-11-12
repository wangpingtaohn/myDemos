package com.wpt.mydemos.keyboard

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.DisplayMetrics
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.activity_keyboard.*

class KeyboardActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keyboard)

        initView()
    }

    private fun initView(){
        edittext.maxLines = 2
        edittext.setHorizontallyScrolling(false)
        btn.setOnClickListener {
            var colorText = "已"
            var text = "软键盘已弹出"
            if (!isSHowKeyboard(baseContext,edittext)){
                colorText = "未"
                text = "软键盘未弹出"
            }
            textview.text = getTextSpan(text,colorText)
        }
    }

    private fun getTextSpan(text:String,colorText:String):SpannableStringBuilder{
        var ssb = SpannableStringBuilder(text)
        var fogeColor = ForegroundColorSpan(Color.parseColor("#ff0000"))
        val start = text.indexOf(colorText);
        val end = start + 1;
        ssb.setSpan(fogeColor,start,end,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return ssb
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

    /**
     * 判断键盘是否显示
     */
    private fun isSoftShowing22(): Boolean {
        //获取当前屏幕内容的高度
        val screenHeight = getWindow().getDecorView().getHeight()
        //获取View可见区域的bottom
        val rect = Rect();
        //DecorView即为activity的顶级view
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect)
        //考虑到虚拟导航栏的情况（虚拟导航栏情况下：screenHeight = rect.bottom + 虚拟导航栏高度）
        //选取screenHeight*2/3进行判断
        return (screenHeight - rect.bottom) > 200
    }

    /**
     * 判断键盘是否显示
     */
    private fun isSoftShowing(): Boolean {
        //获取当前屏幕内容的高度
        val screenHeight = getWindow().getDecorView().getHeight()
        //获取View可见区域的bottom
        val rect = Rect();
        //DecorView即为activity的顶级view
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect)
        //考虑到虚拟导航栏的情况（虚拟导航栏情况下：screenHeight = rect.bottom + 虚拟导航栏高度）
        return screenHeight - rect.bottom - getSoftButtonsBarHeight(this) > 0

        //选取screenHeight*2/3进行判断   -华为mate20没有作用
//        return screenHeight*2/3 > rect.bottom
    }

    /**
     * 底部虚拟按键栏的高度
     * @param mActivity
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun getSoftButtonsBarHeight(mActivity: Activity): Int {
        val metrics = DisplayMetrics()
        //这个方法获取可能不是真实屏幕的高度
        mActivity.windowManager.defaultDisplay.getMetrics(metrics)
        val usableHeight = metrics.heightPixels
        //获取当前屏幕的真实高度
        mActivity.windowManager.defaultDisplay.getRealMetrics(metrics)
        val realHeight = metrics.heightPixels
        return if (realHeight > usableHeight) {
            realHeight - usableHeight
        } else {
            0
        }
    }
}
