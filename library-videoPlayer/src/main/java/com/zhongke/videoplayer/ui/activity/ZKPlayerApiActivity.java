package com.zhongke.videoplayer.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.zhongke.videoplayer.R;
import com.zhongke.videoplayer.ui.fragment.list.RecyclerViewPortraitActivity;

/**
 * @author: user
 * @date: 2021/9/14
 * @description
 */
public class ZKPlayerApiActivity extends Activity implements View.OnClickListener {

    private static final String LIVE_URL = "http://220.161.87.62:8800/hls/0/index.m3u8";
    private static String VOD_HOR_COVER = "https://gzajwlb-1252035736.file.myqcloud.com/awb/pro/client/video/20210714/a4f25e2c7cb0bd3673deefaf1bb2629e.jpg";
    private static final String VOD_HOR_URL = "https://gzajwlb-1252035736.file.myqcloud.com/awb/pro/client/video/20210714/a4f25e2c7cb0bd3673deefaf1bb2629e.mp4";

    private static final String VOD_VER_COVER = "https://gzajwlb-1252035736.file.myqcloud.com/awb/pro/client/snsvideo/20210917/4f5b822406aa415081c1654682cc01c6-iosVideoCover.jpeg";
    private static final String VOD_VER_URL = "https://gzajwlb-1252035736.file.myqcloud.com/awb/pro/client/snsvideo/20210917/4f5b822406aa415081c1654682cc01c6-ios.mp4";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);
        findViewById(R.id.btn_vod).setOnClickListener(this);
        findViewById(R.id.btn_live).setOnClickListener(this);
        findViewById(R.id.btn_vertical_video).setOnClickListener(this);
        findViewById(R.id.btn_landscape_video).setOnClickListener(this);
        findViewById(R.id.btn_auto_video).setOnClickListener(this);
        findViewById(R.id.btn_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_vod) {
            ZKPlayerActivity.start(this, VOD_HOR_URL, "点播", false);
        } else if (id == R.id.btn_live) {
            ZKIssueVideoPlayerActivity.start(this, VOD_HOR_URL);
        } else if (id == R.id.btn_vertical_video) {
            ZKVerticalPlayerActivity.start(this, VOD_VER_COVER,VOD_VER_URL,true);
        } else if (id == R.id.btn_landscape_video) {
            ZKVerticalPlayerActivity.start(this, VOD_HOR_COVER, VOD_HOR_URL,false);
        } else if (id == R.id.btn_auto_video) {
            RecyclerViewPortraitActivity.start(this);
        } else if (id == R.id.btn_back) {
            finish();
        }
    }
}
