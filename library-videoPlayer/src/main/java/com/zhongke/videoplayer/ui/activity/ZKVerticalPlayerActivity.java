package com.zhongke.videoplayer.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import com.zhongke.videoplayer.R;
import com.zhongke.videoplayer.ui.DKBaseActivity;
import com.zhongke.videoplayer.ui.utils.IntentKeys;
import com.zhongke.videoplayer.ui.view.ZKCirclePlayerView;
import com.zhongke.videoplayer.ui.player.VideoView;

/**
 * 竖屏播放器演示
 */
public class ZKVerticalPlayerActivity extends DKBaseActivity {

    private String url;
    private String cover;
    private boolean isVertical;
    public VideoView mVideoView;

    /**
     * @param cover 封面
     * @param url 播放器地址
     * @param isVertical 横屏还是竖屏视频
     */
    public static void start(Context context, String cover, String url, boolean isVertical) {
        Intent intent = new Intent(context, ZKVerticalPlayerActivity.class);
        intent.putExtra(IntentKeys.URL, url);
        intent.putExtra(IntentKeys.COVER, cover);
        intent.putExtra(IntentKeys.IS_VERTICAL, isVertical);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_vertical_player;
    }

    @Override
    protected void initView() {
        super.initView();
        initData();
        //圈子播放器的初始化
        initVideo();
    }

    public void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            //播放视频地址
            url = intent.getStringExtra(IntentKeys.URL);
            cover = intent.getStringExtra(IntentKeys.COVER);
            //根据是否为横竖屏提供视频大小
            isVertical = intent.getBooleanExtra(IntentKeys.IS_VERTICAL, false);
        }
    }

    private void initVideo() {
        ZKCirclePlayerView zkCirclePlayerView = findViewById(R.id.zk_circle_player_view);
        zkCirclePlayerView.setVideoHeight(isVertical);
        zkCirclePlayerView.initVideoView(url);
        zkCirclePlayerView.setCover(cover);
        mVideoView=zkCirclePlayerView.mVideoView;
    }

    public void onButtonClick(View view) {
        int id = view.getId();
        if (id == R.id.scale_default) {
            mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_DEFAULT);
        } else if (id == R.id.scale_169) {
            mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_16_9);
        } else if (id == R.id.scale_43) {
            mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_4_3);
        } else if (id == R.id.scale_original) {
            mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_ORIGINAL);
        } else if (id == R.id.scale_match_parent) {
            mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_MATCH_PARENT);
        } else if (id == R.id.scale_center_crop) {
            mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_CENTER_CROP);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mVideoView != null) {
            mVideoView.resume();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (mVideoView != null) {
            mVideoView.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideoView != null) {
            mVideoView.release();
        }
    }

    @Override
    public void onBackPressed() {
        if (mVideoView == null || !mVideoView.onBackPressed()) {
            super.onBackPressed();
        }
    }
}
