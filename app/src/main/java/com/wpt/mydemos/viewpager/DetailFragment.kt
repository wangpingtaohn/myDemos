package com.wpt.mydemos.viewpager

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.google.android.exoplayer2.SeekParameters
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack
import com.shuyu.gsyvideoplayer.utils.Debuger
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer
import com.wpt.mydemos.R

import java.util.HashMap

import kotlinx.android.synthetic.main.activity_detail_player.*
import tv.danmaku.ijk.media.exo2.Exo2PlayerManager


class DetailFragment(private var myPos: Int) : BaseLazyFragment() {


    //推荐使用StandardGSYVideoPlayer，功能一致
    //CustomGSYVideoPlayer部分功能处于试验阶段


    private var isPlay: Boolean = false
    private var isPause: Boolean = false

    var orientationUtils: OrientationUtils? = null

    private val curPlay: GSYVideoPlayer?
        get() = if (detail_player?.fullWindowPlayer != null) {
            detail_player!!.fullWindowPlayer
        } else detail_player


    private//String url = "android.resource://" + getPackageName() + "/" + R.raw.test;
    //注意，用ijk模式播放raw视频，这个必须打开
    ///exo 播放 raw
    //String url = "rawresource://" + getPackageName() + "/" + R.raw.test;
    ///exo raw 支持
    //String url =  RawResourceDataSource.buildRawResourceUri(R.raw.test).toString();
    //断网自动重新链接，url前接上ijkhttphook:
    //String url = "ijkhttphook:https://res.exexm.com/cw_145225549855002";
    //String url = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
    //String url = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
    //String url = "http://7xjmzj.com1.z0.glb.clouddn.com/20171026175005_JObCxCE2.mp4";
    //String url = "http://hjq-1257036536.cos.ap-shanghai.myqcloud.com/m3u8/m1/out2.m3u8";
    //String url = "http://223.110.243.138/PLTV/2510088/224/3221227177/index.m3u8";
    //String url = "http://qiniu.carmmi.com/image/132451525666042.mp4";
    //String url = "http://ucp.wn.sunmath.cn/file-upload/gYQJHxK9iNQKJeWyS/V80418-103803.mp4?rc_uid=7sCFCGoaF2iTc9vH9&rc_token=prJK-xGutKmy2LDQO-OZASjob0o1u_s3e5SgMHmgjtn";
    //String url = "http://7xse1z.com1.z0.glb.clouddn.com/1491813192";
    //String url = "file://"+ Environment.getExternalStorageDirectory().getPath() + "Download/132451525666042.mp4";
    //String url =  "http://ipsimg-huabei2.speiyou.cn/010/video/other/20180427/40288b156241ec6301624243bdf7021e/40288b156290270d0162a3e7eb2e0726/1524814477/movie.mp4";
    //String url =  "http://ipsimg-huabei2.speiyou.cn/010/video/other/20180424/40288b156290270d0162a3db8cdd033e/40288b156290270d0162a3e8207f074f/e787a64c-f2d0-48fe-896d-246af05f111a.mp4";
    //String url =  "http://video.7k.cn/app_video/20171202/6c8cf3ea/v.m3u8.mp4";
    //String url =  "http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_4x3/bipbop_4x3_variant.m3u8";
    //String url = "rtmp://ctc-zhenjiang04.rt1.gensee.com/5324e855b28b453db7b0ec226598b76c_171391_0_8801038305_1591077225_205d01b8/video";
    //String url = "http://video1.dgtle.com/backend%2F2020%2F3%2F0%2F%E6%88%91%E6%B2%A1%E6%9C%89%E7%BB%99%E4%B8%80%E5%8A%A08Pro%E5%81%9A%E8%AF%84%E6%B5%8B_%E5%8D%B4%E5%B8%A6%E7%9D%80%E5%AE%83%E6%BC%82%E6%B5%81.mp4_1080.mp4";
    //String url = "http://yongtaizx.xyz/20191230/t2Axgh3k/index.m3u8";
    //String url = "http://123.56.109.212:8035/users/bfe52074fba74247853caa764b522731/films/orig/aa4c3451-0468-452a-a189-bd064a1963e5-鹿鼎记下.mp4";
    //String url = "http://pointshow.oss-cn-hangzhou.aliyuncs.com/transcode/ORIGINAL/Mnbc61586842828593.mp4";
    //ssl error
    //String url =  "https://file.shftz.cn:8443/filesystem/download/10/2019/3/26/ce2c7c66-e9eb-42be-adf6-f9008385ea8c.mov/play";
    //String url =  "https://us-4.wl-cdn.com/hls/20200225/fde4f8ef394731f38d68fe6d601cfd56/index.m3u8";
    //String url =  "https://cdn61.ytbbs.tv/cn/tv/55550/55550-1/play.m3u8?md5=v4sI4lWlo4XojzeAjgBGaQ&expires=1521204012&token=55550";
    //String url =  "http://1253492636.vod2.myqcloud.com/2e5fc148vodgzp1253492636/d08af82d4564972819086152830/plHZZoSkje0A.mp4";
    //String url = "rtsp://ajj:12345678@218.21.217.122:65523/h264/ch40/sub/av_stream";
    //String url = "rtsp://ajj:ajj12345678@218.21.217.122:65522/h264/ch15/sub/av_stream";//String url =  "rtsp://cloud.easydarwin.org:554/stream0.sdp";
    //String url = "http://s.swao.cn/o_1c4gm8o1nniu1had13bk1t0l1rq64m.mov";
    //String url = "http://api.ciguang.tv/avideo/play?num=02-041-0491&type=flv&v=1&client=android";
    //String url = "http://video.7k.cn/app_video/20171213/276d8195/v.m3u8.mp4";
    //String url = "http://103.233.191.21/riak/riak-bucket/6469ac502e813a4c1df7c99f364e70c1.mp4";
    //String url = "http://7xjmzj.com1.z0.glb.clouddn.com/20171026175005_JObCxCE2.mp4";
    //String url = "https://media6.smartstudy.com/ae/07/3997/2/dest.m3u8";
    //String url = "http://cdn.tiaobatiaoba.com/Upload/square/2017-11-02/1509585140_1279.mp4";
    //String url = "http://hcjs2ra2rytd8v8np1q.exp.bcevod.com/mda-hegtjx8n5e8jt9zv/mda-hegtjx8n5e8jt9zv.m3u8";
    //String url = "http://7xse1z.com1.z0.glb.clouddn.com/1491813192";
    //String url = "http://ocgk7i2aj.bkt.clouddn.com/17651ac2-693c-47e9-b2d2-b731571bad37";
    //String url = "http://111.198.24.133:83/yyy_login_server/pic/YB059284/97778276040859/1.mp4";
    //String url = "http://vr.tudou.com/v2proxy/v?sid=95001&id=496378919&st=3&pw=";
    //String url = "http://pl-ali.youku.com/playlist/m3u8?type=mp4&ts=1490185963&keyframe=0&vid=XMjYxOTQ1Mzg2MA==&ep=ciadGkiFU8cF4SvajD8bYyuwJiYHXJZ3rHbN%2FrYDAcZuH%2BrC6DPcqJ21TPs%3D&sid=04901859548541247bba8&token=0524&ctype=12&ev=1&oip=976319194";
    //String url = "http://hls.ciguang.tv/hdtv/video.m3u8";
    //String url = "https://res.exexm.com/cw_145225549855002";
    //String url = "http://storage.gzstv.net/uploads/media/huangmeiyan/jr05-09.mp4";//mepg
    //String url = "https://zh-files.oss-cn-qingdao.aliyuncs.com/20170808223928mJ1P3n57.mp4";//90度
    val url: String
        get() {
            GSYVideoManager.instance().enableRawPlay(activity!!.applicationContext)
//            return "https://pointshow.oss-cn-hangzhou.aliyuncs.com/McTk51586843620689.mp4"
            return "http://pl-ali.youku.com/playlist/m3u8?vid=XMzg1OTUxMTY5Mg%3D%3D&type=mp4hdv3&ups_client_netip=b7949c2e&utid=vswvGPsIY2ACAXuBAUAsHAnw&ccode=0501&psid=bb2b2e6fd49b88d90bda7eb16672179b47d30&duration=2714&expire=18000&drm_type=1&drm_device=10&dyt=0&btf=&rid=200000003DE71435770A728E61E2F915F32B794A02000000&ups_ts=1604902614&onOff=0&encr=0&ups_key=edf1c9dd436bde0e810942c400972501"
        }

