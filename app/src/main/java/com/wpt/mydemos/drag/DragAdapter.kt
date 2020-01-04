package com.wpt.mydemos.drag

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.item_drag.view.*





/**
 *    author : wpt
 *    date   : 2019-12-18 09:07
 *    desc   :
 */
class DragAdapter(private var mContext: Context,private var data:List<Int>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_drag, parent, false)
        return MyVH(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MyVH){
            holder.itemView.drag_item_img.setImageResource(data[position])
            holder.itemView.iv_sort.setOnTouchListener { v, event ->
                mItemDragListener?.onStartDrags(holder)
                false
            }
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val lm = recyclerView.layoutManager
        if (lm is GridLayoutManager){
            lm.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
//                    return if (position != 11){
//                        1
//                    } else {
//                        lm.spanCount
//                    }
                    return 3
                }
            }
        }
    }

    var mItemDragListener:ItemDragListener ? = null
    interface ItemDragListener {
        fun onStartDrags(viewHolder: RecyclerView.ViewHolder)
    }

    class MyVH(view:View):RecyclerView.ViewHolder(view)
}