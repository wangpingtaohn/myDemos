package com.wpt.mydemos.drag

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.item_drag.view.*





/**
 *    author : wpt
 *    date   : 2019-12-18 09:07
 *    desc   :
 */
class DragAdapter(private var mContext: Context,private var data:List<DragActivity.DragItem>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0){
            val view = LayoutInflater.from(mContext).inflate(R.layout.item_drag_header, parent, false)
            return HeaderVH(view)
        }
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_drag, parent, false)
        return MyVH(view)
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].type
    }

    override fun getItemCount(): Int {
        return data.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MyVH){
            holder.itemView.drag_item_img.setImageResource(data[position - 1].resId)
            holder.itemView.iv_sort.setOnTouchListener { v, event ->
                Log.d("===wpt===","drags_event.action=" + event.action)
                if (event.action == MotionEvent.ACTION_MOVE || event.action == MotionEvent.ACTION_DOWN){
                    mItemDragListener?.onStartDrags(holder)
                } else if (event.action == MotionEvent.ACTION_UP){
                    mItemDragListener?.onDragsUp()
                }
                false
            }
        }
    }

//    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
//        super.onAttachedToRecyclerView(recyclerView)
//        val lm = recyclerView.layoutManager
//        if (lm is GridLayoutManager){
//            lm.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
//                override fun getSpanSize(position: Int): Int {
////                    return if (position != 11){
////                        1
////                    } else {
////                        lm.spanCount
////                    }
//                    return 3
//                }
//            }
//        }
//    }

    var mItemDragListener:ItemDragListener ? = null
    interface ItemDragListener {
        fun onStartDrags(viewHolder: RecyclerView.ViewHolder)
        fun onDragsUp()
    }

    class MyVH(view:View):RecyclerView.ViewHolder(view)

    class HeaderVH(view:View):RecyclerView.ViewHolder(view)
}