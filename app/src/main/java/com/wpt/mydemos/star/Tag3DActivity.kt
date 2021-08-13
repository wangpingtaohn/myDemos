package com.wpt.mydemos.star

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.moxun.tagcloudlib.view.TagCloudView
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.activity_tag_activity.*

/**
 * 3D球体
 */
class Tag3DActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tag_activity)
        initView()
    }

    private fun initView(){
        tcv.setAdapter(Tag3DAdapter())

        tcv.setOnTagClickListener { p0, p1, p2 -> {
            Toast.makeText(this,"哈哈哈：${p2}",Toast.LENGTH_SHORT).show()
            Log.d("===wpt===","哈哈哈：$p1")
        } }
    }
}