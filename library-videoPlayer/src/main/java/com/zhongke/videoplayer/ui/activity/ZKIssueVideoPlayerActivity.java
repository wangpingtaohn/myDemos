package com.zhongke.videoplayer.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.Nullable;

import com.zhongke.videoplayer.R;
import com.zhongke.videoplayer.ui.ZKStandardVideoController;
import com.zhongke.videoplayer.ui.player.VideoView;
import com.zhongke.videoplayer.ui.utils.IntentKeys;
import com.zhongke.videoplayer.ui.utils.cache.ProxyVideoCacheManager;
import com.zhongke.videoplayer.ui.view.ZKCircleVodControlView;
import com.zhongke.videoplayer.ui.view.ZKIssueVodControlView;


/**
 * 发布的视频播放器
 */
public class ZKIssueVideoPlayerActivity extends Activity {

    private String url;
    public VideoView mVideoView;

    /**
     * @param url 播放器地址
     */
    public static void start(Context context, String url) {
        Intent intent = new Intent(context, ZKIssueVideoPlayerActivity.class);
        intent.putExtra(IntentKeys.URL, url);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_player);
        initData();
        initVideo();
    }

    public void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra(IntentKeys.URL); //播放视频地址
        }
    }

    private void initVideo() {
        mVideoView = findViewById(R.id.player);
        ZKStandardVideoController controller = new ZKStandardVideoController(this);//添加控制器
        ZKIssueVodControlView vodControlView = new ZKIssueVodControlView(this);//点播控制条
        vodControlView.back.setOnClickListener(v -> {
            if(mVideoView.isFullScreen()){
                vodControlView.toggleFullScreen();
            }else {
                finish();
            }
        });
        vodControlView.isShowBackView(true);
        controller.addControlComponent(vodControlView);
        mVideoView.setLooping(true);
        mVideoView.setUrl(url);
        mVideoView.setVideoController(controller);
        mVideoView.start();
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
