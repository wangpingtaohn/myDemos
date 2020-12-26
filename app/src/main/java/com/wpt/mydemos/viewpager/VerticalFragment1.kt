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
class VerticalFragment1(private val num: Int) : Fragment() {


    private var orientationUtils: OrientationUtils? = null
    
    private val TAG = "VerticalFragment1"

    private var isPlay = false

    private var isPause = false

    private var mRootView: View? = null

    private var createdView = false

    val ghJson = "{\"address\":\"大同路2号\",\"adname\":\"龙华区\",\"affiliation_mall\":false,\"amIFocus\":true,\"amIManager\":false,\"auslese_id\":\"0\",\"business_area\":\"解放西/泰龙城\",\"business_hours\":\"\",\"category_name\":\"购物中心\",\"cover\":\"http://staticcdntest.fantuan.cn/uimage/b7/c4/91/9b/b7c4919b984e2425f4d30a9a900fb819.jpg?x-oss-process\\u003dimage/resize,m_lfit,h_600,w_600/quality,Q_70/interlace,1/format,jpg\",\"distance\":\"0\",\"evaluate_post_num\":\"0\",\"exist_navigation\":true,\"hasNewMessage\":false,\"id\":\"1\",\"intro\":\"\",\"latitude\":\"20.037484\",\"logo\":\"http://staticcdntest.fantuan.cn/uimage/50/37/ab/b0/5037abb003cba72df92f0eb7e8e4c4e5.jpg?x-oss-process\\u003dimage/resize,m_lfit,h_600,w_600/quality,Q_70/interlace,1/format,jpg\",\"longitude\":\"110.337527\",\"member_num\":\"0\",\"name\":\"友谊商业广场\",\"phone\":\"0898-66225566 0898-63222914\",\"recommend_num\":\"0\",\"score\":\"0.0\",\"shareInfo\":{\"shareContent\":\"购物中心\",\"shareImage\":\"http://staticcdntest.fantuan.cn/uimage/b7/c4/91/9b/b7c4919b984e2425f4d30a9a900fb819.jpg?x-oss-process\\u003dimage/resize,m_lfit,h_600,w_600/quality,Q_70/interlace,1/format,jpg\",\"shareImageToSpider\":\"http://staticcdntest.fantuan.cn/uimage/b7/c4/91/9b/b7c4919b984e2425f4d30a9a900fb819.jpg?x-oss-process\\u003dimage/resize,m_lfit,h_600,w_600/quality,Q_70/interlace,1/format,jpg\",\"shareImageToWechat\":\"http://staticcdntest.fantuan.cn/uimage/b7/c4/91/9b/b7c4919b984e2425f4d30a9a900fb819.jpg?x-oss-process\\u003dimage/resize,m_lfit,h_600,w_600/quality,Q_70/interlace,1/format,jpg\",\"sharePlatformIcon\":\"https://fanttest.fantuan.cn/jv/static/image/fant/default_cover.jpg\",\"sharePlatformName\":\"主页\",\"shareTitle\":\"范团主页-友谊商业广场\",\"shareUrl\":\"https://mtest.fantuan.cn/ghAccount/fC_Gaj4bh\"},\"show_community\":\"1\",\"show_evaluate\":\"1\",\"show_nearby\":\"1\",\"type\":\"1\"}"


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
        mRootView =  inflater.inflate(R.layout.item_vertical_viewpager_frg_1, null)
        return mRootView

    }


    var isExpend = true
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val allContent = num.toString() + "\n" + ghJson
        val subContent = allContent.substring(0,15)
        tv_ver.text = allContent
        tv_ver.text = Editable.Factory.getInstance().newEditable(num.toString() + "\n" + ghJson)
        tv_ver.movementMethod = ScrollingMovementMethod.getInstance()

        tv_ver.setOnClickListener {v ->
            if (isExpend){
                tv_ver.text = subContent
                isExpend = false
                tv_ver.scrollTo(v.left,0)
            } else {
                tv_ver.text = allContent
                isExpend = true
            }
        }

        Log.d(TAG, "onViewCreated_num=$num")

        exit_full.setOnClickListener { _ ->
            run {
                detail_player.onBackFullscreen()
                initVideo()
            }
        }

        btn_jump.setOnClickListener {
            startActivity(Intent(activity, VideoActivity::class.java))
        }

        initVideo()
        createdView = true


        val commentFrg = CommnentFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.add(R.id.fl_frg, commentFrg)
        transaction.commit()
        tv_comment.setOnClickListener {
//            fl_frg.visibility = View.VISIBLE
//            commentFrg.showInput()
            showDialog()
        }

        fl_frg.setOnClickListener {
//            fl_frg.visibility = View.GONE
        }

        pause_btn.setOnClickListener {
            detail_player.onVideoPause()
        }
        stop_btn.setOnClickListener {
            detail_player.onVideoReset()
//            detail_player.release()
        }

    }

    private fun showDialog(){
        val dialog = BottomSheetDialog(activity!!, R.style.Theme_Design_BottomSheetDialog)
        dialog.setContentView(R.layout.fragment_comment)
        dialog.setCanceledOnTouchOutside(true)
        dialog.show()
    }



    override fun onConfigurationChanged(newConfig: Configuration) {
        Log.d(TAG, "onConfigurationChanged_newConfig=" + newConfig.orientation)
        super.onConfigurationChanged(newConfig)

        detail_player.onConfigurationChanged(activity, newConfig, orientationUtils, true, true)

        val ivCover = ImageView(activity)
        ivCover.setImageResource(R.drawable.ic_gh_foward)

    }



    private fun initVideo() {
        Log.d(TAG, "initVideo=")
        //外部辅助的旋转，帮助全屏
        orientationUtils = OrientationUtils(activity, detail_player)

        val realUrl = "http://1400163667.vod2.myqcloud.com/d1c8688cvodtranscq1400163667/919ff6b05285890806449061880/v.f30.mp4"
//        val realUrl = "https://v.youku.com/v_show/id_XMzg1OTUxMTY5Mg==.html?x&sharefrom=android&sharekey=02fc5625dcd14ac1cf9f685207f0200d6"

        val gsyVideoOption = GSYVideoOptionBuilder()
        gsyVideoOption
            .setIsTouchWiget(true)
            .setRotateViewAuto(false)
            .setLockLand(false)
            .setAutoFullWithSize(true)
            .setShowFullAnimation(false)
            .setNeedLockFull(true)
            .setUrl(realUrl)
            .setCacheWithPlay(false)
            .setVideoTitle("测试视频")
            .setVideoAllCallBack(object : GSYSampleCallBack() {
                override fun onPrepared(url: String?, vararg objects: Any) {
                    super.onPrepared(url, *objects)
                    //开始播放了才能旋转和全屏
                    Log.d(TAG, "initVideo_onPrepared")
                    orientationUtils!!.isEnable = true
                    isPlay = true
                }

                override fun onQuitFullscreen(url: String?, vararg objects: Any) {
                    super.onQuitFullscreen(url, *objects)
                    if (orientationUtils != null) {
                        orientationUtils!!.backToProtVideo()
                    }
                }
            }).setLockClickListener { view, lock ->
                if (orientationUtils != null) {
                    //配合下方的onConfigurationChanged
                    orientationUtils!!.isEnable = true
                }
            }.build(detail_player)

        detail_player.getFullscreenButton().setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                //直接横屏
                orientationUtils!!.resolveByClick()

                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                detail_player.startWindowFullscreen(activity, true, true)
            }
        })

    }

    override fun onPause() {
        detail_player.onVideoPause()
        super.onPause()
        isPause = true

        Log.d(TAG, "onPause_num=$num")
    }

    override fun onResume() {
        detail_player.onVideoResume(false)
        super.onResume()
        isPause = false
        Log.d(TAG, "onResume=$num")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView_num=$num")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy_num=$num")
        if (isPlay) {
            detail_player?.release()
        }
        if (orientationUtils != null)
            orientationUtils!!.releaseListener()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        Log.d(TAG, "setUserVisibleHint_num=$num isVisibleToUser=$isVisibleToUser ,activity=$activity ,createdView=$createdView")
        /*if (isVisibleToUser && createdView){
            initVideo()
        }*/
    }

}