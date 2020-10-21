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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vertical_view_pager)

//        val adapter = MyViewPager(this)
        val fragments = ArrayList<Fragment>()
        fragments.add(0,VerticalFragment(0))
        fragments.add(1,VerticalFragment(1))
        fragments.add(2,VerticalFragment(2))
        fragments.add(3,VerticalFragment(3))
//        val adapter = MyViewPagerAdapter(supportFragmentManager,fragments,titles)
        val adapter = ViewPager2Adapter(supportFragmentManager,fragments)
        vvp.orientation = ViewPager2.ORIENTATION_VERTICAL
        vvp.adapter = adapter


//        vvp.setPageTransformer(false, DefaultTransformer())

        add_btn.setOnClickListener {
            fragments.add(4,VerticalFragment(4))
            fragments.add(5,VerticalFragment(5))
            fragments.add(6,VerticalFragment(6))
            fragments.add(7,VerticalFragment(7))
            adapter.notifyDataSetChanged()
        }

        /*vvp.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                Log.d("===wpt===","pos=${position}")
            }
        })*/
    }
}
