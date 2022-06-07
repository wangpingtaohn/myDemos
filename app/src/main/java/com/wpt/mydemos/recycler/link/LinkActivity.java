package com.wpt.mydemos.recycler.link;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wpt.mydemos.R;
import com.wpt.mydemos.bean.LinkBean;
import com.wpt.mydemos.widget.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: wpt
 * Time: 2022/6/7
 *
 * @Desc：
 */
public class LinkActivity extends BaseActivity {

    private EditText etYs;
    private EditText etArray;
    private LinkAdapter adapter;
    private String[] mArray;
    private List<LinkBean> mList = new ArrayList<>();
    private int index = 0;
    private  RecyclerView rvLink;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_rv);
        initView();
        initListener();
    }

    private void initView(){
        etYs = findViewById(R.id.etYushu);
        etArray = findViewById(R.id.etArray);
    }

    private void initRv(){
        rvLink = findViewById(R.id.rvLink);
        adapter = new LinkAdapter(this,mList);
        rvLink.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rvLink.setAdapter(adapter);

    }

    private void initListener(){
        Button button = findViewById(R.id.btnStart);
        Button btnNext = findViewById(R.id.btnNext);
        button.setOnClickListener(v -> {
            initData();
            initRv();
        });

        btnNext.setOnClickListener(v -> nextData());

    }

    private void initData(){
        String remainderStr = etYs.getText().toString();
        int remainder = Integer.parseInt(remainderStr);
        int count = 0;
        while (count < remainder){
            LinkBean bean = new LinkBean();
            bean.setTitle(String.valueOf(count));
            count++;
            mList.add(bean);
        }
        String arrayInput = etArray.getText().toString();
        mArray = arrayInput.split(",");
    }

    private void nextData(){
        if (index >= mArray.length){
            return;
        }
        String remainderStr = etYs.getText().toString();
        int remainder = Integer.parseInt(remainderStr);
        String str = mArray[index];
        int value = Integer.parseInt(str);
        int result = value % remainder;
        LinkBean subBean = new LinkBean();
        subBean.setTitle(str);
        mList.get(result).getList().add(subBean);
        index++;
        rvLink.scrollToPosition(result);
        adapter.notifyItemChanged(result);
    }
}
