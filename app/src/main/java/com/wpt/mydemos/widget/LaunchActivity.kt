package com.wpt.mydemos.widget

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wpt.mydemos.MainActivity
import com.wpt.mydemos.R

class LaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        goMain()
    }

    private fun goMain(){
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}