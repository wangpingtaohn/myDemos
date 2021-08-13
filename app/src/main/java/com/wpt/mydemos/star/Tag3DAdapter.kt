package com.wpt.mydemos.star

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.moxun.tagcloudlib.view.TagsAdapter
import com.wpt.mydemos.R

/**
 *    author : wpt
 *    date   : 2021/8/515:27
 *    desc   :
 */
class Tag3DAdapter: TagsAdapter() {
    override fun getCount(): Int {
        return 50;
    }

    override fun getView(p0: Context?, p1: Int, p2: ViewGroup?): View {
        val iv = ImageView(p0)
        iv.setImageResource(R.drawable.item_tags)
        iv.setOnClickListener {
            Toast.makeText(p0,"嘿嘿：$p1",Toast.LENGTH_SHORT).show()
            Log.d("===wpt===","嘿嘿：$p1")
        }
        return iv
    }

    override fun getItem(p0: Int): Any {
        return p0
    }

    override fun getPopularity(p0: Int): Int {
        return p0 % 10
    }

    override fun onThemeColorChanged(p0: View?, p1: Int) {
    }
}