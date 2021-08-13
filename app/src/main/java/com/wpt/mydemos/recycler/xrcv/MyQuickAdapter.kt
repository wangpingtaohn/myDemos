package com.wpt.mydemos.recycler.xrcv

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.DraggableModule
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.module.UpFetchModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wpt.mydemos.R

/**
 * author : wpt
 * date   : 2021/8/214:21
 * desc   :
 */
class MyQuickAdapter : BaseQuickAdapter<String, BaseViewHolder>,LoadMoreModule,UpFetchModule {

    constructor(layoutResId: Int, data: MutableList<String>?) : super(layoutResId, data) {}

    override fun convert(holder: BaseViewHolder, s: String) {
        holder.getView<TextView>(R.id.tv_quick).text = s
    }
}