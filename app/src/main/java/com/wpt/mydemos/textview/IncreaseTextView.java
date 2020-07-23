package com.wpt.mydemos.textview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.wpt.mydemos.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * author : wpt
 * date   : 2020-07-22 15:09
 * desc   :
 */
public class IncreaseTextView extends View {
    private int num=100;//数值
    private Paint mPaint;//画笔
    private int textColor= Color.WHITE;
    private float textSize;//字体大小
    private float textHeight=15f;//
    private float textWidth=15;
    private float textDimen;//字与字之间的间距
    private int aniDuration=500;//单位毫秒  动画时间
    private List<List<Integer>> nums=new ArrayList<>();//数字表
    private HashMap<Integer,float[]> numStatus=new HashMap<>();//状态map
    private String TAG="fhpp";
    public IncreaseTextView(Context context) {
        this(context,null);
    }

    public IncreaseTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public IncreaseTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.IncreaseTextView);
        num = array.getInteger(R.styleable.IncreaseTextView_num, 100);
        textColor=array.getColor(R.styleable.IncreaseTextView_text_color, Color.WHITE);
        textSize = array.getDimension(R.styleable.IncreaseTextView_text_size, 15f);
        textDimen = array.getDimension(R.styleable.IncreaseTextView_text_dimen, 5f);
        aniDuration = array.getInteger(R.styleable.IncreaseTextView_ani_duration, 3000);

        init();
    }

    //初始化画笔
    private void init() {
        mPaint=new Paint();
        mPaint.setColor(textColor);
        mPaint.setTextSize(textSize);
        mPaint.setAntiAlias(true);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
    }
    private void startIncrese() {
        Rect rect = new Rect();
        mPaint.getTextBounds("0",0,1,rect);
        textHeight=rect.bottom-rect.top;
        textWidth=rect.right-rect.left;

        String numStr = String.valueOf(num);
        float x=0f;
        for (int i = 0; i < numStr.length(); i++) {
            String substring = numStr.substring(i, i + 1);
            x=x+rect.right-rect.left+textDimen;
            ArrayList<Integer> integers = new ArrayList<>();
            for (int j = 0; j <= Integer.parseInt(substring); j++) {
                integers.add(j);
            }
            nums.add(integers);
        }


        for (int i = 0; i < nums.size(); i++) {
            final List<Integer> intList = nums.get(i);
            ValueAnimator animator = ObjectAnimator.ofFloat(intList.get(intList.size() - 1)+0f,intList.get(0)+0f);
            animator.setDuration(aniDuration);
            final int finalI = i;
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float value = (float) valueAnimator.getAnimatedValue();
                    String valeStr = String.valueOf(value);
                    String[] split = valeStr.split("\\.");
                    if (split.length<2){

                    }else {

                        int nowNum = Integer.parseInt(split[0]);
                        String perNumStr = "0."+split[1];
                        float perNum = Float.parseFloat(perNumStr);
                        numStatus.put(finalI,new float[]{nowNum,perNum});

                    }
                    requestLayout();//重新绘制布局大小

                    invalidate();

                }
            });
            animator.start();
        }
    }


    public String getNum(){
        return String.valueOf(num);
    }
    public void setNum(int num){
//        nums.clear();
//        numStatus.clear();
        this.num=num;
        startIncrese();

    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int hight = MeasureSpec.getSize(heightMeasureSpec);
        int widht = MeasureSpec.getSize(widthMeasureSpec);
        if (heightMode== MeasureSpec.AT_MOST){
            hight= (int) textHeight;
        }
        if (wMode== MeasureSpec.AT_MOST){
            widht= (int) (numStatus.size()*textWidth);
        }
        if (numStatus.size()==0){
            int measuredWidth = (int) (textWidth);
            setMeasuredDimension(measuredWidth,hight+15);

        }else {
            int measuredWidth = (int) (widht + numStatus.size() * textDimen);
            setMeasuredDimension(measuredWidth,hight+15);

        }
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < numStatus.size(); i++) {
            float[] status = numStatus.get(i);
            float nowNum = status[0];
            float nexNum = nowNum + 1;
            float perNum = status[1];

            if (perNum!=0f){
                //需要画两个数字
                canvas.drawText(String.valueOf((int)nowNum),i*textWidth+i*textDimen,textHeight+perNum*textHeight,mPaint);
                canvas.drawText(String.valueOf((int) nexNum),i*textWidth+i*textDimen,perNum*textHeight,mPaint);
            }else {
                //只需要画一个数字
                canvas.drawText(String.valueOf((int)nowNum), i *textWidth+i*textDimen,textHeight,mPaint);
            }

        }

    }

}
