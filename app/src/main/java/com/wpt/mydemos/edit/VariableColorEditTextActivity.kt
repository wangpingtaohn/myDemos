package com.wpt.mydemos.edit

import android.os.Bundle
import android.widget.Toast
import com.wpt.mydemos.R
import com.wpt.mydemos.widget.BaseActivity
import kotlinx.android.synthetic.main.activity_variable.*
import kotlinx.android.synthetic.main.layout_publish_vertical_item.*

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

        vertical_add_pic.setOnClickListener {
        }

        vertical_add_face.setOnClickListener {
        }

        vertical_add_link.setOnClickListener {
        }
        vertical_add_loc.setOnClickListener {
        }

        vertical_add_at.setOnClickListener {
        }

        vertical_add_vote.setOnClickListener {
        }


        et_content2.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus){
                Toast.makeText(this,"我获得焦点了",Toast.LENGTH_SHORT).show()
            }
        }

    }
}
