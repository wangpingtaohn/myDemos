package com.wpt.mydemos.viewpager

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.wpt.mydemos.R

/**
 *    author : wpt
 *    date   : 2020-10-14 14:21
 *    desc   :
 */
class MyViewPager(private val mContext: Context): PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        Log.d("===wpt===", "position=$position")
        val view  = LayoutInflater.from(mContext).inflate(R.layout.item_vertical_viewpager, container, false)
        container.addView(view)
        return view
    }

    override fun getCount(): Int {
        return 20
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {//及时销毁界面，防止内存溢出
        container.removeView(`object` as View)
    }
}