package com.wpt.mydemos.recycler

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wpt.mydemos.R

/**
 *    author : wpt
 *    date   : 2019-10-08 14:34
 *    desc   :
 */
class MyAdapter(private var context: Context): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    var longlistener: LongClickListener ? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.recycler_view_item, parent, false)
        return object : MyViewHolder(view) {}
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.itemView.setOnLongClickListener {
            longlistener?.onLongClick()
            false
        }

    }

    open class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    interface LongClickListener{
        fun onLongClick()
    }

    public fun setLongClickListener(l:LongClickListener){
        longlistener = l;
    }
}