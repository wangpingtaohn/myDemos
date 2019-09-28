package com.wpt.mydemos.keyboard;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;


public class ResizeLayout extends RelativeLayout {


    // 定义默认的软键盘最小高度，这是为了避免onSizeChanged在某些下特殊情况下出现的问题
    private final int SOFTKEYPAD_MIN_HEIGHT = 2 * 48;

    private OnInputSoftListener onInputSoftListener;

    public ResizeLayout(Context context) {
        super(context);
    }

    public ResizeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setInputSoftListener(OnInputSoftListener listener) {
        onInputSoftListener = listener;
    }

    @Override
    protected void onSizeChanged(int w, final int h, int oldw, final int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (onInputSoftListener == null || oldw == 0 || oldh == 0) {
            return;
        }
        post(() -> {
            if (oldh - h > SOFTKEYPAD_MIN_HEIGHT) {
                onInputSoftListener.onSoftInputShow();
            } else if (h - oldh > SOFTKEYPAD_MIN_HEIGHT) {
                onInputSoftListener.onSoftInputHide();
            }
        });
    }

    /**
     * 软键盘弹出时回调函数
     */

    public interface OnInputSoftListener {

        // 软键盘显示的回调方法
        void onSoftInputShow();

        // 软键盘隐藏时的回调方法
        void onSoftInputHide();
    }
}
