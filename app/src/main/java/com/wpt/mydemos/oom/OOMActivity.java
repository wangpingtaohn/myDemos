package com.wpt.mydemos.oom;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.wpt.mydemos.R;
import com.wpt.mydemos.widget.BaseActivity;

public class OOMActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oom);

        findViewById(R.id.oom_btn).setOnClickListener(v ->  {
            test();
        });
    }

    private void test(){
        try {
            throw new OutOfMemoryError();
        } catch (OutOfMemoryError error){
            Toast.makeText(this,error.toString(),Toast.LENGTH_SHORT).show();
        }
    }
}
