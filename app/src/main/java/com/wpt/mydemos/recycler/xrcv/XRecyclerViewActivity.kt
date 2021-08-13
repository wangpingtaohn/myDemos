package com.wpt.mydemos.recycler.xrcv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.activity_xrecycler_view.*

class XRecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xrecycler_view)

        initView()
    }


    private fun initView(){
        val list = mutableListOf<String>()
        for (index in 0..50){
            list.add("第${index}行")
        }

        val myAdapter = MyQuickAdapter(R.layout.item_quick_rv,list)
        with(rlv){
            layoutManager = LinearLayoutManager(this@XRecyclerViewActivity,LinearLayoutManager.VERTICAL,false)
            adapter = myAdapter
        }

        myAdapter.loadMoreModule.isEnableLoadMore = false
        myAdapter.loadMoreModule.isEnableLoadMoreIfNotFullPage = false //不满一页时，不加载更多
        myAdapter.loadMoreModule.setOnLoadMoreListener { Toast.makeText(this,"加載更多",Toast.LENGTH_SHORT).show() }

        myAdapter.upFetchModule.isUpFetchEnable = true
        myAdapter.upFetchModule.setOnUpFetchListener { Toast.makeText(this,"upFetch",Toast.LENGTH_SHORT).show() }
    }
}