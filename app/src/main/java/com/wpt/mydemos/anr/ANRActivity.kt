package com.wpt.mydemos.anr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.activity_anr.*

class ANRActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anr)

        anr_btn.setOnClickListener {
            Thread.sleep(100*6000)
            Toast.makeText(this,"哈哈哈",Toast.LENGTH_SHORT).show()
        }
    }
}