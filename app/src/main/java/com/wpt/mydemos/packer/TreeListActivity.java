package com.wpt.mydemos.packer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.multilevel.treelist.Node;
import com.wpt.mydemos.R;

import java.util.ArrayList;
import java.util.List;

public class TreeListActivity extends AppCompatActivity {

    private RecyclerView rvTree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_list);

        rvTree = findViewById(R.id.rvTree);

        findViewById(R.id.btnShowTree).setOnClickListener(v -> showTreeList());
    }

    private void showTreeList(){
        List<Node> list = new ArrayList<>();
        for (int i = 0;i < 10;i++){
            Node node = new Node(i,-1,"第一层第" + i + "个");
//            node.isExpand = true
//            val subList = mutableListOf<Node>()
//            list.add(node);
            /*for (int j = 10;i < 20 ;j++){
                Node subNode = new Node(j,i,"第二层${j}个");
//                subList.add(subNode);
                list.add(subNode);
                for (int k = 20;k < 30 ;k++){
                    Node sub2Node = new Node(k,j,"第三层${k}个");
//                subList.add(subNode);
                    list.add(sub2Node);
                }
            }*/
//            node.children = subList
            list.add(node);
            list.add(new Node(i * 10,i,"第二层第" + i + "个"));
            list.add(new Node(i * 10 + 1,i,"第二层第" + i + "个"));
            list.add(new Node(i * 10 + 1,i,"第二层第" + i + "个"));
        }
        List<Node> mDatas = new ArrayList<>();
        mDatas.add(new Node("1", "-1", "文件管理系统"));

        mDatas.add(new Node(2+"", 1+"", "游戏"));
        mDatas.add(new Node(3+"", 1+"", "文档"));
        mDatas.add(new Node(4+"", 1+"", "程序"));
        mDatas.add(new Node(5+"", 2+"", "war3"));
        mDatas.add(new Node(6+"", 2+"", "刀塔传奇"));

        mDatas.add(new Node(7 + "", 4 + "", "面向对象"));
        mDatas.add(new Node(8+"", 4+"", "非面向对象"));

        mDatas.add(new Node(9+"", 7+"", "C++"));
        mDatas.add(new Node(10+"", 7+"", "JAVA"));
        mDatas.add(new Node(11+"", 7+"", "Javascript"));
        mDatas.add(new Node(12+"", 8+"", "C"));
        mDatas.add(new Node(13+"", 12+"", "C"));
        mDatas.add(new Node(14+"", 13+"", "C"));
        mDatas.add(new Node(15+"", 14+"", "C"));
        mDatas.add(new Node(16+"", 15+"", "C"));
        MyTreeAdapter treeAdapter = new MyTreeAdapter(rvTree,this, list,1,R.drawable.ic_love01,R.drawable.ic_love01);
        rvTree.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rvTree.setAdapter(treeAdapter);
    }
}