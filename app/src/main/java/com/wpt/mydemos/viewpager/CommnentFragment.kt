package com.wpt.mydemos.viewpager

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.fragment_comment.*

/**
 *    author : wpt
 *    date   : 2020-11-11 09:44
 *    desc   :
 */
class CommnentFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_comment, null)
    }

    fun showInput(){
        et.isFocusable = true
        et.isFocusableInTouchMode = true
        et.requestFocus()
        val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(et, InputMethodManager.SHOW_FORCED)
    }

}