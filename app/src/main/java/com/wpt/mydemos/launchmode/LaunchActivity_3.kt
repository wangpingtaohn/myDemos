package com.wpt.mydemos.launchmode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.activity_launch_3.*

class LaunchActivity_3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_3)


        tv_launch_3.setOnClickListener {
            startActivity(Intent(this,LaunchActivity_2::class.java))
        }

        tv_launch_3.text = "页面3\n${this.taskId}"
    }
}
