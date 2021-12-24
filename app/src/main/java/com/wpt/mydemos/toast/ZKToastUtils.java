package com.wpt.mydemos.toast;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;

import com.wpt.mydemos.R;

/**
 * Created by wpt on 2021/8/11.
 * Toast工具类，可以在任何线程进行Toast
 * 内部处理如果不是主线程调用的，则切到主线程调用
 */
public class ZKToastUtils {

    private static Handler mHandler;

    static {
        mHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * {@link Toast#LENGTH_SHORT}+{@link CharSequence}
     *
     * @param context
     * @param text
     */
    public static void showShort(Context context, CharSequence text) {
        show(context, text, Toast.LENGTH_SHORT);
    }

    /**
     * {@link Toast#LENGTH_SHORT}+resId
     *
     * @param context
     * @param resId
     */
    public static void showShort(Context context, @StringRes int resId) {
        showShort(context, context.getResources().getText(resId));
    }

    /**
     * {@link Toast#LENGTH_LONG}+{@link CharSequence}
     *
     * @param context
     * @param text
     */
    public static void showLong(Context context, CharSequence text) {
        show(context, text, Toast.LENGTH_LONG);
    }

    /**
     * {@link Toast#LENGTH_LONG}+resId
     *
     * @param context
     * @param resId
     */
    public static void showLong(Context context, @StringRes int resId) {

    }


    /**
     * toast封装，可以在子线程内toast
     *
     * @param context
     * @param text
     * @param duration
     */
    public static void show(final Context context, final CharSequence text, final int duration) {
        Log.v("===wpt===","code=" + Build.VERSION.SDK_INT);
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
//            Toast toast = Toast.makeText(context, "", duration);
            Toast toast = new Toast(context);
            TextView textView = new TextView(context);
//            textView.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_toast));
//            textView.setPadding(30,5,30,5);
            textView.setText(text);
            toast.setView(textView);
//            toast.setText(text);
            toast.show();
//            Toast.makeText(context, text, duration).show();//直接使用该方式时，部分手机前面会加上appName的前缀，如：【人在海南：哈哈】
        } else {
            mHandler.post(() -> {
                Toast toast = Toast.makeText(context, "", duration);
                toast.setText(text);
                toast.show();
//                Toast.makeText(context, text, duration).show()
            });
        }
    }

    public static void showShortCenter(Context context, CharSequence text) {
        Toast toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        toast.setText(text);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 自定义toast
     * @param context
     * @param view
     * @param duration
     * @param gravity
     * @param xOffset
     * @param yOffset
     */
    public static Toast showCustomToast(final Context context, final View view, final int duration, final int gravity, final int xOffset, final int yOffset){
        Toast toast = new Toast(context);
        toast.setDuration(duration);
        toast.setView(view);
        toast.setGravity(gravity,xOffset,yOffset);
        toast.show();
        return toast;

    }

}
