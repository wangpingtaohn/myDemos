package com.wpt.mydemos.dragback;

import android.app.Activity;
import android.graphics.Canvas;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


import com.wpt.mydemos.R;
import com.wpt.mydemos.dragback.widget.DragBackLayout;
import com.wpt.mydemos.dragback.widget.ParallaxBackLayout;

/**
 * Created by HanHailong on 2017/6/20.
 */

public class DragBackHelper extends SimpleActivityLifecycleCallbacks implements DragBackListener {

    private LinkedStack<Activity, Activity> mLinkedStack = new LinkedStack<Activity, Activity>();

    private boolean isDynamicCan = true;

    private DragBackHelper() {
    }

    public boolean isDynamicCan() {
        return isDynamicCan;
    }

    public void setDynamicCan(boolean dynamicCan) {
        isDynamicCan = dynamicCan;
    }

    @Override
    public void onDragbackFinished(Activity activity) {
        Log.d("===wpt===","onDragbackFinished");
        if (activity == null) return;

        BanDragBack banDragBack = checkAnnotation(activity.getClass());

        if (banDragBack == null) return;

        //防止混淆后ThirdWebActivity后名称被改
        if (TextUtils.equals("thirdlink", banDragBack.name())) {
//            ActionLogUtils.writeActionLogNC(activity, "thirdlink", "goback");
        }
    }

    private static final class Singleton {
        private static final DragBackHelper mInstance = new DragBackHelper();
    }

    public static DragBackHelper getInstance() {
        return Singleton.mInstance;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {

        Log.d("===wpt===","onActivityCreated");
        mLinkedStack.put(activity, activity);

        BanDragBack banDragBack = checkAnnotation(activity.getClass());

        if (banDragBack == null || !banDragBack.can()) {
            // 1. 默认不添加滑动返回, 主动禁止滑动返回.
            // 2. 跳转协议不支持滑动返回
            return;
        }

        if (mLinkedStack.size() <= 0) return;

        //其他默认带滑动返回
        ParallaxBackLayout dragBackLayout = enableDragBack(activity);

        if (dragBackLayout == null) return;

        //默认是边缘滑动，可以设置全局滑动
//        dragBackLayout.setEdgeMode(ParallaxBackLayout.EDGE_MODE_FULL);
        dragBackLayout.setEdgeMode(ParallaxBackLayout.EDGE_MODE_DEFAULT);
    }

    private BanDragBack checkAnnotation(Class<? extends Activity> activityClass) {
        try {
            Class mc = activityClass;
            BanDragBack banDragBack;
            while (Activity.class.isAssignableFrom(mc)) {
                banDragBack = (BanDragBack) mc.getAnnotation(BanDragBack.class);
                if (banDragBack != null)
                    return banDragBack;
                mc = mc.getSuperclass();
            }
        } catch (Throwable e) {
//            LOGGER.d("DragBackHelper", "checkAnnotation fail.", e);
        }
        return null;
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.d("===wpt===","onActivityDestroyed");
        mLinkedStack.remove(activity);
    }

    /**
     * Enable drag back.
     *
     * @param activity the activity
     */
    public ParallaxBackLayout enableDragBack(Activity activity) {
        ParallaxBackLayout layout = getDragBackLayout(activity, true);
        layout.setEnableGesture(true);
        return layout;
    }

    /**
     * Gets parallax back layout.
     *
     * @param activity the activity
     * @param create   the create
     * @return the drag back layout
     */
    public ParallaxBackLayout getDragBackLayout(Activity activity, boolean create) {
        View view = ((ViewGroup) activity.getWindow().getDecorView()).getChildAt(0);
        if (view instanceof ParallaxBackLayout)
            return (ParallaxBackLayout) view;
        view = activity.findViewById(R.id.draglayout);
        if (view instanceof DragBackLayout)
            return (ParallaxBackLayout) view;
        if (create) {
            ParallaxBackLayout parallaxBackLayout = new ParallaxBackLayout(activity);

            parallaxBackLayout.setId(R.id.draglayout);
            parallaxBackLayout.attachToActivity(activity, this);
            parallaxBackLayout.setBackgroundView(new GoBackView(activity));
            return parallaxBackLayout;
        }
        return null;
    }

    public static class GoBackView implements DragBackLayout.IBackgroundView {

        private Activity mActivity;
        private Activity mActivityBack;

        private GoBackView(Activity activity) {
            mActivity = activity;
        }


        @Override
        public void draw(Canvas canvas) {
            if (mActivityBack != null) {
                mActivityBack.getWindow().getDecorView().requestLayout();
                mActivityBack.getWindow().getDecorView().draw(canvas);
            }
        }

        @Override
        public boolean canGoBack() {
            return (mActivityBack = DragBackHelper.getInstance().mLinkedStack.before(mActivity)) != null && DragBackHelper.getInstance().isDynamicCan;
        }

        @Override
        public boolean canGoTo() {
            return false;
        }
    }
}
