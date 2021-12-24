package com.wpt.mydemos.toast

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.wpt.mydemos.R
import com.wpt.mydemos.widget.BaseActivity
import kotlinx.android.synthetic.main.activity_toast.*

class ToastActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toast)

        initView()
    }

    private var toast:Toast? = null
    private fun initView() {
        toast_btn.setOnClickListener {
            if(toast == null){
                toast = Toast.makeText(this@ToastActivity,"hello",Toast.LENGTH_SHORT)
            } else {
                toast!!.setText("hello")
            }
            toast!!.show()
            val toast2 = Toast.makeText(this@ToastActivity,"hello",Toast.LENGTH_SHORT)
            //toast2!!.show()
            Log.d(LOG_TAG, "toast=$toast,toast2=$toast2")
        }
        toast_btn1.setOnClickListener {
            Thread(Runnable {
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(this@ToastActivity,"Thread",Toast.LENGTH_SHORT).show()
                }
            }).start()
        }

        myToastBtn.setOnClickListener { MyToast.getInstance(this).show("哈哈哈",Toast.LENGTH_SHORT) }
        zkToastBtn.setOnClickListener { ZKToastUtils.showShort(this,"哈哈哈ZKToast") }
        zkToastCenterBtn.setOnClickListener { ZKToastUtils.showShortCenter(this,"我是centerToast") }
    }
}
