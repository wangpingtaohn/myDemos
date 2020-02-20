package com.wpt.mydemos.recycler.rcv1

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.recycler_view_item2.view.*

/**
 *    author : wpt
 *    date   : 2019-10-08 14:34
 *    desc   :
 */
class MyAdapter2(private var context: Context,private var mDatas:List<String>): RecyclerView.Adapter<MyAdapter2.MyViewHolder>() {

    var longlistener: LongClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.recycler_view_item2, parent, false)
        return object : MyViewHolder(view) {}
    }

    override fun getItemCount(): Int {
        return mDatas?.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        with(holder.itemView){
            title.text = mDatas[position]
        }
        holder.itemView.setOnLongClickListener {
            longlistener?.onLongClick()
            false
        }

    }

    open class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface LongClickListener{
        fun onLongClick()
    }

    public fun setLongClickListener(l: LongClickListener){
        longlistener = l
    }
}