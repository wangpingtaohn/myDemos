package com.wpt.mydemos

import android.annotation.TargetApi
import android.app.DialogFragment
import android.app.Fragment
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wpt.mydemos.keyboard.Keyboard2Activity
import com.wpt.mydemos.keyboard.KeyboardActivity
import com.wpt.mydemos.kotlins.KotlinFunActivity
import com.wpt.mydemos.map.MapJavaACtivity
import com.wpt.mydemos.recycler.RecyclerViewActivity
import com.wpt.mydemos.statusbar.StatusBarActivity
import kotlinx.android.synthetic.main.fragment_main.*

/**
 *    author : wpt
 *    date   : 2019-10-10 15:12
 *    desc   :
 */
class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, null)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        dialog_fragment.setOnClickListener {
            showDialog()
        }
        kotlin_fun_demo.setOnClickListener {
            startActivity(Intent(activity, KotlinFunActivity::class.java))
        }
        main_recyclerview.setOnClickListener {
            startActivity(Intent(activity, RecyclerViewActivity::class.java))
        }
        keyboard.setOnClickListener {
            startActivity(Intent(activity, KeyboardActivity::class.java))
        }
        keyboard2.setOnClickListener {
            startActivity(Intent(activity, Keyboard2Activity::class.java))
        }
        hashmap.setOnClickListener {
            startActivity(Intent(activity, MapJavaACtivity::class.java))
        }
        statusBar.setOnClickListener {
            startActivity(Intent(activity, StatusBarActivity::class.java))
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun showDialog(){
        startActivity(Intent(activity, StatusBarActivity::class.java))
        var dialog = DialogFragment()
        var h = Handler()
        h.postDelayed({
            if (!fragmentManager!!.isStateSaved){
                dialog.show(fragmentManager,"showdialog")
            }
        },4000)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
    }

}