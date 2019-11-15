package com.wpt.mydemos.recycler

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.activity_recycler_view.*

/**
 * 处理RecyclerView的数据源发生变化后，不立即执行notifyDataSetChanged的现象
 */
class RecyclerViewActivity : Activity(),MyAdapter.LongClickListener {

    private var list = mutableListOf<String>()

    private var imgList = mutableListOf<ItemBean>()

    private var adapter : MyAdapter ? = null

    private var adapter2 : MyAdapter2 ? = null


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
        for (i in 0..11) {
            var bean = ItemBean()
            bean.resId = R.drawable.bg_btn_yellow_corner6
            imgList.add(bean)
        }
//        adapter2 =  MyAdapter2(this,list)
        adapter = MyAdapter(this,imgList)
        adapter?.setLongClickListener(this)


        recyclerView.layoutManager = GridLayoutManager(this,3)

        if (adapter != null){
            recyclerView.adapter = adapter
        } else {
            recyclerView.adapter = adapter2
        }

        btn1.setOnClickListener {
            if (adapter2 != null){
//                list.clear()
                list.removeAt(0)
            }
        }
        notify.setOnClickListener {
            if (adapter != null){
                adapter!!.notifyItemChanged(0)
            }
        }

        btn2.setOnClickListener {
            if (adapter2 != null){
                list.add("btn2")
                adapter2?.notifyDataSetChanged()
            }
        }

        delete.setOnClickListener {
            adapter?.setIsEdit(false)
            var temp = mutableListOf<ItemBean>()
            for (item in imgList){
                if (item.isDelete){
                    temp.add(item)
                }
            }
            if (temp.size > 0){
                imgList.removeAll(temp)
            }
            adapter?.notifyDataSetChanged()
        }
        select_all.setOnClickListener {
            for (item in imgList){
                item.isDelete = true
            }
            adapter?.notifyDataSetChanged()
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
