package com.wpt.mydemos.pics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_load_pic.*
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.widget.Toast
import com.wpt.mydemos.R
import java.io.FileInputStream
import java.io.FileNotFoundException



class LoadPicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_pic)

        initView();
    }

    private fun initView() {

//        val path = "external_storage_root/Android/data/com.baidu.searchbox/files/bdshare/1582341618304.png"
//        val path = "/storage/emulated/0/DCIM/Camera/1574913254572.jpg"
        val path = "/storage/emulated/0/Android/data/com.wetime.fanc/files/Pictures/1582360280592.png"
        btn.setOnClickListener {
            /*为什么图片一定要转化为 Bitmap格式的！！ */
            val bitmap = getLocalBitmap(path) //从本地取图片(在cdcard中获取)  //
            iv_load.setImageBitmap(bitmap)
        }

    }


    /**
     * 加载本地图片
     * @param url
     * @return
     */
    private fun getLocalBitmap(url: String): Bitmap? {
        try {
            val fis = FileInputStream(url)
            return BitmapFactory.decodeStream(fis)  ///把流转化为Bitmap图片

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            Toast.makeText(this,e.message,Toast.LENGTH_SHORT).show()
            return null
        }

    }


}
