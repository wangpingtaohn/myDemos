package com.wpt.mydemos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wpt.mydemos.keyboard.Keyboard2Activity
import com.wpt.mydemos.keyboard.KeyboardActivity
import com.wpt.mydemos.kotlins.KotlinFunActivity
import com.wpt.mydemos.map.MapJavaACtivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

    }

    private fun initView(){
        kotlin_fun_demo.setOnClickListener {
            startActivity(Intent(this,KotlinFunActivity::class.java))
        }
        keyboard.setOnClickListener {
            startActivity(Intent(this,KeyboardActivity::class.java))
        }
        keyboard2.setOnClickListener {
            startActivity(Intent(this, Keyboard2Activity::class.java))
        }
        hashmap.setOnClickListener {
            startActivity(Intent(this,MapJavaACtivity::class.java))
        }
    }
}
