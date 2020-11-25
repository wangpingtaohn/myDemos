package com.wpt.mydemos.viewpager

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.wpt.mydemos.R
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import com.wpt.mydemos.widget.BaseActivity
import kotlinx.android.synthetic.main.activity_video.*


/**
 *    author : wpt
 *    date   : 2019-10-10 15:12
 *    desc   :
 */
class VideoActivity : BaseActivity() {


    private var orientationUtils: OrientationUtils? = null
    
    private val TAG = "VideoActivity"

    private var isPlay = false

    private var isPause = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        initView()
    }



    private fun initView() {
        btn_set.setOnClickListener {
        }

        initVideo()


    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        Log.d(TAG, "onConfigurationChanged_newConfig=" + newConfig.orientation)
        super.onConfigurationChanged(newConfig)

        detail_player.onConfigurationChanged(this, newConfig, orientationUtils, true, true)

        val ivCover = ImageView(this)
        ivCover.setImageResource(R.drawable.ic_gh_foward)

    }



    private fun initVideo() {
        Log.d(TAG, "initVideo=")
        //外部辅助的旋转，帮助全屏
        orientationUtils = OrientationUtils(this, detail_player)
        //初始化不打开外部的旋转
        orientationUtils!!.isEnable = false

        val realUrl = "http://1400163667.vod2.myqcloud.com/d1c8688cvodtranscq1400163667/919ff6b05285890806449061880/v.f30.mp4"

        val gsyVideoOption = GSYVideoOptionBuilder()
        gsyVideoOption
            .setIsTouchWiget(true)
            .setRotateViewAuto(false)
            .setLockLand(true)
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
                }
            }.build(detail_player)

        detail_player.getFullscreenButton().setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                //直接横屏
                orientationUtils!!.resolveByClick()

                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                detail_player.startWindowFullscreen(applicationContext, true, true)
            }
        })

    }

    override fun onPause() {
        detail_player.onVideoPause()
        super.onPause()
        isPause = true

    }

    override fun onResume() {
        detail_player.onVideoResume(false)
        super.onResume()
        isPause = false
    }


    override fun onDestroy() {
        super.onDestroy()
        if (isPlay) {
            detail_player?.release()
        }
        if (orientationUtils != null)
            orientationUtils!!.releaseListener()
    }

}