    override fun setLayoutId(): Int {
        return R.layout.activity_detail_player
    }


    /*override fun initView() {
        Log.d("===wpt===", "initView")
        initVideo()
    }*/

    override fun initData() {
        Log.d("===wpt===", "initData_myPos=$myPos")
        initVideo()
    }

    /*@Override
    protected void initView() {
        Log.d("===wpt===","initView");
        initVideo();
    }*/

    fun onPageSelected(pos: Int){
        if (pos == myPos && orientationUtils != null){
            val auto = (myPos % 2 == 1)
            orientationUtils!!.isEnable = auto
        }
    }

    private fun initVideo() {
        Log.d("===wpt===", "initVideo")
        val url = url
        //增加封面
        val imageView = ImageView(activity)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.setImageResource(R.mipmap.xxx1)

        //detail_player.setThumbImageView(imageView);

        resolveNormalVideoUI()

        //外部辅助的旋转，帮助全屏
        if (orientationUtils == null){
            if ((activity as DetailPlayer2).orientationUtils == null){
                orientationUtils = OrientationUtils(activity, detail_player)
                (activity as DetailPlayer2).orientationUtils = orientationUtils

            } else {
                orientationUtils = (activity as DetailPlayer2).orientationUtils
            }
        }
        //初始化不打开外部的旋转
        orientationUtils!!.isEnable = false

        /**仅仅横屏旋转，不变直 */
        //orientationUtils.setOnlyRotateLand(true);

        val header = HashMap<String, String>()
        header["ee"] = "33"
        header["allowCrossProtocolRedirects"] = "true"
        header["User-Agent"] = "GSY"
        val gsyVideoOption = GSYVideoOptionBuilder()
        gsyVideoOption.setThumbImageView(imageView)
            .setIsTouchWiget(true)
            .setRotateViewAuto(false)
            //仅仅横屏旋转，不变直
            //.setOnlyRotateLand(true)
            .setLockLand(true)
            .setAutoFullWithSize(true)
            .setShowFullAnimation(false)
            .setNeedLockFull(true)
            .setUrl(url)
            .setMapHeadData(header)
            .setCacheWithPlay(false)
            .setVideoTitle("测试视频")
            .setVideoAllCallBack(object : GSYSampleCallBack() {
                override fun onPrepared(url: String?, vararg objects: Any) {
                    Debuger.printfError("***** onPrepared **** " + objects[0])
                    Debuger.printfError("***** onPrepared **** " + objects[1])
                    super.onPrepared(url, *objects)
                    //开始播放了才能旋转和全屏
                    val auto = (myPos % 2 == 1)
                    Log.d("===wpt===","myPos=$myPos auto=$auto, orientationUtils=$orientationUtils")
                    orientationUtils!!.isEnable = auto
                    isPlay = true

                    //设置 seek 的临近帧。
                    if (detail_player!!.gsyVideoManager.player is Exo2PlayerManager) {
                        (detail_player!!.gsyVideoManager.player as Exo2PlayerManager).setSeekParameter(
                            SeekParameters.NEXT_SYNC
                        )
                        Debuger.printfError("***** setSeekParameter **** ")
                    }
                }

                override fun onEnterFullscreen(url: String?, vararg objects: Any) {
                    super.onEnterFullscreen(url, *objects)
                    Debuger.printfError("***** onEnterFullscreen **** " + objects[0])//title
                    Debuger.printfError("***** onEnterFullscreen **** " + objects[1])//当前全屏player
                }

                override fun onAutoComplete(url: String?, vararg objects: Any) {
                    super.onAutoComplete(url, *objects)
                }

                override fun onClickStartError(url: String?, vararg objects: Any) {
                    super.onClickStartError(url, *objects)
                }

                override fun onQuitFullscreen(url: String?, vararg objects: Any) {
                    super.onQuitFullscreen(url, *objects)
                    Debuger.printfError("***** onQuitFullscreen **** " + objects[0])//title
                    Debuger.printfError("***** onQuitFullscreen **** " + objects[1])//当前非全屏player
                    if (orientationUtils != null) {
                        orientationUtils!!.backToProtVideo()
                    }
                }
            })
            .setLockClickListener { view, lock ->
                if (orientationUtils != null) {
                    //配合下方的onConfigurationChanged
                    orientationUtils!!.isEnable = !lock
                }
            }
            .setGSYVideoProgressListener { progress, secProgress, currentPosition, duration ->
                Debuger.printfLog(
                    " progress $progress secProgress $secProgress currentPosition $currentPosition duration $duration"
                )
            }
            .build(detail_player)

        detail_player!!.fullscreenButton.setOnClickListener {
            //直接横屏
            orientationUtils!!.resolveByClick()

            //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
            detail_player!!.startWindowFullscreen(activity, true, true)
        }

        open_btn!!.setOnClickListener {
            //                fileSearch();
            orientationUtils!!.isEnable = !orientationUtils!!.isEnable
        }
        detail_player!!.startPlayLogic()
    }


