package com.wpt.mydemos.recycler

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.recycler_view_item.view.*

/**
 *    author : wpt
 *    date   : 2019-10-08 14:34
 *    desc   :
 */
class MyAdapter(private var context: Context,private var mData:List<ItemBean>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    var longlistener: LongClickListener ? = null

    var isEdit:Boolean = false;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.recycler_view_item, parent, false)
        return  MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData?.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        with(holder){
            itemView.setOnLongClickListener {
                //longlistener?.onLongClick()
                itemView.img1_sel.visibility = View.VISIBLE
                mData[position].isDelete = true
                isEdit = true
                false
            }
            itemView.img1.setImageResource(mData[position].resId)
            itemView.setOnClickListener {
                if (isEdit){
                    itemView.img1_sel.visibility = View.VISIBLE
                    mData[position].isDelete = true
                }
            }
//            itemView.img1_sel.setTag(position)
            if (mData[position].isDelete) {
                itemView.img1_sel.visibility = View.VISIBLE
            } else {
                itemView.img1_sel.visibility = View.GONE
            }
        }

    }

    public fun setIsEdit(isEdit:Boolean){
        this.isEdit = isEdit
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface LongClickListener{
        fun onLongClick()
    }

    public fun setLongClickListener(l:LongClickListener){
        longlistener = l;
    }
}