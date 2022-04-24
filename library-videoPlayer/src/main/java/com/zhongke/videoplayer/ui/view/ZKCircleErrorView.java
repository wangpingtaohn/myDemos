package com.zhongke.videoplayer.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.zhongke.videoplayer.R;
import com.zhongke.videoplayer.ui.player.VideoView;
import xyz.doikki.videoplayer.controller.ControlWrapper;
import xyz.doikki.videoplayer.controller.IControlComponent;

/**
 * 播放出错提示界面
 */
public class ZKCircleErrorView extends LinearLayout implements IControlComponent {

    public ImageView mIvCircleError, mIvCirclePortError;
    private ControlWrapper mControlWrapper;

    public ZKCircleErrorView(Context context) {
        this(context, null);
    }

    public ZKCircleErrorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ZKCircleErrorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        setVisibility(GONE);
        LayoutInflater.from(getContext()).inflate(R.layout.dkplayer_layout_circle_error_view, this, true);
        mIvCircleError = findViewById(R.id.iv_circle_error);
        mIvCirclePortError = findViewById(R.id.iv_circle_port_error);
        setClickable(true);
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

    }

    public void setHorVisible() {
        mIvCircleError.setVisibility(VISIBLE);
        mIvCirclePortError.setVisibility(View.GONE);
    }

    public void setPortVisible() {
        mIvCirclePortError.setVisibility(VISIBLE);
        mIvCircleError.setVisibility(View.GONE);
    }

    @Override
    public void onPlayStateChanged(int playState) {
        if (playState == VideoView.STATE_ERROR) {
            bringToFront();
            setVisibility(VISIBLE);
        } else if (playState == VideoView.STATE_IDLE) {
            setVisibility(GONE);
        }
    }

    @Override
    public void onPlayerStateChanged(int playerState) {

    }

    @Override
    public void setProgress(int duration, int position) {

    }

    @Override
    public void onLockStateChanged(boolean isLock) {

    }

}
