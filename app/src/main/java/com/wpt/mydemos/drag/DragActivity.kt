package com.wpt.mydemos.drag

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.widget.Toast
import com.fan.baselib.loadmore.AutoLoadMoreAdapter
import com.wpt.mydemos.R
import com.wpt.mydemos.widget.BaseActivity
import kotlinx.android.synthetic.main.activity_drag.*
import java.util.*


class DragActivity : BaseActivity(),DragAdapter.ItemDragListener {

    private var adapter: DragAdapter? = null

    private var mItemTouchHelper:ItemTouchHelper? = null

    private var autoLoadMoreAdapter:AutoLoadMoreAdapter ? = null

    private var list = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag)

        initView()
        setDrag()
    }

    private fun initView(){
        var count = 0
        while (count < 20){
            list.add(R.drawable.ic_mygold_money_banner)
            count++
        }

        refreshLayout.setEnableRefresh(true)
//        nestedScrollView.fullScroll(View.FOCUS_UP)
        adapter = DragAdapter(this,list)
        autoLoadMoreAdapter = AutoLoadMoreAdapter(this,adapter)
        adapter!!.mItemDragListener = this
        val lm = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, true)
//        val lm = GridLayoutManager(this,3)
//        lm.stackFromEnd = true
        drag_recyclerview.layoutManager = lm
        drag_recyclerview.adapter = autoLoadMoreAdapter

        autoLoadMoreAdapter!!.setOnLoadListener(object : AutoLoadMoreAdapter.OnLoadListener {
            override fun onRetry() {

            }

            override fun onLoadMore() {
            }
        })
        autoLoadMoreAdapter!!.disable()
    }

    private fun setDrag() {
        mItemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            /**
             *
             * @param recyclerView 关联的recyclerView
             * @param viewHolder  操作的viewHolder对象
             * @return 返回运动方向标志的组合，通过makeMovementFlags(dragFlags, swipeFlags)进行组合
             */
            override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
                //拖拽的方法标记
                val dragFlags =
                    ItemTouchHelper.UP or ItemTouchHelper.DOWN
                //滑动方向标记
                val swipeFlags = 0
                //通过makeMovementFlags方法将将方向标记进行组合，并将复合的值返回
                return ItemTouchHelper.Callback.makeMovementFlags(dragFlags, swipeFlags)
            }

            /**
             * @param recyclerView 关联的recyclerView
             * @param viewHolder  要移动的viewHolder对象
             * @param target   移动到的目标ViewHolder对象
             * @return 返回true 才会执行ItemTouchHelper.Callback的onMoved方法，
             */
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition
                Collections.swap(list, fromPosition, toPosition)
                autoLoadMoreAdapter!!.notifyItemMoved(fromPosition, toPosition)
//                recyclerView.scrollToPosition(toPosition + 2)
                refreshLayout.setEnableRefresh(true)
                return true
            }

            /**
             * 左滑删除
             * @param viewHolder 滑动的viewHolder对象
             * @param direction  移动的方向标识
             */
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                //recyclerViewAdapter.delData(viewHolder.adapterPosition)
            }

            override fun isLongPressDragEnabled(): Boolean {
                return false
            }
        })
        //关联RecyclerView
        mItemTouchHelper!!.attachToRecyclerView(drag_recyclerview)
    }

    override fun onStartDrags(viewHolder: RecyclerView.ViewHolder) {
        refreshLayout.setEnableRefresh(false)
        mItemTouchHelper?.startDrag(viewHolder)
    }

    override fun onDragsUp() {
        Toast.makeText(this,"拖拽完成",Toast.LENGTH_SHORT).show()
    }


}
