package com.wpt.mydemos.statusbar

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.RequiresApi
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.activity_status_bar.*
import java.lang.StringBuilder

class StatusBarActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status_bar)

        //window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)//设置绘画模式
        //window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)//清除绘画模式
        //window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)//设置半透明模式
        //window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)//清除半透明模式
        window.statusBarColor = resources.getColor(R.color.colorPrimary)

        init()
    }

    private fun init(){
        setStatusBtn.setOnClickListener {
            setStatusBar()
        }
        setStatusDefatultBtn.setOnClickListener {
            setStatusBarDefafult()
        }
    }


    private fun setStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) run {
            val decorView = window.decorView
            var vis = decorView.systemUiVisibility
            //黑色字体
            vis = vis or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            var text = StringBuilder(vis.toString())
//            //            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vis = vis or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            }
            text.append("--")
                .append(vis.toString())
            status_text.text = text
            decorView.systemUiVisibility = vis
        }
    }

    private fun setStatusBarDefafult(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) run {
            val decorView = window.decorView
            var vis = decorView.systemUiVisibility
            var text = StringBuilder(vis.toString())
            //白色字体
            vis = View.SYSTEM_UI_FLAG_VISIBLE
//            //            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vis = vis or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            }
            text.append("--")
                .append(vis.toString())
            status_text.text = text
            decorView.systemUiVisibility = vis
        }
    }
}
