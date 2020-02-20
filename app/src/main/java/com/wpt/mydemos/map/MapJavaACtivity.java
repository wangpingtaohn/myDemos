package com.wpt.mydemos.map;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.wpt.mydemos.R;

import java.io.Serializable;
import java.util.*;

/**
 * author : wpt
 * date   : 2019-09-19 15:15
 * desc   :
 */
public class MapJavaACtivity extends Activity {

    HashMap<Integer, String> hashMap = new HashMap();
    LinkedHashMap<Integer, String> linkMap = new LinkedHashMap();

    Set<String> set = new HashSet<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        initView();
        initData();
    }

    private void initData(){
        int count = 0;
        while (count < 10){
            hashMap.put(count,count+ "");
            linkMap.put(count,count+ "");
            set.add(count+ "");
            count++;
        }
        Log.d("MapJavaACtivity_set=", set.toString());
    }

    TestBean test;
    MyTest myTest;
    String hashMapText = "";
    private void initView(){
        final TextView textView = findViewById(R.id.textview);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hashMapText = "";
                for (Map.Entry<Integer, String> entry : hashMap.entrySet()){
                    int key = entry.getKey();
                    String value = entry.getValue();
                    hashMapText += key + ":" + value + "\n";
                }
                hashMapText += "====linkMap====\n";
                for (Map.Entry<Integer, String> entry : linkMap.entrySet()){
                    int key = entry.getKey();
                    String value = entry.getValue();
                    hashMapText += key + ":" + value + "\n";
                }
                textView.setText(hashMapText);

                try {
                    expctionTest(null);
                } catch (Exception e){
                    Log.e("wpt",e.getMessage());
                }

                test = new TestBean();
                test.name = "Jim";

                myTest = new MyTest(test);
                myTest.showTest();


                test.name = "Tom";

                String str = "     ";
                str.trim();
                Log.d("===wpt===","str=" + str.trim());
                Log.d("===wpt===","str is null=" + TextUtils.isEmpty(str.trim()));
            }
        });
        final List<String> list = new ArrayList<>();

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> list2 = new ArrayList<>();

                for (Map.Entry<Integer, String> entry : hashMap.entrySet()){
                    int key = entry.getKey();
                    String value = entry.getValue();
                    list.add(key,value);
                }
                list2.addAll(list);
                List<String> list3 = new ArrayList<>(list.subList(1, list.size()));
                list.set(5,"555");
                hashMapText += list.toString();
                hashMapText += "\n====list2====\n" + list2.toString();
                hashMapText += "\n====list3====\n" + list3.toString();
                textView.setText(hashMapText);

                //myTest.showTest();

            }
        });

        List<String> list3 = new ArrayList<>();
        List<String> list4 = new ArrayList<>();
        list3.add("1");
        list3.add("2");
        list4.add("3");
        list4.add("4");
        textView.setText(list3.toString());
        findViewById(R.id.btn3).setOnClickListener(v -> {
//            list3.add(1,"3");
            list3.addAll(list4);
            textView.setText(list3.toString());

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(2000);
//                    Toast.makeText(getApplicationContext(),"****",Toast.LENGTH_SHORT).show();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }

    private void expctionTest(String str){
        if (str == null){
            throw new NullPointerException("str is null");
        }
    }

    class TestBean implements Serializable {
        String name;
    }

    class MyTest{
        TestBean test;
        public MyTest(TestBean bean){
            test = bean;
            Toast.makeText(MapJavaACtivity.this,test.name,Toast.LENGTH_SHORT).show();
        }

        public void showTest(){
            Toast.makeText(MapJavaACtivity.this,test.name,Toast.LENGTH_SHORT).show();
        }
    }

}
