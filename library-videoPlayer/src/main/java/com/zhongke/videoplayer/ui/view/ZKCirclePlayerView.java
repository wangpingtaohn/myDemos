package com.zhongke.videoplayer.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.zhongke.videoplayer.R;
import com.zhongke.videoplayer.ui.controller.PortraitWhenFullScreenController;
import com.zhongke.videoplayer.ui.player.VideoView;
import com.zhongke.videoplayer.ui.utils.DkDpUtil;
import com.zhongke.videoplayer.ui.utils.Utils;
import com.zhongke.videoplayer.ui.utils.cache.ProxyVideoCacheManager;
import xyz.doikki.videocontroller.component.GestureView;


/**
 * 封装播放器横竖屏的view
 */
public class ZKCirclePlayerView extends FrameLayout {

    public FrameLayout mPlayerContainer;
    public VideoView mVideoView;
    public TextView mTvTime,mTvPlayCount;
    private FrameLayout.LayoutParams layoutParams;
    public ZKPrepareView mZKPrepareView;//视频封面
    private PortraitWhenFullScreenController mController;

    public ZKCirclePlayerView(@NonNull Context context) {
        super(context);
    }

    public ZKCirclePlayerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ZKCirclePlayerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        LayoutInflater.from(getContext()).inflate(R.layout.dkplayer_circler_view, this, true);
        mPlayerContainer = findViewById(R.id.card_view);
        mTvTime = findViewById(R.id.tv_play_time);//倒计时
        mTvPlayCount = findViewById(R.id.tv_play_count);//倒计时
        mZKPrepareView = findViewById(R.id.prepare_view);//视频封面
    }

    /**
     * 创建播放器的组件
     */
    public void initVideoView(String url) {
        //使用ExoPlayer解码
        mVideoView=new VideoView(getContext());
        //添加组件
        addDefaultControlComponent();
        //边播边存
        String proxyUrl = ProxyVideoCacheManager.getProxy(getContext()).getProxyUrl(url);
        //设置播放url
        mVideoView.setUrl(proxyUrl);
        //循环播放
        mVideoView.setLooping(true);
        //居中裁剪模式
        mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_CENTER_CROP);
        //倒计时的回调
        mController.vodControlView.setVideoTimeCountDownListener(new ZKCircleVodControlView.OnVideoTimeCountDownListener() {
            @Override
            public void onTimeCountDown(String time) {
                mTvTime.setVisibility(VISIBLE);
                mTvTime.setText(time);
            }
        });

        //点击播放
        mZKPrepareView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //把列表中预置的PrepareView添加到控制器中，注意isDissociate此处只能为true, 请点进去看isDissociate的解释
                mController.addControlComponent(mZKPrepareView, true);//设置封面
                Utils.removeViewFormParent(mVideoView);
                mPlayerContainer.addView(mVideoView, 0);
                mVideoView.start();
            }
        });
        mController.addControlComponent(mZKPrepareView, true);//设置封面
        Utils.removeViewFormParent(mVideoView);
        mPlayerContainer.addView(mVideoView, 0);
//        mVideoView.start();
    }


    /**
     * 添加组件
     */
    public void addDefaultControlComponent() {
        mController = new PortraitWhenFullScreenController(getContext());
        mController.addControlComponent(new GestureView(getContext()));//手势点击页面
        mController.addControlComponent(new ZKErrorView(getContext())); //设置封面
      //  ZKCircleTitleView zkCircleTitleView = new ZKCircleTitleView(getContext());//标题
       // mController.addControlComponent(zkCircleTitleView);
        mVideoView.setVideoController(mController);
        mVideoView.setOnStateChangeListener(mOnStateChangeListener);
    }


    /**
     * 设置封面
     * @param cover
     */
    public void setCover(String cover){
        Log.d("setCover==","setCover=="+cover);
        Glide.with(this).load(cover).placeholder(R.drawable.ic_vector_default_load_icon).error(R.drawable.ic_vector_default_load_icon).into(mZKPrepareView.mThumb);
    }

    /**
     * 设置横竖屏视频显示
     */
    public void setVideoHeight (boolean isVertical){
        if (isVertical) { //宽度大于高度
            layoutParams = new FrameLayout.LayoutParams(DkDpUtil.dp2px(getContext(),231), DkDpUtil.dp2px(getContext(),308));
        } else {
            layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DkDpUtil.dp2px(getContext(),184));
        }
        mPlayerContainer.setLayoutParams(layoutParams);
    }

    private VideoView.OnStateChangeListener mOnStateChangeListener = new VideoView.SimpleOnStateChangeListener() {
        @Override
        public void onPlayerStateChanged(int playerState) {
            switch (playerState) {
                case VideoView.PLAYER_NORMAL://小屏
                    mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_CENTER_CROP);
                    mVideoView.setMute(true);
                    break;
                case VideoView.PLAYER_FULL_SCREEN://全屏
                    mVideoView.setMute(false);
                    break;
            }
        }

        @Override
        public void onPlayStateChanged(int playState) {
            switch (playState) {
                case VideoView.STATE_IDLE:
                    break;
                case VideoView.STATE_PREPARING:
                    break;
                case VideoView.STATE_PREPARED:
                    break;
                case VideoView.STATE_PLAYING:
                    break;
                case VideoView.STATE_PAUSED:
                    break;
                case VideoView.STATE_BUFFERING:
                    break;
                case VideoView.STATE_BUFFERED:
                    break;
                case VideoView.STATE_PLAYBACK_COMPLETED:
                    break;
                case VideoView.STATE_ERROR:
                    break;
            }
        }
    };
}
