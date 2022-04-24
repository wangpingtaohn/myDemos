package com.zhongke.videoplayer.ui.view;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zhongke.videoplayer.R;

import xyz.doikki.videoplayer.controller.ControlWrapper;
import xyz.doikki.videoplayer.controller.IControlComponent;
import xyz.doikki.videoplayer.player.VideoView;
import xyz.doikki.videoplayer.util.PlayerUtils;

/**
 * 播放器顶部标题栏
 */
public class ZKCircleTitleView extends FrameLayout implements IControlComponent {

    private ControlWrapper mControlWrapper;
    private LinearLayout mTitleContainer;
    public ImageView ivBack;

    public ZKCircleTitleView(@NonNull Context context) {
        super(context);
    }

    public ZKCircleTitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ZKCircleTitleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        setVisibility(GONE);
        LayoutInflater.from(getContext()).inflate(R.layout.dkplayer_layout_circle_title_view, this, true);
        mTitleContainer = findViewById(R.id.title_container);
        ivBack = findViewById(R.id.iv_title_back);
        ivBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = PlayerUtils.scanForActivity(getContext());
                if (activity != null && mControlWrapper.isFullScreen()) {
                    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    mControlWrapper.stopFullScreen();
                }
            }
        });
    }

    @Override
    public void attach(@NonNull ControlWrapper controlWrapper) {
        mControlWrapper = controlWrapper;
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void onVisibilityChanged(boolean isVisible, Animation anim) {

        //只在全屏时才有效
        if (isLandScape()) {
            if (isVisible) {
                if (getVisibility() == GONE) {
                    setVisibility(VISIBLE);
                    if (anim != null) {
                        startAnimation(anim);
                    }
                }
            } else {
                if (getVisibility() == VISIBLE) {
                    setVisibility(GONE);
                    if (anim != null) {
                        startAnimation(anim);
                    }
                }
            }
        }
    }

    @Override
    public void onPlayStateChanged(int playState) {
        switch (playState) {
            case VideoView.STATE_IDLE:
            case VideoView.STATE_START_ABORT:
            case VideoView.STATE_PREPARING:
            case VideoView.STATE_PREPARED:
            case VideoView.STATE_ERROR:
            case VideoView.STATE_PLAYBACK_COMPLETED:
                setVisibility(GONE);
                break;
        }
    }

    @Override
    public void onPlayerStateChanged(int playerState) {
        if (playerState == VideoView.PLAYER_NORMAL) {
            setVisibility(GONE);
        } else if (playerState == VideoView.PLAYER_FULL_SCREEN) {
            //setVisibility(VISIBLE);
        }
        Activity activity = PlayerUtils.scanForActivity(getContext());
        if (activity != null && mControlWrapper.hasCutout()) {
            int orientation = activity.getRequestedOrientation();
            int cutoutHeight = mControlWrapper.getCutoutHeight();
            if (orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                mTitleContainer.setPadding(0, cutoutHeight, 0, 0);
            } else if (orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                mTitleContainer.setPadding(cutoutHeight, 0, 0, 0);
            } else if (orientation == ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE) {
                mTitleContainer.setPadding(0, 0, cutoutHeight, 0);
            }
        }
    }

    @Override
    public void setProgress(int duration, int position) {

    }

    @Override
    public void onLockStateChanged(boolean isLocked) {
        if (isLocked) {
            setVisibility(GONE);
        } else {
            setVisibility(VISIBLE);
        }
    }

    /**
     * 判断是否是横屏播放
     *
     * @return true
     */
    public boolean isLandScape() {
        Activity activity = PlayerUtils.scanForActivity(getContext());
        if (activity != null) {
            int o = activity.getRequestedOrientation();
            if (o == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                return true;
            }
        }
        return false;
    }
}
