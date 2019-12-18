package com.wpt.mydemos.drag

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.wpt.mydemos.R
import com.wpt.mydemos.widget.BaseActivity
import kotlinx.android.synthetic.main.activity_drag.*
import java.util.*


class DragActivity : BaseActivity() {

    private var adapter: DragAdapter? = null

    private var mItemTouchHelper:ItemTouchHelper? = null

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
        adapter = DragAdapter(this,list)
        drag_recyclerview.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        drag_recyclerview.adapter = adapter
    }

    private fun setDrag() {
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
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
                Collections.swap(list, fromPosition, toPosition);
                adapter!!.notifyItemMoved(fromPosition, toPosition);
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
        })
        //关联RecyclerView
        itemTouchHelper.attachToRecyclerView(drag_recyclerview)
    }
}
