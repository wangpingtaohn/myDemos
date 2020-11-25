package com.wpt.mydemos.viewpager

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager

import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.activity_detail_player2.*


class DetailPlayer2 : AppCompatActivity() {

    var orientationUtils: OrientationUtils? = null

    var curPos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player2)

        /*val newFragment = DetailFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout2, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()*/


        val fragments = ArrayList<Fragment>()
        fragments.add(DetailFragment(0))
        fragments.add(DetailFragment(1))
        fragments.add(DetailFragment(2))
        fragments.add(DetailFragment(3))
        fragments.add(DetailFragment(4))
        fragments.add(DetailFragment(5))
        val adapter = MyViewPagerAdapter(supportFragmentManager,fragments)
//        val adapter = ViewPager2Adapter(supportFragmentManager,fragments)
//        vvp.orientation = ViewPager2.ORIENTATION_VERTICAL
        vvp.adapter = adapter

        vvp.setOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                curPos = position
                (fragments[curPos] as DetailFragment).onPageSelected(position)
            }
        })
    }

}
