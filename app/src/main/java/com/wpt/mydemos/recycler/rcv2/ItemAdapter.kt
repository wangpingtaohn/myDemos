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
class ItemAdapter(private var context: Context): RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = when (viewType) {
            1 ->
                LayoutInflater.from(context).inflate(
                    R.layout.item_1,
                    parent,
                    false
                )
            2 ->
                LayoutInflater.from(context).inflate(
                    R.layout.item_2,
                    parent,
                    false
                )
            3 ->
                LayoutInflater.from(context).inflate(
                    R.layout.item_3,
                    parent,
                    false
                )
            4 ->
                LayoutInflater.from(context).inflate(
                    R.layout.item_4,
                    parent,
                    false
                )
            5 ->
                LayoutInflater.from(context).inflate(
                    R.layout.item_5,
                    parent,
                    false
                )
            else ->
                LayoutInflater.from(context).inflate(
                    R.layout.item_6,
                    parent,
                    false
                )
        }

        return object : RecyclerView.ViewHolder(view) {}
    }

    override fun getItemViewType(position: Int): Int {
        return position + 1
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

}