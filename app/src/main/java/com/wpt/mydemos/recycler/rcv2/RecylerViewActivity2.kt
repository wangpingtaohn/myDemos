package com.wpt.mydemos.recycler.rcv2

import android.os.Bundle
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
        val list = mutableListOf<com.wpt.mydemos.recycler.rcv2.ItemBean2>()
        for (i in 1..50){
            val item = com.wpt.mydemos.recycler.rcv2.ItemBean2();
            item.index = i.toString()
            list.add(item)
        }
        adapter = RvAdapter(this, list)
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
