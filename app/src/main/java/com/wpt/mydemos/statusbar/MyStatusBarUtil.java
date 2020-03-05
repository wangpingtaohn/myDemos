package com.wpt.mydemos.statusbar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;


import com.jaeger.library.StatusBarUtil;
import com.wpt.mydemos.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MyStatusBarUtil {
    public static void setRNBar(Activity activity, Boolean isStatusBarDarkContent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decorView = activity.getWindow().getDecorView();
            int vis = decorView.getSystemUiVisibility();
            vis |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            if (isStatusBarDarkContent) {
                vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                vis |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
//                activity.getWindow().setNavigationBarColor(ContextCompat.getColor(activity, R.color.head_bg_cocor));
//            } else {
            activity.getWindow().setNavigationBarColor(ContextCompat.getColor(activity, R.color.head_bg_cocor));
//            }

            MyStatusBarUtil.setMIUIStatusBarDarkIcon(activity, isStatusBarDarkContent);
            MyStatusBarUtil.setMeizuStatusBarDarkIcon(activity, isStatusBarDarkContent);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vis |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
            }
            decorView.setSystemUiVisibility(vis);
        } else {
            StatusBarUtil.setColorForSwipeBack(activity, ContextCompat.getColor(activity, R.color.head_bg_cocor), 112);
        }
    }

    public static void setStatusBarColor(Activity activity, @ColorRes int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarUtil.setColorForSwipeBack(activity, ContextCompat.getColor(activity, id), 0);
            View decorView = activity.getWindow().getDecorView();
            int vis = decorView.getSystemUiVisibility();
            vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                vis |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
//                activity.getWindow().setNavigationBarColor(ContextCompat.getColor(activity, R.color.head_bg_cocor));
//            } else {
            activity.getWindow().setNavigationBarColor(ContextCompat.getColor(activity, R.color.head_bg_cocor));
//            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vis |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
            }
            decorView.setSystemUiVisibility(vis);
            setMIUIStatusBarDarkIcon(activity, true);
            setMeizuStatusBarDarkIcon(activity, true);
        } else {
            StatusBarUtil.setColorForSwipeBack(activity, ContextCompat.getColor(activity, id), 112);
        }


    }

    public static void setStatusBarWhite(Activity activity) {
        int statusBarColor = ContextCompat.getColor(activity, R.color.white);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarUtil.setColorForSwipeBack(activity, statusBarColor, 0);
            View decorView = activity.getWindow().getDecorView();
            int vis = decorView.getSystemUiVisibility();
            vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                vis |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
//                activity.getWindow().setNavigationBarColor(ContextCompat.getColor(activity, R.color.head_bg_cocor));
//            } else {
            activity.getWindow().setNavigationBarColor(statusBarColor);
//            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vis |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
            }
            decorView.setSystemUiVisibility(vis);
            setMIUIStatusBarDarkIcon(activity, true);
            setMeizuStatusBarDarkIcon(activity, true);
        } else {
            StatusBarUtil.setColorForSwipeBack(activity, statusBarColor, 112);
        }
//        StatusBarUtil.setColor(activity, ContextCompat.getColor(activity, R.color.head_bg_cocor), 0);

    }

    public static void setStatusBarTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            StatusBarUtil.setTransparent(activity);
//            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

//            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            View decorView = activity.getWindow().getDecorView();
            int vis = decorView.getSystemUiVisibility();
            vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vis |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
            }
            decorView.setSystemUiVisibility(vis);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                vis |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
//                decorView.setSystemUiVisibility(vis);
//                activity.getWindow().setNavigationBarColor(ContextCompat.getColor(activity, R.color.head_bg_cocor));
//            } else {
            activity.getWindow().setNavigationBarColor(ContextCompat.getColor(activity, R.color.head_bg_cocor));
//            }
            setRootView(activity);
        } else {
            StatusBarUtil.setColorForSwipeBack(activity, ContextCompat.getColor(activity, R.color.head_bg_cocor), 112);
        }

    }


    public static void setStatusBarTransparentFullScreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            View decorView = activity.getWindow().getDecorView();
            int vis = decorView.getSystemUiVisibility();
            vis |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                vis |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
//                activity.getWindow().setNavigationBarColor(ContextCompat.getColor(activity, R.color.head_bg_cocor));
//            } else {
            activity.getWindow().setNavigationBarColor(ContextCompat.getColor(activity, R.color.head_bg_cocor));
//            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vis |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
            }
            decorView.setSystemUiVisibility(vis);
