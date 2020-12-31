package com.wpt.mydemos.dragback.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.IntDef;
import androidx.core.view.ViewCompat;

import com.wpt.mydemos.R;
import com.wpt.mydemos.dragback.DragBackListener;
import com.wpt.mydemos.dragback.ViewDragHelper;
import com.wpt.mydemos.utils.Utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * with the parallax effect of back layout
 */
public class ParallaxBackLayout extends FrameLayout {

    private static final String TAG = "ParallaxBackLayout";

    private static final int DEFAULT_SCRIM_COLOR = 0x99000000;

    private static final int FULL_ALPHA = 255;

    @IntDef({EDGE_MODE_DEFAULT, EDGE_MODE_FULL})
    @Retention(RetentionPolicy.SOURCE)
    private @interface EdgeMode {
    }

    /**
     * Default threshold of scroll
     */
    private static final float DEFAULT_SCROLL_THRESHOLD = 0.5f;

    private static final int OVERSCROLL_DISTANCE = 0;
    private static final int EDGE_LEFT = ViewDragHelper.EDGE_LEFT;

    public static final int EDGE_MODE_FULL = 0;
    public static final int EDGE_MODE_DEFAULT = 1;

    /**
     * Threshold of scroll, we will close the activity, when scrollPercent over
     * this value;
     */
    private float mScrollThreshold = DEFAULT_SCROLL_THRESHOLD;

    private boolean mEnable = true;
    private int mEdgeMode = EDGE_MODE_DEFAULT;

    private View mContentView;

    private ViewDragHelper mDragHelper;

    private float mScrollPercent;

    private int mContentLeft;

    private int mContentTop;

    private DragBackLayout.IBackgroundView mBackgroundView;

    private Drawable mShadowLeft;

    private float mScrimOpacity;

    private int mScrimColor = DEFAULT_SCRIM_COLOR;

    private boolean mInLayout;

    /**
     * Edge being dragged
     */
    private int mTrackingEdge;
    private Activity mSwipeHelper;

    private DragBackListener mDragBackListener;

    public ParallaxBackLayout(Context context) {
        this(context, null);
    }

