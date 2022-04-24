package com.zhongke.videoplayer.ui.fragment.list;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.zhongke.videoplayer.R;
import com.zhongke.videoplayer.ui.DKBaseActivity;
import com.zhongke.videoplayer.ui.ZKStandardVideoController;
import com.zhongke.videoplayer.ui.adapter.VideoRecyclerViewAdapter;
import com.zhongke.videoplayer.ui.adapter.listener.OnItemChildClickListener;
import com.zhongke.videoplayer.ui.bean.VideoBean;
import com.zhongke.videoplayer.ui.controller.PortraitWhenFullScreenController;
import com.zhongke.videoplayer.ui.utils.DataUtil;
import com.zhongke.videoplayer.ui.utils.Tag;
import com.zhongke.videoplayer.ui.utils.Utils;
import com.zhongke.videoplayer.ui.utils.cache.ProxyVideoCacheManager;
import com.zhongke.videoplayer.ui.view.ZKCompleteView;
import com.zhongke.videoplayer.ui.view.ZKErrorView;

import java.util.ArrayList;
import java.util.List;

import xyz.doikki.videocontroller.component.CompleteView;
import xyz.doikki.videocontroller.component.ErrorView;
import xyz.doikki.videocontroller.component.GestureView;
import xyz.doikki.videocontroller.component.TitleView;
import xyz.doikki.videoplayer.player.VideoView;
import xyz.doikki.videoplayer.util.L;

import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE;

/**
 * 全屏后手动横屏，并不完美，仅做参考
 */
public class RecyclerViewPortraitActivity extends DKBaseActivity implements OnItemChildClickListener {

    protected List<VideoBean> mVideos = new ArrayList<>();
    protected VideoRecyclerViewAdapter mAdapter;
    protected RecyclerView mRecyclerView;
    protected LinearLayoutManager mLinearLayoutManager;

    protected VideoView mVideoView;
    protected PortraitWhenFullScreenController mController;
    protected ZKErrorView mErrorView;
    protected ZKCompleteView mCompleteView;

    /**
     * 当前播放的位置
     */
    protected int mCurPos = -1;
    /**
     * 上次播放的位置，用于页面切回来之后恢复播放
     */
    protected int mLastPos = mCurPos;

    public static void start(Context context) {
        Intent intent = new Intent(context, RecyclerViewPortraitActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.dkplayer_fragment_recycler_view;
    }

    @Override
    protected void initView() {
        super.initView();
        initVideoView();
        mRecyclerView = findViewById(R.id.rv);
        ((SimpleItemAnimator) mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);//取消动画不闪动
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new VideoRecyclerViewAdapter(mVideos);
        mAdapter.setOnItemChildClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(@NonNull View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(@NonNull View view) {
                FrameLayout playerContainer = view.findViewById(R.id.player_container);
                View v = playerContainer.getChildAt(0);
                if (v != null && v == mVideoView && !mVideoView.isFullScreen()) {
                    releaseVideoView();
                }
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == SCROLL_STATE_IDLE) { //滚动停止
                    autoPlayVideo(recyclerView);
                }
            }

            private void autoPlayVideo(RecyclerView view) {
                if (view == null) return;
                //遍历RecyclerView子控件,如果mPlayerContainer完全可见就开始播放
                int count = view.getChildCount();
                L.d("ChildCount:" + count);
                for (int i = 0; i < count; i++) {
                    View itemView = view.getChildAt(i);
                    if (itemView == null) continue;
                    VideoRecyclerViewAdapter.VideoHolder holder = (VideoRecyclerViewAdapter.VideoHolder) itemView.getTag();
                    Rect rect = new Rect();
                    holder.mPlayerContainer.getLocalVisibleRect(rect);
                    int height = holder.mPlayerContainer.getHeight();
                    if (rect.top == 0 && rect.bottom == height) {
                        startPlay(holder.mPosition);
                        break;
                    }
                }
            }
        });

        View view = findViewById(R.id.add);
        view.setVisibility(View.VISIBLE);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.addData(DataUtil.getVideoList());
            }
        });
    }

    private void initVideoView() {
        mVideoView = new VideoView(this);
        mVideoView.setScreenScaleType(VideoView.SCREEN_SCALE_CENTER_CROP);
        mVideoView.setLooping(true);
        mController = new PortraitWhenFullScreenController(this);
        mController.addDefaultControlComponent();
        mVideoView.setVideoController(mController);
        mVideoView.setOnStateChangeListener(new VideoView.SimpleOnStateChangeListener() {
            @Override
            public void onPlayStateChanged(int playState) {
                if (playState == VideoView.STATE_IDLE) {
                    Utils.removeViewFormParent(mVideoView);
                    mLastPos = mCurPos;
                    mCurPos = -1;
                }
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        List<VideoBean> videoList = DataUtil.getVideoList();
        mVideos.addAll(videoList);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.post(() -> {
            //自动播放第一个
            startPlay(0);
        });
    }


    @Override
    public void onPause() {
        super.onPause();
        pause();
    }

    /**
     * 由于onPause必须调用super。故增加此方法，
     * 子类将会重写此方法，改变onPause的逻辑
     */
    protected void pause() {
        releaseVideoView();
    }

    @Override
    public void onResume() {
        super.onResume();
        resume();
    }

    /**
     * 由于onResume必须调用super。故增加此方法，
     * 子类将会重写此方法，改变onResume的逻辑
     */
    protected void resume() {
        if (mLastPos == -1)
            return;
        //恢复上次播放的位置
        startPlay(mLastPos);
    }

    /**
     * PrepareView被点击
     */
    @Override
    public void onItemChildClick(int position) {
        startPlay(position);
    }

    /**
     * 开始播放
     * @param position 列表位置
     */
    protected void startPlay(int position) {
        if (mCurPos == position) return;
        if (mCurPos != -1) {
            releaseVideoView();
        }
        VideoBean videoBean = mVideos.get(position);
        //边播边存
        String proxyUrl = ProxyVideoCacheManager.getProxy(this).getProxyUrl(videoBean.getUrl());
        mVideoView.setUrl(proxyUrl);
        View itemView = mLinearLayoutManager.findViewByPosition(position);
        if (itemView == null) return;
        VideoRecyclerViewAdapter.VideoHolder viewHolder = (VideoRecyclerViewAdapter.VideoHolder) itemView.getTag();
        //把列表中预置的PrepareView添加到控制器中，注意isDissociate此处只能为true, 请点进去看isDissociate的解释
        mController.addControlComponent(viewHolder.mPrepareView, true);
        Utils.removeViewFormParent(mVideoView);
        viewHolder.mPlayerContainer.addView(mVideoView, 0);
        //播放之前将VideoView添加到VideoViewManager以便在别的页面也能操作它
        getVideoViewManager().add(mVideoView, Tag.LIST);
        mVideoView.start();
        mCurPos = position;

    }

    private void releaseVideoView() {
        mVideoView.release();
        if (mVideoView.isFullScreen()) {
            mVideoView.stopFullScreen();
        }
        if(this.getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        mCurPos = -1;
    }
}
