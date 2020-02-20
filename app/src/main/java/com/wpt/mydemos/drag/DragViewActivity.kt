package com.wpt.mydemos.drag

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.activity_drag_view.*

class DragViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_view)

        initView()
    }

    private fun initView(){
        btn_hide.setOnClickListener {
            drag_view.hide();
        }
        btn_show.setOnClickListener {
            drag_view.show()
        }

        btn2.setOnClickListener {
            Log.d("===wpt===","btn2")
        }
        view_empty.setOnClickListener {
            Log.d("===wpt===","view_empty")
        }

        drag_view.setLimitBottom(true)
    }
}
