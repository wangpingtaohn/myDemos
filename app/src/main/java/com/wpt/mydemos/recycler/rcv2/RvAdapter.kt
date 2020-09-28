package com.wpt.mydemos.recycler.rcv2

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.item_recylerview_2.view.*

/**
 *    author : wpt
 *    date   : 2019-11-15 09:22
 *    desc   :
 */
class RvAdapter(private var context: Context,private var mData: MutableList<com.wpt.mydemos.recycler.rcv2.ItemBean2>): RecyclerView.Adapter<ViewHolder>() {

    private var mMyWatcher: MyWatcher? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ItemVH(
            LayoutInflater.from(context).inflate(
                R.layout.item_recylerview_2,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is ItemVH){
            holder.itemView.run{
//                if (edittext.tag is MyWatcher){
//                    edittext.removeTextChangedListener(mMyWatcher)
//                }
                if (mMyWatcher != null){
                    edittext.removeTextChangedListener(mMyWatcher)
                }
                mMyWatcher = MyWatcher(holder)
                edittext.setText(mData[position].index)
                edittext.tag = mMyWatcher
                edittext.addTextChangedListener(mMyWatcher)

                delete.setOnClickListener {
                    mData.removeAt(position)
                    notifyDataSetChanged()
                }
            }
        }

    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        super.onViewAttachedToWindow(holder)
        Log.d("===wpt===","onViewAttachedToWindow_pos=" + holder.adapterPosition)
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        Log.d("===wpt===","onViewDetachedFromWindow_pos=" + holder.adapterPosition)
    }


    class ItemVH(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class MyWatcher(private var viewHolder: ItemVH): TextWatcher{

        override fun afterTextChanged(s: Editable?) {
            mData[viewHolder.adapterPosition].index = s.toString()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

    }

}