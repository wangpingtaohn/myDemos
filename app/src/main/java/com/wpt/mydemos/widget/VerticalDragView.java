package com.wpt.mydemos.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * author : wpt
 * date   : 2019-12-19 13:46
 * desc   :
 */
public class VerticalDragView extends LinearLayout{

    private TranslateAnimation showViewAnimation;

    private TranslateAnimation hideViewAnimation;

    private MoveListener moveListener;

    private int lastY;

    private boolean isValidMove;

    private boolean isMoveDown;

    private boolean isMoveUp;

    private boolean isLongAct;


    public VerticalDragView(Context context) {
        super(context);
        init();
    }

    public VerticalDragView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VerticalDragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setMoveListener(MoveListener l) {
        this.moveListener = l;
    }

    private void init(){
        //设置动画，从自身位置的最下端向上滑动了自身的高度，持续时间为300ms
        showViewAnimation = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 0f, TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 1f, TranslateAnimation.RELATIVE_TO_SELF, 0f);

        hideViewAnimation = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0.0f, TranslateAnimation.RELATIVE_TO_SELF, 0.0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0.0f, TranslateAnimation.RELATIVE_TO_SELF, 1.0f);

        showViewAnimation.setDuration(300);
        hideViewAnimation.setDuration(300);
    }

    public void show() {
        if (this.getVisibility() == View.GONE) {
            ViewGroup.LayoutParams vlp = this.getLayoutParams();
            if (vlp instanceof RelativeLayout.LayoutParams){
                ((RelativeLayout.LayoutParams) vlp).bottomMargin = 0;
            } else if (vlp instanceof LinearLayout.LayoutParams){
                ((LinearLayout.LayoutParams) vlp).bottomMargin = 0;
            }
            this.setLayoutParams(vlp);
            this.setVisibility(View.VISIBLE);
            this.startAnimation(showViewAnimation);
        }
    }

    public void hide() {
        if (this.getVisibility() == View.VISIBLE) {
            this.setVisibility(View.GONE);
            this.startAnimation(hideViewAnimation);
        }
    }

    long lastTime;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        int viewH = this.getHeight();
        int action = event.getAction();
        //获取手机触摸的坐标
        int x = (int) event.getX();
        int y = (int) event.getY();
        Log.d("===wpt===","onInterceptTouchEvent_y=" + y + ",lastY=" + lastY + ",viewH=" + viewH);
        switch (action){
            case MotionEvent.ACTION_DOWN://按下
                lastTime = System.currentTimeMillis();
                Log.d("===wpt===","onInterceptTouchEvent_DOWN_y=" + y + ",lastY=" + lastY);
                lastY = y;
                isValidMove = false;
                break;
            case MotionEvent.ACTION_MOVE://移动
                Log.d("===wpt===","onInterceptTouchEvent_MOVE" + ",Math.abs(y - lastY)=" + Math.abs(y - lastY));
                if (Math.abs(y - lastY) < 25){
                    isValidMove = false;
                } else {
                    if (y > lastY){//向下
                        if (y - lastY < 25){
                            isValidMove = false;
                        } else {
                            isValidMove = false;
                            isMoveUp = false;
                            int offsetY = y - lastY;
                            layout(getLeft(),getTop()+offsetY,
                                    getRight(),getBottom()+offsetY);
                        }
                    }
                    if (y < lastY){//向上
                        isMoveDown = false;
                        isMoveUp = true;
//                    int limitH = Tools.getScreenH(getContext()) - Tools.dip2px(getContext(), isLongAct ? 0 : 50);
                        if (lastY - y  < 25){
                            isValidMove = false;
                        } else {
                            isValidMove = true;
//                            int offsetY = y - lastY;
//                            layout(getLeft(),getTop()+offsetY,
//                                    getRight(),getBottom()+offsetY);
                        }
                    }
                }
//                Log.d("===wpt===","onInterceptTouchEvent_MOVE_y=" + y + ",lastY=" + lastY
//                        + ",getBottom()" + getBottom() + ",ScreenH=" + Tools.getScreenH(getContext()));

//                isValidMove = true;
                break;
            case MotionEvent.ACTION_UP://当手指抬起
                Log.d("===wpt===","onInterceptTouchEvent_UP");
//                if (isValidMove && moveListener != null && (isMoveDown || isMoveUp)){
//                    if (isMoveDown){
//                        moveListener.onMoveDown();
//                    }
//                    isValidMove = false;
//                    isMoveDown = false;
//                    isMoveUp = false;
//                    return true;
//                } else {
//                    return false;
//                }
                isValidMove = false;
                break;
        }
        return isValidMove;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN://按下
//                lastTime = System.currentTimeMillis();
//                Log.d("===wpt===","onInterceptTouchEvent_DOWN_y=" + y + ",lastY=" + lastY);
//                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE://移动
                Log.d("===wpt===","onInterceptTouchEvent_MOVE" + ",Math.abs(y - lastY)=" + Math.abs(y - lastY));
//                if (Math.abs(y - lastY) < 25){
//                    return false;
//                }
////                Log.d("===wpt===","onInterceptTouchEvent_MOVE_y=" + y + ",lastY=" + lastY
////                        + ",getBottom()" + getBottom() + ",ScreenH=" + Tools.getScreenH(getContext()));
//                if (y > lastY){//向下
//                    if (y - lastY < 25){
//                        return false;
//                    }
//                    isMoveDown = true;
//                    isMoveUp = false;
//                }
//                if (y < lastY){//向上
//                    isMoveDown = false;
//                    isMoveUp = true;
////                    int limitH = Tools.getScreenH(getContext()) - Tools.dip2px(getContext(), isLongAct ? 0 : 50);
////                    if (lastY - y  < 25 || getBottom() < limitH){
////                        return false;
////                    }
//                }
                int offsetY = y - lastY;
                layout(getLeft(),getTop()+offsetY,
                        getRight(),getBottom()+offsetY);
                isValidMove = true;
                break;
            case MotionEvent.ACTION_UP://当手指抬起
//                Log.d("===wpt===","onInterceptTouchEvent_UP");
//                if (isValidMove && moveListener != null && (isMoveDown || isMoveUp)){
//                    if (isMoveDown){
//                        moveListener.onMoveDown();
//                    }
//                    isValidMove = false;
//                    isMoveDown = false;
//                    isMoveUp = false;
//                    return true;
//                } else {
//                    return false;
//                }
                break;
        }
        return true;
    }

    public interface MoveListener {
        void onMoveDown();
    }

    public void setLongAct(boolean longAct) {
        this.isLongAct = longAct;
    }

}
