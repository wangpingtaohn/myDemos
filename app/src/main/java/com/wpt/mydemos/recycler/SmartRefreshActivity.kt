package com.wpt.mydemos.recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wpt.mydemos.R
import com.wpt.mydemos.recycler.xrcv.MyQuickAdapter
import com.wpt.mydemos.widget.PQRecyclerView

class SmartRefreshActivity : AppCompatActivity() {

    private var rootView:PQRecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootView = PQRecyclerView(this)
//        setContentView(R.layout.view_smart_refresh_recyclerview)
        setContentView(rootView)
        initView()
    }


    private fun initView(){
        val list = mutableListOf<String>()
        for (index in 0..50){
            list.add("第${index}行")
        }

        val myAdapter = MyQuickAdapter(R.layout.item_quick_rv,list)
        with(rootView!!.recyclerView){
            layoutManager = LinearLayoutManager(this@SmartRefreshActivity,
                LinearLayoutManager.VERTICAL,false)
            adapter = myAdapter
        }

        /*rootView!!.smartRefreshLayout.setOnRefreshListener {
            Log.d("===wpt===","下拉更新")
            Toast.makeText(this,"下拉更新",Toast.LENGTH_SHORT).show()
        }

        rootView!!.smartRefreshLayout.setOnLoadMoreListener {
            Log.d("===wpt===","加载更多")
           Toast.makeText(this,"加载更多",Toast.LENGTH_SHORT).show()
        }*/

    }
}