//            setRootView(activity);
        } else {
            StatusBarUtil.setColorForSwipeBack(activity, ContextCompat.getColor(activity, R.color.head_bg_cocor), 112);
        }

    }


    private static void setRootView(Activity activity) {
        ViewGroup parent = activity.findViewById(android.R.id.content);
        for (int i = 0, count = parent.getChildCount(); i < count; i++) {
            View childView = parent.getChildAt(i);
            if (childView instanceof ViewGroup) {
                childView.setFitsSystemWindows(true);
                ((ViewGroup) childView).setClipToPadding(true);
            }
        }
    }

//    public static void setStatusBarHeadColorForInput(Activity activity, ViewGroup llContent) {
//        setStatusBarHeadColorForInput(activity, llContent, false);
//    }
//
//    public static void setStatusBarHeadColorForInput(Activity activity, ViewGroup llContent, boolean isfit) {
//        ViewGroup contentView = activity.findViewById(android.R.id.content);
//        View lltop = LayoutInflater.from(activity).inflate(R.layout.item_state_bar, null);
//        View tvBar = lltop.findViewById(R.id.bar_view);
//
//        ViewGroup.LayoutParams params = tvBar.getLayoutParams();
//        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        params.height = getStatusBarHeight(activity);
//
//        tvBar.setLayoutParams(params);
//        if (!isfit)
//            llContent.addView(lltop, 0);
//        activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            contentView.setBackgroundColor(ContextCompat.getColor(activity, R.color.head_bg_cocor));
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                llContent.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
////                        | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//                activity.getWindow().setNavigationBarColor(ContextCompat.getColor(activity, R.color.head_bg_cocor));
//            } else {
//                llContent.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//                activity.getWindow().setNavigationBarColor(ContextCompat.getColor(activity, R.color.head_bg_cocor));
//            }
//            setMIUIStatusBarDarkIcon(activity, true);
//            setMeizuStatusBarDarkIcon(activity, true);
//
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
//            }
//        } else {
//            contentView.setBackgroundColor(
//                    calculateStatusColor(ContextCompat.getColor(activity, R.color.head_bg_cocor), 112));
//            tvBar.setBackgroundColor(calculateStatusColor(ContextCompat.getColor(activity, R.color.head_bg_cocor), 112));
//            llContent.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        }
//    }


    public static void setLightMode(Activity activity) {
        setMIUIStatusBarDarkIcon(activity, true);
        setMeizuStatusBarDarkIcon(activity, true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }


    public static void setDarkMode(Activity activity) {
        setMIUIStatusBarDarkIcon(activity, false);
        setMeizuStatusBarDarkIcon(activity, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            activity.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
//                    View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
//        }
    }

    /**
     * 计算状态栏颜色
     *
     * @param color color值
     * @param alpha alpha值
     * @return 最终的状态栏颜色
     */
    private static int calculateStatusColor(@ColorInt int color, int alpha) {
        if (alpha == 0) {
            return color;
        }
        float a = 1 - alpha / 255f;
        int red = color >> 16 & 0xff;
        int green = color >> 8 & 0xff;
        int blue = color & 0xff;
        red = (int) (red * a + 0.5);
        green = (int) (green * a + 0.5);
        blue = (int) (blue * a + 0.5);
        return 0xff << 24 | red << 16 | green << 8 | blue;
    }

    /**
     * 获取状态栏高度
     *
     * @param context context
     * @return 状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    /**
     * 修改 MIUI V6  以上状态栏颜色
     */
    public static void setMIUIStatusBarDarkIcon(@NonNull Activity activity, boolean darkIcon) {
//        if (!OSHelper.isMIUI())
//            return;
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            int darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkIcon ? darkModeFlag : 0, darkModeFlag);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    /**
     * 修改魅族状态栏字体颜色 Flyme 4.0
     */
    public static void setMeizuStatusBarDarkIcon(@NonNull Activity activity, boolean darkIcon) {
//        if (!OSHelper.isFlyme())
//            return;
        try {
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
            darkFlag.setAccessible(true);
            meizuFlags.setAccessible(true);
            int bit = darkFlag.getInt(null);
            int value = meizuFlags.getInt(lp);
            if (darkIcon) {
                value |= bit;
            } else {
                value &= ~bit;
            }
            meizuFlags.setInt(lp, value);
            activity.getWindow().setAttributes(lp);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    /**
     * 底部虚拟按键栏的高度
     * @param mActivity
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static int getSoftButtonsBarHeight(Activity mActivity) {
        DisplayMetrics metrics = new DisplayMetrics();
        //这个方法获取可能不是真实屏幕的高度
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int usableHeight = metrics.heightPixels;
        //获取当前屏幕的真实高度
        mActivity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        int realHeight = metrics.heightPixels;
        if (realHeight > usableHeight) {
            return realHeight - usableHeight;
        } else {
            return 0;
        }
    }
}
