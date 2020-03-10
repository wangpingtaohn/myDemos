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
import android.widget.Toast
import com.wpt.mydemos.animator.AnimatorActivity
import com.wpt.mydemos.custom.CustomViewActivity
import com.wpt.mydemos.drag.DragViewActivity
import com.wpt.mydemos.drag.RecyclerViewDragActivity
import com.wpt.mydemos.drag2.Drag2Activity
import com.wpt.mydemos.edit.EditFontStyleActivity
import com.wpt.mydemos.edit.SorfKeyActivity
import com.wpt.mydemos.edit.VariableColorEditTextActivity
import com.wpt.mydemos.elevation.ElevationActivity
import com.wpt.mydemos.emoji.EmojiActivity
import com.wpt.mydemos.flutter.FlutterDemoActivity
import com.wpt.mydemos.flutter.PageRouter
import com.wpt.mydemos.keyboard.Keyboard2Activity
import com.wpt.mydemos.keyboard.KeyboardActivity
import com.wpt.mydemos.kotlins.KotlinFunActivity
import com.wpt.mydemos.map.MapJavaACtivity
import com.wpt.mydemos.okhttp.OkHttpActivity
import com.wpt.mydemos.oom.OOMActivity
import com.wpt.mydemos.pics.LoadPicActivity
import com.wpt.mydemos.recycler.rcv1.RecyclerViewActivity
import com.wpt.mydemos.recycler.rcv2.RecylerViewActivity2
import com.wpt.mydemos.statusbar.StatusBarActivity
import com.wpt.mydemos.toast.ToastActivity
import io.flutter.embedding.android.FlutterActivity
import kotlinx.android.synthetic.main.fragment_main.*

/**
 *    author : wpt
 *    date   : 2019-10-10 15:12
 *    desc   :
 */
class MainFragment : Fragment() {


    var mainBean: MainBean? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, null)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {

        main_custom_view.setOnClickListener {
            startActivity(Intent(activity, CustomViewActivity::class.java))
        }
        main_flutter.setOnClickListener {
            startActivity(
                FlutterActivity
                    .withNewEngine()
                    .initialRoute("/flutter?myWalletPage")
                    .build(activity)
            )
        }
        main_flutter_boost.setOnClickListener {
            PageRouter.openPageByUrl(activity, PageRouter.FLUTTER_MY_WALLET,null)
        }

        main_flutter_boost_simple.setOnClickListener {
            startActivity(Intent(activity, FlutterDemoActivity::class.java))
//            startActivity(FlutterActivity.createDefaultIntent(activity))
        }


        main_load_pic.setOnClickListener {
            startActivity(Intent(activity, LoadPicActivity::class.java))
//            startActivity(FlutterActivity.createDefaultIntent(activity))
        }

        main_font.setOnClickListener {
            startActivity(Intent(activity, EditFontStyleActivity::class.java))
        }
        main_oom.setOnClickListener {
            startActivity(Intent(activity, OOMActivity::class.java))
        }
        main_keybaord.setOnClickListener {
            startActivity(Intent(activity, SorfKeyActivity::class.java))
        }
        main_drag_view.setOnClickListener {
            startActivity(Intent(activity, DragViewActivity::class.java))
        }
        main_drag2.setOnClickListener {
            startActivity(Intent(activity, Drag2Activity::class.java))
        }
        main_drag.setOnClickListener {
            startActivity(Intent(activity, RecyclerViewDragActivity::class.java))
        }
        main_okhttp.setOnClickListener {
            startActivity(Intent(activity, OkHttpActivity::class.java))
        }
        main_animator.setOnClickListener {
            startActivity(Intent(activity, AnimatorActivity::class.java))
        }
        main_toast.setOnClickListener {
            startActivity(Intent(activity, ToastActivity::class.java))
        }
        main_variable_edit.setOnClickListener {
            startActivity(Intent(activity, VariableColorEditTextActivity::class.java))
        }
        main_elevation.setOnClickListener {
            startActivity(Intent(activity, ElevationActivity::class.java))
        }

        main_flexbox.setOnClickListener {
            startActivity(Intent(activity, FlexboxActivity::class.java))
        }

        dialog_fragment.setOnClickListener {
            showDialog()
        }
        Toast.makeText(activity, mainBean?.name, Toast.LENGTH_SHORT).show()
        main_object.setOnClickListener {
            if (activity is MainActivity){
                (activity as MainActivity).setMainBean()
            }
            Toast.makeText(activity, mainBean?.name, Toast.LENGTH_SHORT).show()
        }
        main_recyclerview2.setOnClickListener {
            startActivity(Intent(activity, RecylerViewActivity2::class.java))
        }

        main_emoji.setOnClickListener {
            startActivity(Intent(activity, EmojiActivity::class.java))
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