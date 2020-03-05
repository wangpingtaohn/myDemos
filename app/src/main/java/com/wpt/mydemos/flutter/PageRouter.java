package com.wpt.mydemos.flutter;

/**
 * author : wpt
 * date   : 2020-02-17 16:31
 * desc   :
 */
import android.app.Activity;
import android.content.Context;
import android.content.Entity;
import android.content.Intent;
import android.util.Log;
import com.idlefish.flutterboost.containers.BoostFlutterActivity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PageRouter {

//    public final static Map<String, String> pageName = new HashMap<String, String>() {{
//
//        put("first", "first");
//        put("second", "second");
//        put("tab", "tab");
//        put("testPage", "testPage");
//        put("sample://flutterPage", "flutterPage");
//    }};

    public static final String NATIVE_PAGE_URL = "sample://nativePage";
    public static final String NATIVE_PAGE_URL2 = "func://nativePage";
    public static final String FLUTTER_PAGE_URL = "first?sample://flutterPage";
    public static final String FLUTTER_TEST_PAGE_URL = "/flutter?testPage";
    public static final String FLUTTER_FRAGMENT_PAGE_URL = "sample://flutterFragmentPage";

    public static final String FLUTTER_PREFIX = "/flutter?";

    public static final String FLUTTER_MY_WALLET = FLUTTER_PREFIX + "myWalletPage";

    public static boolean openPageByUrl(Context context, String url, Map params) {
        return openPageByUrl(context, url, params, 0);
    }

    public static boolean openPageByUrl(Context context, String url, Map params, int requestCode) {

        /*flutter调用
        FlutterBoost.singleton
                .open("func://nativePage", urlParams: {
            "query": {"aaa": "bbb"}
        })*/
        /*打印出来的值为
            key=__container_uniqueId_key__,value=1582186131998--1296865764
            key=query,value={aaa=bbb}
        */
        if (params == null){
            params = new HashMap();
        }
        setParams(context,params);

        for (Object key : params.keySet()) {
//                String value = (String) params.get(key);
            Log.i("openPageByUrl", "key=" + key + ",value=" + params.get(key));
        }


        //flutter?myWalletPage&aaa=bbb
        String path = url.split("\\&")[0];

        Log.i("openPageByUrl",url);

        try {
//            if (pageName.containsKey(path)) {
            if (path.startsWith(FLUTTER_PREFIX)) {
                Intent intent = FlutterBaseActivity.withNewEngine().url(path).params(params).build(context);
                intent.setClass(context,FlutterBaseActivity.class);
                if(context instanceof Activity){
                    Activity activity=(Activity)context;
                    activity.startActivityForResult(intent,requestCode);
                }else{
                    context.startActivity(intent);
                }
                return true;
            } else if (url.startsWith(FLUTTER_FRAGMENT_PAGE_URL)) {
                context.startActivity(new Intent(context, FlutterFragmentPageActivity.class));
                return true;
            } else if (url.startsWith(NATIVE_PAGE_URL) || url.startsWith(NATIVE_PAGE_URL2)) {
                context.startActivity(new Intent(context, NativePageActivity.class));
                return true;
            }

            return false;

        } catch (Throwable t) {
            return false;
        }
    }

    private static void setParams(Context context, Map params) {
        if (params == null) {
            params = new HashMap();
        }
        params.put("token","6b484ae609284374bb91ad5c1b38c480");
        params.put("env","debug");
        params.put("version","2.13.4");
    }
}
