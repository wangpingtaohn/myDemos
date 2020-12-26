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
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.item_vertical_viewpager_frg_1.*
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import com.shuyu.gsyvideoplayer.utils.OrientationUtils


/**
 *    author : wpt
 *    date   : 2019-10-10 15:12
 *    desc   :
 */
class VerticalFragment2(private val num: Int) : Fragment() {


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
        mRootView =  inflater.inflate(R.layout.item_vertical_viewpager_frg2, null)
        return mRootView

    }


}