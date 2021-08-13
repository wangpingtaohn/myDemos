package com.wpt.mydemos.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import androidx.annotation.Nullable;

import com.wpt.mydemos.R;


/**
 * 实现了帧动画的ImageView
 * 播放的图片过多不建议使用，避免OOM
 * 两种使用方式：在drawable里创建xml的animation-list文件，把要播放的动画图片直接添加进去
 *   然后在布局文件里添加src，或者在代码里设置setImageResource，然后setAnimDrawable传进来
 * Created by wpt on 2021/08/02.
 */

public class FrameAnimImageView extends androidx.appcompat.widget.AppCompatImageView {

    //是否重复播放
    private boolean isRepeat;
    //是否自动播放
    private boolean isAutoPlay;

    private android.graphics.drawable.AnimationDrawable animDrawable;

    public FrameAnimImageView(Context context) {
        this(context, null);
    }

    public FrameAnimImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FrameAnimImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.FrameAnimImageView);
        isRepeat = ta.getBoolean(R.styleable.FrameAnimImageView_anim_isRepeat,true);
        isAutoPlay = ta.getBoolean(R.styleable.FrameAnimImageView_anim_isAutoPlay,true);
        initAnim();
    }

    private void initAnim() {

        animDrawable = (AnimationDrawable) getDrawable();
        if (animDrawable == null){
            return;
        }
        animDrawable.setOneShot(!isRepeat);
        if (isAutoPlay){
            startAnimation();
        }
    }

    public void setAnimDrawable(AnimationDrawable animDrawable) {
        this.animDrawable = animDrawable;
    }

    public void startAnimation(){
        if (animDrawable == null){
            return;
        }
        animDrawable.start();
    }


    public void stopAnimation(){
        if (animDrawable == null){
            return;
        }
        animDrawable.stop();
    }
}
