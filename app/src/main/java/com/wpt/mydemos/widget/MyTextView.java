package com.wpt.mydemos.widget;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * author : wpt
 * date   : 2020-03-10 17:51
 * desc   :
 */
@SuppressLint("AppCompatCustomView")
public class MyTextView extends TextView {

    int count = 0;

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        count++;
        Log.d("===wpt===","MyTextView_onMeasure_第" + count + "次");
    }
}
