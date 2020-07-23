package com.wpt.mydemos.launchmode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.activity_launch_1.*

class LaunchActivity_1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_1)

        tv_launch_1.setOnClickListener {
            val intent = Intent(this,LaunchActivity_2::class.java)
            //singleTask与Intent.FLAG_ACTIVITY_NEW_TASK并不会创建新的task,但是如果manifest.xml里配置了taskAffinity，则会新建task
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        tv_launch_1.text = "页面1\n${this.taskId}"
    }
}
