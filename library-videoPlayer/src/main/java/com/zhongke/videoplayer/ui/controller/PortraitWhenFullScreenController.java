package com.zhongke.videoplayer.ui.controller;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.zhongke.videoplayer.R;
import com.zhongke.videoplayer.ui.view.ZKCircleVodControlView;
import com.zhongke.videoplayer.ui.view.ZKErrorView;
import xyz.doikki.videocontroller.component.GestureView;
import xyz.doikki.videoplayer.controller.GestureVideoController;
import xyz.doikki.videoplayer.controller.MediaPlayerControl;
import xyz.doikki.videoplayer.player.VideoView;
import xyz.doikki.videoplayer.player.VideoViewManager;

/**
 * 手动点击全屏控制器
 */
public class PortraitWhenFullScreenController extends GestureVideoController implements View.OnClickListener {

    private View mFullScreen;
    private View mIvBack;
    public ZKCircleVodControlView vodControlView;
    protected ProgressBar mLoadingProgress;

    public PortraitWhenFullScreenController(@NonNull Context context) {
        this(context, null);
    }

    public PortraitWhenFullScreenController(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PortraitWhenFullScreenController(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dkplayer_layout_standard_controller;
    }

    @Override
    protected void initView() {
        super.initView();
        mLoadingProgress = findViewById(R.id.loading);
        VideoViewManager.instance().setPlayOnMobileNetwork(true);//设置是否在移动网络下直接播放视频
        addVodControlView();       //进度条显示页面
    }

    /**
     * 进度条显示的View
     */
    private void addVodControlView() {
        vodControlView = new ZKCircleVodControlView(getContext());
        vodControlView.showBottomProgress(false);
        mFullScreen = vodControlView.findViewById(R.id.fullscreen);
        mIvBack = vodControlView.findViewById(R.id.iv_back);
        mFullScreen.setOnClickListener(this);
        mIvBack.setOnClickListener(this);
        addControlComponent(vodControlView);
        setGestureEnabled(true);
    }

    /**
     * 需要添加什么组件
     */
    public void addDefaultControlComponent() {
        ZKErrorView mErrorView = new ZKErrorView(getContext());//错误显示页面
        addControlComponent(mErrorView);
        GestureView gestureView = new GestureView(getContext());//手势点击页面
        addControlComponent(gestureView);
    }


    @Override
    public void setMediaPlayer(MediaPlayerControl mediaPlayer) {
        super.setMediaPlayer(mediaPlayer);
        //不监听设备方向
        mOrientationHelper.setOnOrientationChangeListener(null);
    }

    /**
     * 点击视频进行放大 startFullScreen
     */
    @Override
    public void toggleFullScreen() {
        if (mActivity == null) return;
        int o = mActivity.getRequestedOrientation();
        if (o == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            vodControlView.back.setSelected(false);
            mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            vodControlView.back.setSelected(true);
            mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            if(onFullScreenChangeListener !=null){
                onFullScreenChangeListener.onFullScrollChange();
            }
        }

        mFullScreen.setSelected(o != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    /**
     * 点击视频进行放大 startFullScreen
     */
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        if (!mControlWrapper.isFullScreen()) {
            mControlWrapper.setScreenScaleType(VideoView.SCREEN_SCALE_DEFAULT);//默认放大模式
            mControlWrapper.startFullScreen();
            return true;
        }
        mControlWrapper.toggleShowState();
        return true;
    }

    /**
     * 播放器的全屏和小屏状态监听
     */
    @Override
    protected void onPlayerStateChanged(int playerState) {
        super.onPlayerStateChanged(playerState);
        if (playerState == VideoView.PLAYER_FULL_SCREEN) {
            mFullScreen.setSelected(false);
        } else {
            hide();
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.fullscreen) {//全屏按钮
            toggleFullScreen();
        } else if (i == R.id.iv_play) {//播放按钮
            togglePlay();
        } else if (i == R.id.iv_back) {
            back();
        }
    }

    //播放器返回的逻辑
    public void back() {
        if(isLandScape()){//判断是否是横屏播放
            toggleFullScreen();
        }else {
            mControlWrapper.setScreenScaleType(VideoView.SCREEN_SCALE_CENTER_CROP);//居中裁剪模式
            mControlWrapper.stopFullScreen();
        }
    }

    /**
     * 判断是否是横屏播放
     * @return true
     */
    public boolean isLandScape(){
        int o = mActivity.getRequestedOrientation();
        if (o == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            return true;
        }
        return false;
    }

    @Override
    protected void onPlayStateChanged(int playState) {
        super.onPlayStateChanged(playState);
        switch (playState) {
            //调用release方法会回到此状态
            case VideoView.STATE_IDLE:
            case VideoView.STATE_PLAYING:
            case VideoView.STATE_PAUSED:
            case VideoView.STATE_PREPARED:
            case VideoView.STATE_ERROR:
            case VideoView.STATE_BUFFERED:
            case VideoView.STATE_PLAYBACK_COMPLETED:
                mLoadingProgress.setVisibility(GONE);
                break;
            case VideoView.STATE_PREPARING:
            case VideoView.STATE_BUFFERING:
                mLoadingProgress.setVisibility(VISIBLE);
                break;
        }
    }

    @Override
    public boolean onBackPressed() {
        if (isLocked()) {
            show();
            Toast.makeText(getContext(), xyz.doikki.videocontroller.R.string.dkplayer_lock_tip, Toast.LENGTH_SHORT).show();
            return true;
        }

        if(isLandScape()) {//判断是否是横屏播放
            toggleFullScreen();
            return true;
        }

        if (mControlWrapper.isFullScreen()) {
            return stopFullScreen();
        }
        return super.onBackPressed();
    }

    private OnFullScreenChangeListener onFullScreenChangeListener;
    public interface OnFullScreenChangeListener {
        void onFullScrollChange();
    }
    public void setOnFullScreenChangeListener(OnFullScreenChangeListener l) {
        onFullScreenChangeListener=l;
    }
}