package com.wpt.mydemos.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

/**
 * author : wpt
 * date   : 2019-12-19 13:46
 * desc   :
 */
public class PublishVerticalItemView extends LinearLayout{

    private TranslateAnimation showViewAnimation;

    private TranslateAnimation hideViewAnimation;

    private MoveListener moveListener;

    private int lastX;

    private int lastY;

    private boolean isValidMove;

    private boolean isMoveDown;


    public PublishVerticalItemView(Context context) {
        super(context);
        init();
    }

    public PublishVerticalItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PublishVerticalItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public PublishVerticalItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void setMoveListener(MoveListener l) {
        this.moveListener = l;
    }

    private void init(){
        //设置动画，从自身位置的最下端向上滑动了自身的高度，持续时间为300ms
        showViewAnimation = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 0f, TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 1f, TranslateAnimation.RELATIVE_TO_SELF, 0.3f);

        hideViewAnimation = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0.0f, TranslateAnimation.RELATIVE_TO_SELF, 0.0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0.0f, TranslateAnimation.RELATIVE_TO_SELF, 1.0f);

        showViewAnimation.setDuration(300);
        hideViewAnimation.setDuration(300);

    }

    public void show() {
        if (this.getVisibility() == View.GONE) {
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

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        Log.d("===wpt===","onTouchEvent");
//        int action = event.getAction();
//        //获取手机触摸的坐标
//        int x = (int) event.getX();
//        int y = (int) event.getY();
//        switch (action){
//            case MotionEvent.ACTION_DOWN://按下,获取小球初始的位置
//                lastX = x;
//                lastY = y;
//                break;
//            case MotionEvent.ACTION_MOVE://移动,小球跟随手指的移动
//                if (y > lastY){//向下
//                    if (y - lastY < 25){
//                        return false;
//                    }
//                    isMoveDown = true;
//                }
//                if (y < lastY){//向上
//                    isMoveDown = false;
//                    if (lastY - y  < 25 || getTop() < 200){
//                        return false;
//                    }
//                }
//                int offsetY = y - lastY;
//                Log.d("===wpt===","onTouchEvent_y=" + y + ",lastY=" + lastY);
//                layout(getLeft(),getTop()+offsetY,
//                        getRight(),getBottom()+offsetY);
//                isValidMove = true;
//                break;
//            case MotionEvent.ACTION_UP://当手指抬起时,回到小球初始的位置
//                Log.d("===wpt===","onTouchEvent");
//                if (isValidMove && moveListener != null && isMoveDown){
//                    moveListener.onMoveDown();
//                    isValidMove = false;
//                    isMoveDown = false;
//                }
//                break;
//        }
//        return true;
//    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Log.d("===wpt===","onTouchEvent");
        int action = event.getAction();
        //获取手机触摸的坐标
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (action){
            case MotionEvent.ACTION_DOWN://按下,获取小球初始的位置
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE://移动,小球跟随手指的移动
                if (y > lastY){//向下
                    if (y - lastY < 25){
                        return false;
                    }
                    isMoveDown = true;
                }
                if (y < lastY){//向上
                    isMoveDown = false;
                    if (lastY - y  < 25 || getTop() < 200){
                        return false;
                    }
                }
                int offsetY = y - lastY;
                Log.d("===wpt===","onTouchEvent_y=" + y + ",lastY=" + lastY);
                layout(getLeft(),getTop()+offsetY,
                        getRight(),getBottom()+offsetY);
                isValidMove = true;
                break;
            case MotionEvent.ACTION_UP://当手指抬起时,回到小球初始的位置
                Log.d("===wpt===","onTouchEvent");
                if (isValidMove && moveListener != null && isMoveDown){
                    moveListener.onMoveDown();
                    isValidMove = false;
                    isMoveDown = false;
                }
                break;
        }
        return super.onInterceptTouchEvent(event);
    }

    public interface MoveListener {
        void onMoveDown();
    }

}