    public ParallaxBackLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ParallaxBackLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        mDragHelper = ViewDragHelper.create(this, new ViewDragCallback());
        mShadowLeft = getResources().getDrawable(R.drawable.dragback_shadow_left);
    }


    /**
     * Set up contentView which will be moved by user gesture
     *
     * @param view
     */
    private void setContentView(View view) {
        mContentView = view;
    }

    public View getContentView() {
        return mContentView;
    }

    public void setEnableGesture(boolean enable) {
        mEnable = enable;
    }

    /**
     * set the slide mode fullscreen or default
     *
     * @param mode
     */
    public void setEdgeMode(@EdgeMode int mode) {
        mEdgeMode = mode;
        if (mEdgeMode == EDGE_MODE_FULL) {
            int width = Utils.getScreenW(getContext());
            int height = Utils.getScreenH(getContext());
            width = Math.max(width,getWidth());
            height = Math.max(height,getHeight());
            mDragHelper.setEdgeSize(Math.max(width, height));
        }
    }

    /**
     * Set a color to use for the scrim that obscures primary content while a
     * drawer is open.
     *
     * @param color Color to use in 0xAARRGGBB format.
     */
    public void setScrimColor(int color) {
        mScrimColor = color;
        invalidate();
    }

    /**
     * Sets background view.
     *
     * @param backgroundView the background view
     */
    public void setBackgroundView(DragBackLayout.IBackgroundView backgroundView) {
        mBackgroundView = backgroundView;
    }

    /**
     * Set scroll threshold, we will close the activity, when scrollPercent over
     * this value
     *
     * @param threshold
     */
    public void setScrollThresHold(float threshold) {
        if (threshold >= 1.0f || threshold <= 0) {
            throw new IllegalArgumentException("Threshold value should be between 0 and 1.0");
        }
        mScrollThreshold = threshold;
    }

    /**
     * Scroll out contentView and finish the activity
     */
    public void scrollToFinishActivity(int duration) {
        if (!mEnable) {
            return;
        }
        final int childWidth = mContentView.getWidth();
        int left, top = 0;
        left = childWidth;
        mTrackingEdge = ViewDragHelper.EDGE_LEFT;

        mDragHelper.smoothSlideViewTo(mContentView, left, top, duration);
        invalidate();
    }

    float lastX;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (!mEnable || !mBackgroundView.canGoBack()) {
            return false;
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE){ //不处理左滑
            float x = event.getX();
            boolean isLeft = x < lastX;
            lastX = x;
            if (isLeft){
                return false;
            }
        }
        try {
            return mDragHelper.shouldInterceptTouchEvent(event);
        } catch (ArrayIndexOutOfBoundsException e) {
            // FIXME: handle exception
            // issues #9
            return false;
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!mEnable || !mBackgroundView.canGoBack()) {
            return false;
        }
        mDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        mInLayout = true;
        if (mContentView != null)
            mContentView.layout(mContentLeft, mContentTop,
                    mContentLeft + mContentView.getMeasuredWidth(),
                    mContentTop + mContentView.getMeasuredHeight());
        mInLayout = false;
    }

    @Override
    public void requestLayout() {
        if (!mInLayout) {
            super.requestLayout();
        }
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        final boolean drawContent = child == mContentView;
        boolean ret = super.drawChild(canvas, child, drawingTime);
        if (mEnable)
            drawThumb(canvas, child);
        if (mScrimOpacity > 0 && drawContent
                && mDragHelper.getViewDragState() != ViewDragHelper.STATE_IDLE) {
            drawShadow(canvas, child);
            drawScrim(canvas, child);
        }
        return ret;
    }


    private void drawScrim(Canvas canvas, View child) {
        final int baseAlpha = (mScrimColor & 0xff000000) >>> 24;
        final int alpha = (int) (baseAlpha * mScrimOpacity);
        final int color = alpha << 24 | (mScrimColor & 0xffffff);
        if ((mTrackingEdge & EDGE_LEFT) != 0) {
            canvas.clipRect(0, 0, child.getLeft(), getHeight());
        }
        canvas.drawColor(color);
    }

    private void drawThumb(Canvas canvas, View child) {
        if (child.getLeft() == 0)
            return;
        int store = canvas.save();
        int left = (child.getLeft() - getWidth()) / 2;
        canvas.translate(left, 0);
        canvas.clipRect(left, 0, (child.getLeft() + getWidth()) / 2, child.getBottom());
        mBackgroundView.draw(canvas);
        canvas.restoreToCount(store);
    }

    private void drawShadow(Canvas canvas, View child) {
        mShadowLeft.setBounds(child.getLeft() - mShadowLeft.getIntrinsicWidth(), child.getTop(),
                child.getLeft(), child.getBottom());
        mShadowLeft.setAlpha((int) (mScrimOpacity * FULL_ALPHA));
        mShadowLeft.draw(canvas);
    }

    public void attachToActivity(Activity activity, DragBackListener dragBackListener) {
        mSwipeHelper = activity;
        this.mDragBackListener = dragBackListener;
        ViewGroup decor = (ViewGroup) activity.getWindow().getDecorView();
        ViewGroup decorChild = (ViewGroup) decor.getChildAt(0);
        decor.removeView(decorChild);
        addView(decorChild);
        setContentView(decorChild);
        decor.addView(this);
    }

    @Override
    public void computeScroll() {
        mScrimOpacity = 1 - mScrollPercent;
        if (mDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private class ViewDragCallback extends ViewDragHelper.Callback {
        private boolean mIsScrollOverValid;

        @Override
        public boolean tryCaptureView(View view, int i) {
            boolean ret = mDragHelper.isEdgeTouched(ViewDragHelper.EDGE_LEFT, i);
            if (ret) {
                mTrackingEdge = EDGE_LEFT;
                mIsScrollOverValid = true;
            }
            boolean directionCheck = !mDragHelper.checkTouchSlop(ViewDragHelper.DIRECTION_VERTICAL, i);
            return ret & directionCheck;
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return 1;
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return 0;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            if ((mTrackingEdge & EDGE_LEFT) != 0) {
                mScrollPercent = Math.abs((float) left
                        / mContentView.getWidth());
            }
            mContentLeft = left;
            mContentTop = top;
            invalidate();
            if (mScrollPercent < mScrollThreshold && !mIsScrollOverValid) {
                mIsScrollOverValid = true;
            }

            if (mScrollPercent >= 1) {
                if (!mSwipeHelper.isFinishing()) {
                    if (mDragBackListener != null) {
                        mDragBackListener.onDragbackFinished(mSwipeHelper);
                    }
                    mSwipeHelper.finish();
                    mSwipeHelper.overridePendingTransition(0, 0);
                }
            }
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            final int childWidth = releasedChild.getWidth();
            int left = 0, top = 0;
            if ((mTrackingEdge & EDGE_LEFT) != 0) {
                left = xvel > 0 || mScrollPercent > mScrollThreshold ? childWidth : 0;
            }
            mDragHelper.settleCapturedViewAt(left, top);
            invalidate();
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            int ret = 0;
            if ((mTrackingEdge & EDGE_LEFT) != 0) {
                ret = Math.min(child.getWidth(), Math.max(left, 0));
            }
            return ret;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return 0;
        }

        @Override
        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
        }
    }
}
