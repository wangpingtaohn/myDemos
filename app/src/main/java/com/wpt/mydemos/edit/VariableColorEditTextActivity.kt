package com.wpt.mydemos.edit

import android.os.Bundle
import com.wpt.mydemos.R
import com.wpt.mydemos.widget.BaseActivity
import kotlinx.android.synthetic.main.activity_variable.*

class VariableColorEditTextActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_variable)

        initView()
    }


    private fun initView(){
        tv_red.setOnClickListener {
            et_content.setTextColor(resources.getColor(R.color.red))
        }
        tv_blue.setOnClickListener {
            et_content.setTextColor(resources.getColor(R.color.blue))
        }
        tv_green.setOnClickListener {
            et_content.setTextColor(resources.getColor(R.color.green))
        }

    }
}
