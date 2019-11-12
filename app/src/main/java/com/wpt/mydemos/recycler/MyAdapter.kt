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

    var isEdit:Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.recycler_view_item, parent, false)
        return  MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData?.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        with(holder.itemView){
            var item = mData[position]
            item?.run {
                setOnLongClickListener {
                    //longlistener?.onLongClick()
                    if (!isEdit){
//                        img1_sel.visibility = View.VISIBLE
                        isDelete = true
                        isEdit = true
                        this@MyAdapter.notifyDataSetChanged()
                        true
                    } else {
                        true
                    }

                }
                img1.setImageResource(resId)
                setOnClickListener {
                    var pos = holder.adapterPosition
                    if (isEdit && !isDelete){
                        img1_sel.visibility = View.VISIBLE
                        isDelete = true
                    } else{
                        img1_sel.visibility = View.GONE
                        isDelete = false
                    }
                }
//            itemView.img1_sel.setTag(position)
                if (isDelete) {
                    img1_sel.visibility = View.VISIBLE
                } else {
                    img1_sel.visibility = View.GONE
                }
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
        longlistener = l
    }
}