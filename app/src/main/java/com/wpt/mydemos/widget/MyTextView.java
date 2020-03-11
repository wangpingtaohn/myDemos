package com.wpt.mydemos.widget;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.wpt.mydemos.R;

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
        init(context,attrs);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context,@Nullable AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
        String type = typedArray.getString(R.styleable.MyTextView_myText);
        setCustomText(type);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        count++;
        Log.d("===wpt===","MyTextView_onMeasure_第" + count + "次");
    }

    public void setCustomText(CharSequence text){
        if (text == null){
            return;
        }
        int lineHeight = (int) getTextSize();
        SpannableStringBuilder ssb;
        if (text instanceof SpannableStringBuilder){
            ssb = (SpannableStringBuilder) text;
            ssb.setSpan(new ExcludeInnerLineSpaceSpan(lineHeight),0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            ssb = new SpannableStringBuilder(text);
            ssb.setSpan(new ExcludeInnerLineSpaceSpan(lineHeight),0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        setText(ssb);
    }
}
