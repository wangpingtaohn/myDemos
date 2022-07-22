package com.wpt.mydemos

//import com.wpt.mydemos.flutter.PageRouter
import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.telephony.TelephonyManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.wpt.mydemos.animator.AnimatorActivity
import com.wpt.mydemos.animator.Fragment3DActivity
import com.wpt.mydemos.animator.ScaleAnimationActivity
import com.wpt.mydemos.anr.ANRActivity
import com.wpt.mydemos.arcgis.ArcGisActivity
import com.wpt.mydemos.bezier.BezierActivity
import com.wpt.mydemos.bitmap.JointBitmapActivity
import com.wpt.mydemos.coordinator.Coordinator2Activity
import com.wpt.mydemos.coordinator.CoordinatorActivity
import com.wpt.mydemos.custom.CustomViewActivity
import com.wpt.mydemos.custom.RoundedCornersTransform
import com.wpt.mydemos.drag.DragViewActivity
import com.wpt.mydemos.drag.DrawerActivity
import com.wpt.mydemos.drag.RecyclerViewDragActivity
import com.wpt.mydemos.drag2.Drag2Activity
import com.wpt.mydemos.edit.EditFontStyleActivity
import com.wpt.mydemos.edit.SorfKeyActivity
import com.wpt.mydemos.edit.VariableColorEditTextActivity
import com.wpt.mydemos.elevation.ElevationActivity
import com.wpt.mydemos.emoji.EmojiActivity
import com.wpt.mydemos.flexbox.MyFlexBoxActivity
import com.wpt.mydemos.fling.RightToJumpActivity
import com.wpt.mydemos.flutter.FlutterDemoActivity
import com.wpt.mydemos.keyboard.Keyboard2Activity
import com.wpt.mydemos.keyboard.KeyboardActivity
import com.wpt.mydemos.kotlins.KotlinFunActivity
import com.wpt.mydemos.launchmode.LaunchActivity_1
import com.wpt.mydemos.leecode.LeeCodeActivity
import com.wpt.mydemos.map.MapJavaACtivity
import com.wpt.mydemos.okhttp.OkHttpActivity
import com.wpt.mydemos.oom.OOMActivity
import com.wpt.mydemos.packer.CityPickerActivity
import com.wpt.mydemos.packer.PickerViewActivity
import com.wpt.mydemos.pics.LoadPicActivity
import com.wpt.mydemos.radar.RadarActivity
import com.wpt.mydemos.recycler.SmartRefreshActivity
import com.wpt.mydemos.recycler.link.LinkActivity
import com.wpt.mydemos.recycler.rcv2.RecylerViewActivity2
import com.wpt.mydemos.recycler.xrcv.XRecyclerViewActivity
import com.wpt.mydemos.refresh.SwipeRefreshActivity
import com.wpt.mydemos.star.Tag3DActivity
import com.wpt.mydemos.statusbar.StatusBarActivity
import com.wpt.mydemos.textview.AnimatorTextViewActivity
import com.wpt.mydemos.toast.ToastActivity
import com.wpt.mydemos.top.TopActivity
import com.wpt.mydemos.viewpager.DetailPlayer2
import com.wpt.mydemos.viewpager.VerticalViewPagerActivity
import com.wpt.mydemos.wasabeef.WasabeefActivity
import com.wpt.mydemos.webview.WebActivity
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

        et_input.addTextChangedListener(object : TextWatcher{

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                Log.d("et_input","afterTextChanged_s=$s")
                et_input.removeTextChangedListener(this)
                et_input.text = Editable.Factory.getInstance().newEditable("7")
                et_input.addTextChangedListener(this)
            }
        })

        main_get_phone.setOnClickListener {
            getPhoneNumber()
        }

        main_joint_bitmap.setOnClickListener {
            startActivity(Intent(activity, JointBitmapActivity::class.java))
        }
        main_snackBar.setOnClickListener {
            showSnackBar()
        }

        main_arc_gis.setOnClickListener {
            startActivity(Intent(activity, ArcGisActivity::class.java))
        }

        main_link_rv.setOnClickListener {
            startActivity(Intent(activity, LinkActivity::class.java))
        }

        main_go_city.setOnClickListener {
            startActivity(Intent(activity, CityPickerActivity::class.java))
        }

        main_3d_fragment.setOnClickListener {
            startActivity(Intent(activity, Fragment3DActivity::class.java))
        }

        main_picker.setOnClickListener {
            startActivity(Intent(activity, PickerViewActivity::class.java))
        }

        main_radar.setOnClickListener {
            startActivity(Intent(activity, RadarActivity::class.java))
        }

        main_3d_tags.setOnClickListener {
            startActivity(Intent(activity, Tag3DActivity::class.java))
        }

        main_smartRefresh.setOnClickListener {
            startActivity(Intent(activity, SmartRefreshActivity::class.java))
        }

        main_quick_adapter.setOnClickListener {
            startActivity(Intent(activity, XRecyclerViewActivity::class.java))
        }

        main_bezier.setOnClickListener {
            startActivity(Intent(activity, BezierActivity::class.java))
        }

        main_anr.setOnClickListener {
            startActivity(Intent(activity, ANRActivity::class.java))
        }

        main_myflexbox.setOnClickListener {
            startActivity(Intent(activity, MyFlexBoxActivity::class.java))
        }
        main_coordinator2.setOnClickListener {
            startActivity(Intent(activity, Coordinator2Activity::class.java))
        }