    override fun onPause() {
        if (curPlay != null) {
            curPlay!!.onVideoPause()
        }
        super.onPause()
        isPause = true
    }

    override fun onResume() {
        if (curPlay != null) {
            curPlay!!.onVideoResume(false)
        }
        super.onResume()
        isPause = false
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isPlay) {
            if (curPlay != null) {
                curPlay!!.release()
            }
        }
        //GSYPreViewManager.instance().releaseMediaPlayer();
        if (orientationUtils != null)
            orientationUtils!!.releaseListener()
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        //如果旋转了就全屏
        if (isPlay && !isPause && myPos == ((activity as DetailPlayer2).curPos)) {
            detail_player!!.onConfigurationChanged(activity, newConfig, orientationUtils, true, true)
        }
    }


    private fun resolveNormalVideoUI() {
        //增加title
        detail_player!!.titleTextView.visibility = View.GONE
        detail_player!!.backButton.visibility = View.GONE
    }


    private fun getPathForSearch(uri: Uri) {
        val selectionArgs =
            arrayOf(DocumentsContract.getDocumentId(uri).split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1])
        val cursor = activity!!.contentResolver.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Images.Media._ID + "=?",
            selectionArgs, null
        )
        if (null != cursor) {
            if (cursor.moveToFirst()) {
                val index = cursor.getColumnIndex(MediaStore.Video.Media.DATA)
                if (index > -1) {
                    detail_player!!.setUp(uri.toString(), false, "File")
                    detail_player!!.startPlayLogic()
                }
            }
            cursor.close()
        }
    }

    protected fun fileSearch() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "video/*"
        startActivityForResult(intent, READ_REQUEST_CODE)
    }

    companion object {

        private val READ_REQUEST_CODE = 42
    }
}
