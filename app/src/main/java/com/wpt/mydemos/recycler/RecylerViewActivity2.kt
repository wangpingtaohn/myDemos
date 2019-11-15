package com.wpt.mydemos.recycler

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import com.wpt.mydemos.R

import kotlinx.android.synthetic.main.activity_recyler_view2.*

class RecylerViewActivity2 : AppCompatActivity() {

    private var adapter: RvAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyler_view2)

        initView()

    }

    private fun initView(){
        val list = mutableListOf<ItemBean2>()
        for (i in 1..50){
            val item = ItemBean2();
            item.index = i.toString()
            list.add(item)
        }
        adapter = RvAdapter(this,list)
        var layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        btn.setOnClickListener {
            if (!TextUtils.isEmpty(textview.text)){
//                recyclerView.scrollToPosition(textview.text.toString().toInt())
                recyclerView.scrollBy(0,textview.text.toString().toInt())

            }
        }
    }

}
