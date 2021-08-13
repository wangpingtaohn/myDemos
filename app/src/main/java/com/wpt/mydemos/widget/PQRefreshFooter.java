package com.wpt.mydemos.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

import org.jetbrains.annotations.NotNull;

/**
 * author : wpt
 * date   : 2021/8/217:55
 * desc   :
 */
public class PQRefreshFooter extends LinearLayout implements RefreshFooter {

    public PQRefreshFooter(Context context) {
        super(context);
        initView(context);
    }

    public PQRefreshFooter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PQRefreshFooter(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    @Override
    public boolean setNoMoreData(boolean b) {
        return false;
    }

    private void initView(Context context){
        TextView textView = new TextView(context);
        textView.setText("加载更多");
        addView(textView);
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

    }

    @Override
    public int onFinish(@NonNull @NotNull RefreshLayout refreshLayout, boolean b) {
        return 0;
    }

    @Override
    public void onHorizontalDrag(float v, int i, int i1) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(@NonNull @NotNull RefreshLayout refreshLayout, @NonNull @NotNull RefreshState refreshState, @NonNull @NotNull RefreshState refreshState1) {

    }
}
