package com.wpt.mydemos.textview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * author : wpt
 * date   : 2020-09-28 08:59
 * desc   : 中黑体TextView
 */
public class FakeBoldTextView extends AppCompatTextView {
    public FakeBoldTextView(Context context) {
        super(context);
        init();
    }

    public FakeBoldTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FakeBoldTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init(){
        this.getPaint().setFakeBoldText(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE){
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return super.onTouchEvent(event);
    }
}
