package com.wpt.mydemos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_flexbox.*

class FlexboxActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flexbox)

        initView()
    }

    private fun initView(){
        var count = 0
        while (count < 20){
            val view = LayoutInflater.from(this).inflate(R.layout.text_flexbox,null,false)
//            val lp = flexbox.layoutParams
//            lp.width = ViewGroup.LayoutParams.MATCH_PARENT
//            lp.height = 60
//            view.layoutParams = lp
            val textview = view.findViewById<TextView>(R.id.tv_tag)
            textview.text = "个性标签" + count*2
            flexbox.addView(view)
            count++

        }
    }
}
