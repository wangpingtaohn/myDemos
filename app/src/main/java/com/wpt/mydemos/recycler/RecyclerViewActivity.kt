package com.wpt.mydemos.recycler

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : Activity(),MyAdapter2.LongClickListener {

    private var list = mutableListOf<String>()

    override fun onLongClick() {
//        root_view.setBackgroundColor(Color.parseColor("#1A000000"))
        root_view.visibility = View.VISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        initView()
    }

    private fun initView(){
        for (i in 0..99) {
            list.add(i.toString())
        }

        var adapter =  MyAdapter2(this,list)
        adapter.setLongClickListener(this)

        recyclerView.layoutManager = MyLinearLayoutManager(this)

        recyclerView.adapter = adapter

        btn1.setOnClickListener {
            list.clear()
        }

        btn2.setOnClickListener {
            list.add("btn2")
            adapter.notifyDataSetChanged()
        }
    }

    class MyLinearLayoutManager(context: Context?) : LinearLayoutManager(context) {

        override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
            try {
                super.onLayoutChildren(recycler, state)
            } catch (e:IndexOutOfBoundsException){
                Log.e("",e.message)
            }
        }

        override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
            try {
                return super.scrollVerticallyBy(dy, recycler, state)
            } catch (e:IndexOutOfBoundsException){
                Log.e("",e.message)
                return 0
            }
        }

    }
}
