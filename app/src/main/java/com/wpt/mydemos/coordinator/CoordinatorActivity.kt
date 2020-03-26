package com.wpt.mydemos.coordinator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.wpt.mydemos.R
import com.wpt.mydemos.recycler.rcv2.RvAdapter
import kotlinx.android.synthetic.main.activity_coordinator.*

class CoordinatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coordinator)

        initView()
    }


    private fun initView(){
        val list = mutableListOf<com.wpt.mydemos.recycler.rcv2.ItemBean2>()
        for (i in 1..50){
            val item = com.wpt.mydemos.recycler.rcv2.ItemBean2();
            item.index = i.toString()
            list.add(item)
        }
        var adapter = RvAdapter(this, list)
        var layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        coordinator_recyclerview.layoutManager = layoutManager
        coordinator_recyclerview.adapter = adapter
    }
}
