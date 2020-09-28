package com.wpt.mydemos.drag

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.activity_drawer.*

class DrawerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)

        btn_drawer.setOnClickListener {
            drawer_root.openDrawer(drawer_layout)
        }
    }
}
