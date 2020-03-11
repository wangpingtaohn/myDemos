package com.wpt.mydemos.widget;

import android.graphics.Paint;
import android.text.style.LineHeightSpan;

/**
 * author : wpt
 * date   : 2020-03-11 10:28
 * desc   :
 */
public class ExcludeInnerLineSpaceSpan implements LineHeightSpan {

    private final int mHeight;

    public ExcludeInnerLineSpaceSpan(int height){
        this.mHeight = height;
    }
    @Override
    public void chooseHeight(CharSequence charSequence, int start, int end, int spanstartv, int lineHeight, Paint.FontMetricsInt fontMetricsInt) {
        final int originHeight = fontMetricsInt.descent - fontMetricsInt.ascent;
        if (originHeight <= 0){
            return;
        }
        final float ratio = mHeight * 1.0f / originHeight;
        fontMetricsInt.descent = Math.round(fontMetricsInt.descent * ratio);
        fontMetricsInt.ascent = fontMetricsInt.descent - mHeight;
    }
}