//        val url = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Faliyunzixunbucket.oss-cn-beijing.aliyuncs.com%2Fjpg%2F28ba0eb865f7f09e2c8873e9fb567f53.jpg%3Fx-oss-process%3Dimage%2Fresize%2Cp_100%2Fauto-orient%2C1%2Fquality%2Cq_90%2Fformat%2Cjpg%2Fwatermark%2Cimage_eXVuY2VzaGk%3D%2Ct_100&refer=http%3A%2F%2Faliyunzixunbucket.oss-cn-beijing.aliyuncs.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1613007940&t=2236010a025806f8aa499a36a0bdd7ca"
        val url = "http://staticcdntest.fantuan.cn/uimage/ec/72/a0/11/ec72a011c7e78b38414fe324a8676092.jpg?x-oss-process=image/format,jpg"


//        val transform = RoundedCornersTransform(context, 10f)
//        transform.setNeedCorner(false, false, false, false)

        Glide.with(this).load(R.drawable.test_cover)
//            .apply(RequestOptions.overrideOf(300,300))
            .apply(RequestOptions.bitmapTransform(RoundedCornersTransform(context,20,0)))
            .into(iv_cover)

        Glide.with(this).load(R.drawable.test_cover)
            .into(iv_cover1)

        main_memery.setOnClickListener {
            hh_text.text = "可用内存:" + getMemory(context!!) + "是否内存过低:" + isLowMemory(context!!)
        }

        main_fling.setOnClickListener {
            startActivity(Intent(activity, RightToJumpActivity::class.java))
        }

        main_gsy.setOnClickListener {
            startActivity(Intent(activity, DetailPlayer2::class.java))
        }

        main_vertical_viewpager.setOnClickListener {
            startActivity(Intent(activity, VerticalViewPagerActivity::class.java))
        }

        main_wasabeef.setOnClickListener {
            startActivity(Intent(activity, WasabeefActivity::class.java))
        }
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

        main_go_app.setOnClickListener {
            val scheme = "zkgy://pinhn/splash?/Recruitment/companyLicense"
            val uri = Uri.parse(scheme)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        main_go_market.setOnClickListener {
            try {
            val uri = Uri.parse("market://details?id=${requireContext().packageName}") // id为包名
//                val uri = Uri.parse("market://details?id=com.zkgy.pinhn") // id为包名
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            } catch (e: Exception){
                Log.d("===wpt===","跳转应用市场异常:${e.message}")
            }

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
//            PageRouter.openPageByUrl(activity,"/flutter?testPage",null)
        }
        main_flutter_boost.setOnClickListener {
//            PageRouter.openPageByUrl(activity, PageRouter.FLUTTER_MY_WALLET,null)
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
//            startActivity(Intent(activity, RecyclerViewActivity::class.java))
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

    private fun getPhoneNumber(){
        val tm: TelephonyManager = activity?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager


        val tel: String? = if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_SMS
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_PHONE_NUMBERS
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_PHONE_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            tm.line1Number
        } else {
            "获取不到手机号"
        }
        tel?.let { Snackbar.make(clSb, it, Snackbar.LENGTH_SHORT).show() }
    }

    private fun showSnackBar() {
        val snackBar = Snackbar.make(clSb, "哈哈哈，我是snackBar", Snackbar.LENGTH_SHORT)
        snackBar.setAction("收到", View.OnClickListener {

        })
        val view = snackBar.view
        view.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.red))
        snackBar.show()
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun showDialog(){
        startActivity(Intent(activity, StatusBarActivity::class.java))
        var dialog = DialogFragment()
        var h = Handler()
        h.postDelayed({
            if (!fragmentManager!!.isStateSaved){
                dialog.show(fragmentManager!!,"showdialog")
            }
        },4000)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
    }

    // FIXME: It looks like outInfo.lowMemory does not work well as we expected.
    // after run command: adb shell fillup -p 100, outInfo.lowMemory is still false.
    private fun isLowMemory(context: Context): Boolean {
        val am: ActivityManager =
            context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val outInfo: ActivityManager.MemoryInfo = ActivityManager.MemoryInfo()
        am.getMemoryInfo(outInfo)
        return outInfo.lowMemory
    }

    private fun getMemory(context: Context): Long {
        val am: ActivityManager =
            context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val outInfo: ActivityManager.MemoryInfo = ActivityManager.MemoryInfo()
        am.getMemoryInfo(outInfo)
        return outInfo.availMem
    }


}