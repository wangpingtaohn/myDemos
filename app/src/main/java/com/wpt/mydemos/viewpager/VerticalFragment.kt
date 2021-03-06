package com.wpt.mydemos.viewpager

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.text.Editable
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.item_vertical_viewpager_frg_1.*
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import kotlinx.android.synthetic.main.item_vertical_viewpager.*


/**
 *    author : wpt
 *    date   : 2019-10-10 15:12
 *    desc   :
 */
class VerticalFragment(private val num: Int) : Fragment() {


    private val TAG = "VerticalFragment1"

    private var mRootView: View? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach=$num")
//        ImmersionBar.with(this).init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRootView =  inflater.inflate(R.layout.item_vertical_viewpager, null)
        return mRootView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragments = ArrayList<Fragment>()
        fragments.add(VerticalFragment1(0))
        fragments.add(VerticalFragment2(0))
        val adapter = ViewPager2Adapter(activity!!.supportFragmentManager,fragments)
        vp2_frg.adapter = adapter
        vp2_frg.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }
}