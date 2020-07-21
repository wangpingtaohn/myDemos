package com.wpt.mydemos.launchmode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.activity_launch_2.*

class LaunchActivity_2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_2)

        tv_launch_2.setOnClickListener {
            startActivity(Intent(this,LaunchActivity_3::class.java))
        }
    }
}
