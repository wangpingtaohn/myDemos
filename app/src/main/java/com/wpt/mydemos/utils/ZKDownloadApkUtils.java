package com.wpt.mydemos.utils;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;


import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.wpt.mydemos.R;

import java.io.File;

/**
 * author : wpt
 * date   : 2021/8/916:05
 * desc   :
 */
public class ZKDownloadApkUtils {


    //下载apk完成后的广播
    private CompleteReceiver mCompleteReceiver;

    //下载管理
    private DownloadManager mDownloadManager;

    //下载的id
    private long mDownloadId;

//    private SeekBar mSeekBar;
    /**
     * 下载
     *  apk 路径
     */

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DownloadManager.STATUS_PENDING:
//                    System.out.println("准备下载----");
                    break;
                case DownloadManager.STATUS_RUNNING: //下载中
//                    if (mSeekBar != null) mSeekBar.setProgress((int) msg.obj);
                    break;

                case DownloadManager.STATUS_SUCCESSFUL://下载成功
                case DownloadManager.STATUS_FAILED://下载失败
//                    if (dialog != null) dialog.dismiss();
                    break;
            }
        }
    };


    public void downloadApk(final Context context, final String apkUrl) {

        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 6666);

        mCompleteReceiver = new CompleteReceiver();
        /** register download success broadcast **/
        context.registerReceiver(mCompleteReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        Toast toast = Toast.makeText(context, "已转为后台下载...", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    mDownloadManager = (DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);
//                    String dir = isFolderExist();
                    String dir = context.getFilesDir().getAbsolutePath();
                    String fileName = null;
                    if (!TextUtils.isEmpty(apkUrl) && apkUrl.length() > 0) {
                        fileName = apkUrl.substring(apkUrl.lastIndexOf("/"));
                    }

                    mCompleteReceiver.savePath = dir + fileName;
                    File f = new File(dir + fileName);
                    if (f.exists()) f.delete();
                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(apkUrl));
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);

                    request.allowScanningByMediaScanner();//表示允许MediaScanner扫描到这个文件，默认不允许。
                    request.setTitle(context.getString(R.string.app_name));//设置下载中通知栏提示的标题
                    request.setDescription("程序更新正在下载中:" + dir);//设置下载中通知栏提示的介绍
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    //获得下载id，这是下载任务生成时的唯一id，可通过此id获得下载信息
                    mDownloadId = mDownloadManager.enqueue(request);
                    //查询下载信息方法
                    queryDownloadProgress(mDownloadId, mDownloadManager);
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void queryDownloadProgress(long requestId, DownloadManager downloadManager) {

        DownloadManager.Query query = new DownloadManager.Query();
        //根据任务编号id查询下载任务信息
        query.setFilterById(requestId);
        try {
            boolean isGoging = true;
            while (isGoging) {
                Cursor cursor = downloadManager.query(query);
                if (cursor != null && cursor.moveToFirst()) {

                    // 获得下载状态
                    int state = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
                    switch (state) {
                        case DownloadManager.STATUS_SUCCESSFUL://下载成功
                            isGoging = false;
                            handler.obtainMessage(downloadManager.STATUS_SUCCESSFUL).sendToTarget();//发送到主线程，更新ui

                            break;
                        case DownloadManager.STATUS_FAILED://下载失败
                            isGoging = false;
                            handler.obtainMessage(downloadManager.STATUS_FAILED).sendToTarget();//发送到主线程，更新ui
                            break;
                        case DownloadManager.STATUS_RUNNING://下载中
                            /**
                             * 计算下载下载率；
                             */
                            int totalSize = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                            int currentSize = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                            int progress = (int) (((float) currentSize) / ((float) totalSize) * 100);
                            handler.obtainMessage(downloadManager.STATUS_RUNNING, progress).sendToTarget();//发送到主线程，更新ui
                            break;
                        case DownloadManager.STATUS_PAUSED://下载停止
                            handler.obtainMessage(DownloadManager.STATUS_PAUSED).sendToTarget();
                            break;
                        case DownloadManager.STATUS_PENDING://准备下载
                            handler.obtainMessage(DownloadManager.STATUS_PENDING).sendToTarget();
                            break;
                    }
                }
                if (cursor != null) {
                    cursor.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String isFolderExist() {
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        boolean rs = folder.exists() && folder.isDirectory() || folder.mkdirs();
        return folder.getAbsolutePath();
    }

    //广播接受者，监听下载完成后安装
    class CompleteReceiver extends BroadcastReceiver {
        public String savePath = "";

        @Override
        public void onReceive(Context context, Intent intent) {
            if (mDownloadId == intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0)) {
                DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                DownloadManager.Query query = new DownloadManager.Query();
                query.setFilterById(mDownloadId);

                Cursor cursor = manager.query(query);
                if (!cursor.moveToFirst()) {
                    cursor.close();
                    return;
                }
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                    savePath = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                }else{
                    savePath = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME));
                }
                Uri uri = manager.getUriForDownloadedFile(mDownloadId);
                downComplete(context,savePath,uri);
            }

        }

        private void downComplete(Context context,String filePath,Uri uri) {
            File _file = new File(filePath);
            Log.d("download","filePath=" + filePath);
            Intent intent = new Intent();
            //Android7.0 或者7.0以上setDestinationInExternalPublicDir
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

                Uri contentUri = FileProvider.getUriForFile(context, "com.wpt.mydemos", _file);
//                Uri contentUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID.concat(".provider"), _file);
                Log.d("download","uriHost=" + contentUri.getHost());
//                Uri contentUri = mDownloadManager.getUriForDownloadedFile(mDownloadId);
                intent.setDataAndType(uri, "application/vnd.android.package-archive");

                // 兼容8.0
                if (Build.VERSION.SDK_INT >= 26) {
                    try {
                        boolean hasInstallPermission = context.getPackageManager().canRequestPackageInstalls();
                        if (!hasInstallPermission) {
                            // 请求安装未知应用来源的权限
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.REQUEST_INSTALL_PACKAGES}, 6666);
                        }
                    } catch (Exception e){
                        Log.d("download","e=" + e.getMessage());
                    }

                }
            } else {
                //Android7.0以下
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 以新压入栈
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setDataAndType(Uri.fromFile(_file), "application/vnd.android.package-archive");
            }
            // 解决安装apk时弹出“选择打开方式”让用户选择而不是直接跳转到APP安装界面
            try {
                intent.setAction(Intent.ACTION_VIEW);
                context.startActivity(intent);
            } catch (Exception e){
                Log.d("download","调起安装=" + e.getMessage());
            }

        }
    }
}
