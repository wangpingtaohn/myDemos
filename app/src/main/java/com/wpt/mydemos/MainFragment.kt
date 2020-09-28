package com.wpt.mydemos

import android.annotation.SuppressLint
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
import com.wpt.mydemos.animator.ScaleAnimationActivity
import com.wpt.mydemos.coordinator.CoordinatorActivity
import com.wpt.mydemos.custom.CustomViewActivity
import com.wpt.mydemos.drag.DragViewActivity
import com.wpt.mydemos.drag.DrawerActivity
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
import com.wpt.mydemos.launchmode.LaunchActivity_1
import com.wpt.mydemos.launchmode.LaunchActivity_2
import com.wpt.mydemos.leecode.LeeCodeActivity
import com.wpt.mydemos.map.MapJavaACtivity
import com.wpt.mydemos.okhttp.OkHttpActivity
import com.wpt.mydemos.oom.OOMActivity
import com.wpt.mydemos.pics.LoadPicActivity
import com.wpt.mydemos.recycler.rcv1.RecyclerViewActivity
import com.wpt.mydemos.recycler.rcv2.RecylerViewActivity2
import com.wpt.mydemos.refresh.SwipeRefreshActivity
import com.wpt.mydemos.statusbar.StatusBarActivity
import com.wpt.mydemos.textview.AnimatorTextViewActivity
import com.wpt.mydemos.toast.ToastActivity
import com.wpt.mydemos.top.TopActivity
import com.wpt.mydemos.webview.WebActivity
import io.flutter.embedding.android.FlutterActivity
import kotlinx.android.synthetic.main.fragment_main.*

/**
 *    author : wpt
 *    date   : 2019-10-10 15:12
 *    desc   :
 */
@SuppressLint("NewApi")
class MainFragment : Fragment() {


    var mainBean: MainBean? = null

//    val ghJson = "{\"address\":\"大同路2号\",\"adname\":\"龙华区\",\"affiliation_mall\":false,\"amIFocus\":true,\"amIManager\":false,\"auslese_id\":\"0\",\"business_area\":\"解放西/泰龙城\",\"business_hours\":\"\",\"category_name\":\"购物中心\",\"cover\":\"http://staticcdntest.fantuan.cn/uimage/b7/c4/91/9b/b7c4919b984e2425f4d30a9a900fb819.jpg?x-oss-process\\u003dimage/resize,m_lfit,h_600,w_600/quality,Q_70/interlace,1/format,jpg\",\"distance\":\"0\",\"evaluate_post_num\":\"0\",\"exist_navigation\":true,\"hasNewMessage\":false,\"id\":\"1\",\"intro\":\"\",\"latitude\":\"20.037484\",\"logo\":\"http://staticcdntest.fantuan.cn/uimage/50/37/ab/b0/5037abb003cba72df92f0eb7e8e4c4e5.jpg?x-oss-process\\u003dimage/resize,m_lfit,h_600,w_600/quality,Q_70/interlace,1/format,jpg\",\"longitude\":\"110.337527\",\"member_num\":\"0\",\"name\":\"友谊商业广场\",\"phone\":\"0898-66225566 0898-63222914\",\"recommend_num\":\"0\",\"score\":\"0.0\",\"shareInfo\":{\"shareContent\":\"购物中心\",\"shareImage\":\"http://staticcdntest.fantuan.cn/uimage/b7/c4/91/9b/b7c4919b984e2425f4d30a9a900fb819.jpg?x-oss-process\\u003dimage/resize,m_lfit,h_600,w_600/quality,Q_70/interlace,1/format,jpg\",\"shareImageToSpider\":\"http://staticcdntest.fantuan.cn/uimage/b7/c4/91/9b/b7c4919b984e2425f4d30a9a900fb819.jpg?x-oss-process\\u003dimage/resize,m_lfit,h_600,w_600/quality,Q_70/interlace,1/format,jpg\",\"shareImageToWechat\":\"http://staticcdntest.fantuan.cn/uimage/b7/c4/91/9b/b7c4919b984e2425f4d30a9a900fb819.jpg?x-oss-process\\u003dimage/resize,m_lfit,h_600,w_600/quality,Q_70/interlace,1/format,jpg\",\"sharePlatformIcon\":\"https://fanttest.fantuan.cn/jv/static/image/fant/default_cover.jpg\",\"sharePlatformName\":\"主页\",\"shareTitle\":\"范团主页-友谊商业广场\",\"shareUrl\":\"https://mtest.fantuan.cn/ghAccount/fC_Gaj4bh\"},\"show_community\":\"1\",\"show_evaluate\":\"1\",\"show_nearby\":\"1\",\"type\":\"1\"}"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, null)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {

        main_drawer.setOnClickListener {
            startActivity(Intent(activity, DrawerActivity::class.java))
        }

        main_text_animator.setOnClickListener {
            startActivity(Intent(activity, AnimatorTextViewActivity::class.java))
        }

        main_launch_more.setOnClickListener {
            val intent = Intent(activity, LaunchActivity_1::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION)
            startActivity(intent)
        }

        main_leeCode.setOnClickListener {
            startActivity(Intent(activity, LeeCodeActivity::class.java))
        }

        main_swipe.setOnClickListener {
            startActivity(Intent(activity, SwipeRefreshActivity::class.java))
        }
        main_top_activity.setOnClickListener {
            startActivity(Intent(activity, TopActivity::class.java))
        }

        main_scale.setOnClickListener {
            startActivity(Intent(activity, ScaleAnimationActivity::class.java))
        }

        main_coordinator.setOnClickListener {
            startActivity(Intent(activity, CoordinatorActivity::class.java))
        }
        main_webView.setOnClickListener {
            startActivity(Intent(activity, WebActivity::class.java))
        }

        main_custom_view.setOnClickListener {
            startActivity(Intent(activity, CustomViewActivity::class.java))
        }
        main_flutter.setOnClickListener {
//            startActivity(
//                FlutterActivity
//                    .withNewEngine()
//                    .initialRoute("/flutter?ghSelectShop")
//                    .build(activity)
//            )
            PageRouter.openPageByUrl(activity,"/flutter?testPage",null)
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