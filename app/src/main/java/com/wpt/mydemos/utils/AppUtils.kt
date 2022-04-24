package com.wpt.mydemos.utils

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Process
import android.text.TextUtils
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import java.util.regex.Pattern







object AppUtils {
    private var sVersion: String? = null
    private var sVersionCode: String? = null
    private var sVersionName: String? = null

    /**
     * 获取版名称
     */
    fun getVersion(context: Context): String? {
        if (TextUtils.isEmpty(sVersion)) {
            try {
                val manager: PackageManager = context.packageManager
                val info: PackageInfo =
                    manager.getPackageInfo(context.packageName, 0)
                sVersion = info.versionName
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return sVersion
    }

    /**
     * 获取版本号
     */
    fun getVersionCode(context: Context): String {
        if (TextUtils.isEmpty(sVersionCode)) {
            try {
                val manager: PackageManager = context.packageManager
                val info: PackageInfo =
                    manager.getPackageInfo(context.packageName, 0)
                sVersionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    info.longVersionCode.toString() + ""
                } else {
                    info.versionCode.toString() +""
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return if (!TextUtils.isEmpty(sVersionCode)) sVersionCode!! else ""
    }

    /**
     * 获取版本号
     */
    fun getVersionName(context: Context): String {
        if (TextUtils.isEmpty(sVersionName)) {
            try {
                val manager: PackageManager = context.packageManager
                val info: PackageInfo =
                    manager.getPackageInfo(context.packageName, 0)
                sVersionName = info.versionName + ""
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return if (!TextUtils.isEmpty(sVersionName)) sVersionName!! else ""
    }

    //    1、获得当前activity的名字
    fun getRunningActivityName(context: Context): String {
        val activityManager =
            context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        //完整类名
        val runningActivity =
            activityManager.getRunningTasks(1)[0].topActivity!!.className
        return runningActivity.substring(runningActivity.lastIndexOf(".") + 1)
    }

    //    2、获得当前应用包名
    fun getAppPackageName(context: Context): String {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val taskInfo = activityManager.getRunningTasks(1)
        val componentInfo = taskInfo[0].topActivity
        return if (componentInfo !== null) componentInfo.packageName else ""
    }

    fun isWechatInstalled(context: Context) = isAppInstalled(context, "com.tencent.mm")

    fun isAppInstalled(context: Context, pkgName: String): Boolean {
        if (pkgName.isEmpty()) return false

        return try {
            context.packageManager.getPackageInfo(pkgName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            null
        }.takeIf {
            it != null
        }?.let {
            true
        } ?: false
    }

    fun getAppIcon(context: Context, pkgName: String) =
        context.packageManager.getApplicationIcon(pkgName)

    /**
     * 验证手机格式
     */
    fun isMobileNO(phone: String): Boolean {
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
         * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
         * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
         */
        val compile = Pattern.compile("^(13|14|15|16|17|18|19)\\d{9}$")
        val matcher = compile.matcher(phone)
        return matcher.matches()

    }

    /**
     * 数字跟字母组合 8-24位
     */
    fun numbersAndLetters(phone: String): Boolean {

        val compile = Pattern.compile( "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,24}$")
        val matcher = compile.matcher(phone)
        return matcher.matches()

    }

    private var currentProcessName: String? = null

    /**
     * @return 当前进程名
     */
    @Nullable
    fun getCurrentProcessName(context: Context?): String? {
        if (context == null){
            return currentProcessName
        }
        if (!TextUtils.isEmpty(currentProcessName)) {
            return currentProcessName
        }

        //1)通过Application的API获取当前进程名
        currentProcessName = getCurrentProcessNameByApplication(context)
        if (!TextUtils.isEmpty(currentProcessName)) {
            return currentProcessName
        }

        //2)通过反射ActivityThread获取当前进程名
        currentProcessName = getCurrentProcessNameByActivityThread()
        if (!TextUtils.isEmpty(currentProcessName)) {
            return currentProcessName
        }

        //3)通过ActivityManager获取当前进程名
        currentProcessName = getCurrentProcessNameByActivityManager(context)
        return currentProcessName
    }

    /**
     * 通过反射ActivityThread获取进程名，避免了ipc
     */
    private fun getCurrentProcessNameByActivityThread(): String? {
        var processName: String? = null
        try {
            val declaredMethod = Class.forName(
                "android.app.ActivityThread", false,
                Application::class.java.classLoader
            )
                .getDeclaredMethod("currentProcessName", *arrayOfNulls<Class<*>?>(0))
            declaredMethod.isAccessible = true
            val invoke = declaredMethod.invoke(null, *arrayOfNulls(0))
            if (invoke is String) {
                processName = invoke
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return processName
    }

    /**
     * 通过Application新的API获取进程名，无需反射，无需IPC，效率最高。
     */
    private fun getCurrentProcessNameByApplication(context: Context): String? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Application.getProcessName()
        } else getCurrentProcessNameByActivityManager(context)
    }

    /**
     * 通过ActivityManager 获取进程名，需要IPC通信
     */
    private fun getCurrentProcessNameByActivityManager(@NonNull context: Context?): String? {
        if (context == null) {
            return null
        }
        val pid: Int = Process.myPid()
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningAppList = am.runningAppProcesses
        if (runningAppList != null) {
            for (processInfo in runningAppList) {
                if (processInfo.pid == pid) {
                    return processInfo.processName
                }
            }
        }
        return null
    }

    /**
     * 获取当前栈顶Activity
     */
    fun getTaskTopActivity(context: Context):String?{
        val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        manager.appTasks?.run {
            return this[0].taskInfo.topActivity?.className
        }
        return ""
    }
}