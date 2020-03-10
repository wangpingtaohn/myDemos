package com.wpt.mydemos.widget;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * author : wpt
 * date   : 2020-03-10 17:51
 * desc   :
 */
@SuppressLint("AppCompatCustomView")
public class MyLinearLayout extends LinearLayout {

    int count = 0;

    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        count++;
        Log.d("===wpt===","MyLinearLayout_onMeasure_第" + count + "次");
    }

}
