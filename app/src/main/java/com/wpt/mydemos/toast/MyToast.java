package com.wpt.mydemos.toast;

/**
 * Author: wpt
 * Time: 2021/12/14
 *
 * @Desc：
 */
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wpt.mydemos.R;

import java.util.Timer;
import java.util.TimerTask;



public class MyToast extends Toast {
    private Context mContext;
    private Toast toast;
    public static MyToast lsToast;
    private View toastView;

    public MyToast(Context context) {
        super(context);
        this.mContext = context;
        toastView = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);

        if (null == toast) {
            toast = new Toast(context);
        }

    }

    public static MyToast getInstance(Context context) {
//        if (null == lsToast) {
        lsToast = new MyToast(context);
//        }
        return lsToast;
    }


    /**
     *
     * @param str
     * @param duration
     */
    public void show(String str,int duration){
        toast.setDuration(duration);
        TextView textView = toastView.findViewById(R.id.tv_ls_toast);
        textView.setText(str);
        toast.setView(toastView);
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toast.show();
            }
        }, 0, 10*1000);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toast.cancel();
                timer.cancel();
            }
        }, 10*1000 );
    }
}
