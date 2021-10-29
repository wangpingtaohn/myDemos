package com.wpt.mydemos.packer

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.contrarywind.view.WheelView
import com.wpt.mydemos.R

/**
 * Author: wpt
 * Time: 2021/10/28
 *
 * @Desc：
 */
object ZKPickerViewUtils {
    fun <T> show(context: Context,title: String?, list: List<T>?, listener: OnOptionsSelectListener?) {show(context,title,list,null,null)}
    fun <T> show(
        context: Context,
        title: String?,
        list: List<T>?,
        list2: List<List<T>?>?,
        listener: OnOptionsSelectListener?
    ) {
        show(context,title,list,list2,null,listener)
    }

    fun <T> show(
        context: Context,
        title: String?,
        list1: List<T>?,
        list2: List<List<T>?>?,
        list3: List<List<List<T>?>?>?,
        listener: OnOptionsSelectListener?
    ) {
        var op1:Int = 0
        var op2:Int = 0
        var op3:Int = 0
        var view:View ? = null
        var tvCancel: TextView? = null
        var tvTitle: TextView? = null
        var tvSubmit: TextView? = null
        var pvCustomOptions = OptionsPickerBuilder(
            context
        ) { options1, option2, options3, v -> //返回的分别是三个级别的选中位置
            op1 = options1
            op2 = option2
            op3 = options3
            view = v
        }
            .setLayoutRes(
                R.layout.pickerview_custom_options
            ) { v -> //自定义布局中的控件初始化及事件处理
                tvSubmit = v.findViewById<View>(R.id.tv_finish) as TextView
                tvTitle = v.findViewById<View>(R.id.tv_title) as TextView
                tvCancel = v.findViewById<View>(R.id.tv_cancel) as TextView

            }.setContentTextSize(18)
            .setTitleText(title)
            .setTextColorCenter(ContextCompat.getColor(context, R.color.color_333))
            .setDividerType(WheelView.DividerType.FILL)
            .setLineSpacingMultiplier(3f)
//            .setDividerColor(ContextCompat.getColor(this,R.color.color_F4F5FA))
            .build<Any>()
        pvCustomOptions?.setPicker(list1, list2,list3) //添加数据
        pvCustomOptions?.show()
//        listener?.onOptionsSelect(op1,op2,op3,view)
        tvTitle?.text = title

        tvSubmit?.setOnClickListener {
            pvCustomOptions?.returnData()
            listener?.onOptionsSelect(op1,op2,op3,view)
        }
        tvCancel?.setOnClickListener {
            pvCustomOptions?.dismiss()
        }
    }
}