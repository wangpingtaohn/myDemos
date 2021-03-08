package com.wpt.mydemos.coordinator

import android.os.Bundle
import android.util.Log
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.wpt.mydemos.R
import com.wpt.mydemos.recycler.rcv2.ItemAdapter
import com.wpt.mydemos.widget.BaseActivity
import kotlinx.android.synthetic.main.activity_coordinator2.*





class Coordinator2Activity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coordinator2)

        initView()
    }


    private fun initView(){

        /*val list = mutableListOf<com.wpt.mydemos.recycler.rcv2.ItemBean2>()
        for (i in 1..20){
            val item = com.wpt.mydemos.recycler.rcv2.ItemBean2()
            item.index = i.toString()
            list.add(item)
        }*/

        val adapter = ItemAdapter(this)
        val layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        var isExpand = true
        btn_scroll.setOnClickListener {
            if(isExpand){
                layoutManager.scrollToPositionWithOffset(1,50)
            } else {
                layoutManager.scrollToPositionWithOffset(0,0)
            }
            appbar.setExpanded(!isExpand)
            isExpand = !isExpand
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastPos = layoutManager.findLastVisibleItemPosition()
                val visibleCount = layoutManager.childCount
                val itemCount = layoutManager.itemCount
                //的值表示是否能向上滚动，false表示已经滚动到底部
                val canScroll_1 = recyclerView.canScrollHorizontally(1)
                //的值表示是否能向下滚动，false表示已经滚动到顶部
                val canScroll_1_ = recyclerView.canScrollHorizontally(-1)
                Log.d("===wpt===", "onScrolled_lastPos=$lastPos" +
                        " ,visibleCount=$visibleCount ,itemCount=$itemCount" +
                        ",canScroll_1=$canScroll_1 ,canScroll_1_=$canScroll_1_")
            }
        })

        /*Handler().postDelayed({
            val lastPos = layoutManager.findLastCompletelyVisibleItemPosition()
            Log.d("===wpt===", "lastPos=$lastPos")
            val lp = iv_header.layoutParams as AppBarLayout.LayoutParams
            var flag = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
            if (lastPos == list.size - 1) {
                iv_header.minimumHeight = iv_header.height
                flag = flag or AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
            }
            lp.scrollFlags = flag
        }, 200)*/


    }
}
