package com.wpt.mydemos.viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.wpt.mydemos.MainFragment
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.activity_vertical_view_pager.*



class VerticalViewPagerActivity : AppCompatActivity() {

    var TAG = "VerticalViewPagerActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vertical_view_pager)

//        val adapter = MyViewPager(this)
        val fragments = ArrayList<Fragment>()
        fragments.add(VerticalFragment(0))
        fragments.add(VerticalFragment(1))
        fragments.add(VerticalFragment(2))
        fragments.add(VerticalFragment(3))
        fragments.add(VerticalFragment(4))
        fragments.add(VerticalFragment(5))
        val adapter = MyViewPagerAdapter(supportFragmentManager,fragments)
//        val adapter = ViewPager2Adapter(supportFragmentManager,fragments)
//        vvp.orientation = ViewPager2.ORIENTATION_VERTICAL
        vvp.adapter = adapter


//        vvp.setPageTransformer(false, DefaultTransformer())

        add_btn.setOnClickListener {

        }

        vvp.setOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
                Log.d(TAG,"onPageScrollStateChanged")

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                Log.d(TAG,"onPageScrolled_pos=${position}")
            }

            override fun onPageSelected(position: Int) {
                Log.d(TAG,"pos=${position}")
                if (position == 3){
                    fragments.add(VerticalFragment(6))
                    fragments.add(VerticalFragment(7))
                    fragments.add(VerticalFragment(8))
                    fragments.add(VerticalFragment(9))
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }
}
