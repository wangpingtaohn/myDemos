package com.wpt.mydemos.packer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import kotlinx.android.synthetic.main.activity_picker_view.*

import android.widget.Toast
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.wpt.mydemos.R


class PickerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picker_view)


        /*val opt = OptionsPickerBuilder(this, object :OnOptionsSelectListener{
            override fun onOptionsSelect(p0: Int, p1: Int, p2: Int, p3: View?) {

            }

        }).build<String>()

        val list1 = mutableListOf<String>()
        val list2 = mutableListOf<MutableList<String>>()

        list1.add("海口")

        for (i in 0..13){
            list1.add(i.toString())
            list2.add(list1)
        }
        opt.setPicker(list1)*/

        btn_picker.setOnClickListener {
            setCustomPicker()
//            showPicker()
        }


    }

    private fun showPicker(){
        val list1 = mutableListOf<String>()
        val list2_0 = mutableListOf<String>()
        val list2 = mutableListOf<MutableList<String>>()

        list1.add("海南")

        list2_0.add("海口")
        list2_0.add("三亚")
        list2_0.add("琼海")
        list2_0.add("万宁")
        list2_0.add("五指山")
        list2_0.add("屯昌")
        list2_0.add("文昌")
        list2_0.add("乐东")


        list2.add(list2_0)

        val opt = OptionsPickerBuilder(this, object : OnOptionsSelectListener {
            override fun onOptionsSelect(p0: Int, p1: Int, p2: Int, p3: View?) {

            }

        }).build<String>()
        opt.setPicker(list1,list2)
        opt.show()
    }

    private fun setCustomPicker(){
// 注意：自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针
        // 具体可参考demo 里面的两个自定义布局
        // 注意：自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针
        // 具体可参考demo 里面的两个自定义布局
        val list1 = mutableListOf<String>()
        val list2_0 = mutableListOf<String>()
        val list2_1 = mutableListOf<String>()
        val list2 = mutableListOf<MutableList<String>>()

        list1.add("海南")
        list1.add("广东")

        list2_0.add("海口")
        list2_0.add("三亚")
        list2_0.add("琼海")
        list2_0.add("万宁")
        list2_0.add("五指山")
        list2_0.add("屯昌")
        list2_0.add("文昌")
        list2_0.add("乐东")

        list2_1.add("广州")
        list2_1.add("深圳")
        list2_1.add("珠海")
        list2_1.add("佛山")
        list2_1.add("惠州")
        list2_1.add("江门")
        list2_1.add("汕头")
        list2_1.add("茂名")


        list2.add(list2_0)
        list2.add(list2_1)

        /*var pvCustomOptions = OptionsPickerBuilder(
            this
        ) { options1, option2, options3, v -> //返回的分别是三个级别的选中位置

        }
            .setLayoutRes(
                R.layout.pickerview_custom_options
            ) { v -> //自定义布局中的控件初始化及事件处理
                val tvSubmit = v.findViewById<View>(R.id.tv_finish) as TextView
//                val tvAdd = v.findViewById<View>(R.id.tv_add) as TextView
//                val ivCancel: ImageView = v.findViewById<View>(R.id.tv) as ImageView
                tvSubmit.setOnClickListener {
//                    pvCustomOptions.returnData(tvSubmit)
                }
//                ivCancel.setOnClickListener(View.OnClickListener { pvCustomOptions.dismiss() })
                *//*tvAdd.setOnClickListener {
                    getData()
                    pvCustomOptions.setPicker(cardItem)
                }*//*
            }.setContentTextSize(18)
            .setTextColorCenter(ContextCompat.getColor(this, R.color.color_333))
            .setDividerType(WheelView.DividerType.FILL)
            .setLineSpacingMultiplier(3f)
//            .setDividerColor(ContextCompat.getColor(this,R.color.color_F4F5FA))
            .build<Any>()
        pvCustomOptions.setPicker(list1 as List<Any>?, list2 as List<MutableList<Any>>?) //添加数据
        pvCustomOptions.show()*/

        ZKPickerViewUtils.show(this,"城市选择",list1,list2
        ) { p0, p1, p2, p3 ->
            Toast.makeText(
                this@PickerViewActivity,
                "$p0,$p1,$p2",
                Toast.LENGTH_SHORT
            ).show()
        }

    }
}