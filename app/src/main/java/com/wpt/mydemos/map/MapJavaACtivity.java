package com.wpt.mydemos.map;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.wpt.mydemos.R;

import java.util.*;

/**
 * author : wpt
 * date   : 2019-09-19 15:15
 * desc   :
 */
public class MapJavaACtivity extends AppCompatActivity {

    HashMap<Integer, String> hashMap = new HashMap();
    LinkedHashMap<Integer, String> linkMap = new LinkedHashMap();

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
            count++;
        }
    }

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
                list.set(5,"555");
                hashMapText += list.toString();
                hashMapText += "\n====list2====\n" + list2.toString();
                textView.setText(hashMapText);

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    Toast.makeText(getApplicationContext(),"****",Toast.LENGTH_SHORT).show();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
