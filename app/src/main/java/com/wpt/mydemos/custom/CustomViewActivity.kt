package com.wpt.mydemos.custom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.activity_custom_view.*

class CustomViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view)


        custom_btn.setOnClickListener {
            custom_tv.text = "fn\nfn\nff\n"
//            custom_tv.addOnLayoutChangeListener(object : View.OnLayoutChangeListener{
//                override fun onLayoutChange(v: View?, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
//                    val lp = custom_btn.layoutParams
//                    lp.height = oldBottom - bottom
//                    custom_btn.layoutParams = lp
//                    custom_tv.removeOnLayoutChangeListener(this)
//                }
//            })
        }
    }
}
