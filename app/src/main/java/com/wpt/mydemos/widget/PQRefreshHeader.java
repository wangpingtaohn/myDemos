package com.wpt.mydemos.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.wpt.mydemos.R;

import org.jetbrains.annotations.NotNull;

/**
 * author : wpt
 * date   : 2021/8/217:55
 * desc   :
 */
public class PQRefreshHeader extends LinearLayout implements RefreshHeader {

    private FrameAnimImageView faiv;

    public PQRefreshHeader(Context context) {
        super(context);
        initView(context);
    }

    public PQRefreshHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PQRefreshHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        faiv = new FrameAnimImageView(context);
        faiv.setImageResource(R.drawable.animlist_video_love);
        faiv.setAnimDrawable((AnimationDrawable) faiv.getDrawable());
        addView(faiv);
    }

    @NonNull
    @NotNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @NotNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(int... ints) {

    }

    @Override
    public void onInitialized(@NonNull @NotNull RefreshKernel refreshKernel, int i, int i1) {

    }

    @Override
    public void onMoving(boolean b, float v, int i, int i1, int i2) {

    }

    @Override
    public void onReleased(@NonNull @NotNull RefreshLayout refreshLayout, int i, int i1) {

    }

    @Override
    public void onStartAnimator(@NonNull @NotNull RefreshLayout refreshLayout, int i, int i1) {
        faiv.startAnimation();
    }

    @Override
    public int onFinish(@NonNull @NotNull RefreshLayout refreshLayout, boolean b) {
        faiv.stopAnimation();
        return 200;
    }

    @Override
    public void onHorizontalDrag(float v, int i, int i1) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(@NonNull @NotNull RefreshLayout refreshLayout, @NonNull @NotNull RefreshState oldState, @NonNull @NotNull RefreshState newState) {
        switch (newState) {
            case None:
            case PullDownToRefresh:
                /*mHeaderText.setText("下拉开始刷新");
                mArrowView.setVisibility(VISIBLE);//显示下拉箭头
                mProgressView.setVisibility(GONE);//隐藏动画
                mArrowView.animate().rotation(0);//还原箭头方向*/
                break;
            case Refreshing:
                /*mHeaderText.setText("正在刷新");
                mProgressView.setVisibility(VISIBLE);//显示加载动画
                mArrowView.setVisibility(GONE);//隐藏箭头*/
                break;
            case ReleaseToRefresh:
                /*mHeaderText.setText("释放立即刷新");
                mArrowView.animate().rotation(180);//显示箭头改为朝上*/
                break;
        }
    }
}
