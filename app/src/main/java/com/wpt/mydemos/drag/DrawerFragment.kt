package com.wpt.mydemos.drag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wpt.mydemos.R

/**
 *    author : wpt
 *    date   : 2020-12-28 13:39
 *    desc   :
 */
class DrawerFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_drawer,container,false)
    }
}