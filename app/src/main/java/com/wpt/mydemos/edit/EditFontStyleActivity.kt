package com.wpt.mydemos.edit

import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.util.Log
import com.wpt.mydemos.R
import com.wpt.mydemos.widget.BaseActivity
import kotlinx.android.synthetic.main.activity_edit_font_style.*

class EditFontStyleActivity : BaseActivity() {

    var list:ArrayList<FontBean> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_font_style)

        initView()
    }

    private fun initView(){
        tv_red.setOnClickListener {
            val start = edittext.selectionStart
            val end = edittext.selectionEnd
            Log.d(LOG_TAG, "start=$start,end=$end")
            val ssb = SpannableStringBuilder(edittext.text)
            val colorId = resources.getColor(R.color.red)
            val fcs = ForegroundColorSpan(colorId)
            ssb.setSpan(fcs,start,end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            edittext.text = ssb
            edittext.setSelection(end)
            setBeanInfo(colorId, start,end,ssb)

        }
        tv_blue.setOnClickListener {
            val start = edittext.selectionStart
            val end = edittext.selectionEnd
            val ssb = SpannableStringBuilder(edittext.text)
            val colorId = resources.getColor(R.color.blue)
            val fcs = ForegroundColorSpan(colorId)
            ssb.setSpan(fcs,start,end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            edittext.text = ssb
            edittext.setSelection(end)
            setBeanInfo(colorId, start,end,ssb)
        }
        tv_green.setOnClickListener {
            val start = edittext.selectionStart
            val end = edittext.selectionEnd
            val ssb = SpannableStringBuilder(edittext.text)
            val colorId = resources.getColor(R.color.green)
            val fcs = ForegroundColorSpan(colorId)
            ssb.setSpan(fcs,start,end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            edittext.text = ssb
            edittext.setSelection(end)
            setBeanInfo(colorId, start,end,ssb)
        }
        pass_btn.setOnClickListener {
            if (list.isEmpty()){
                edittext2.text = edittext.text
            } else {
                var ssb = SpannableStringBuilder(edittext.text)
                for (fontBean in list){
                    val fcs = ForegroundColorSpan(fontBean.color)
                    ssb.setSpan(fcs,fontBean.start,fontBean.end,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
                edittext2.text = ssb
            }
        }

        edittext.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d(LOG_TAG, "start=$start,count=$count")
                if (list.isNotEmpty() && count > 0){
                    for (font in list){
                        val length = font.end - font.start
                        if (font.start <= start && start <= font.end){//span的全部范围内
                            if (length <= count) {
                                list.remove(font)
                                break
                            } else {
                                font.end = font.end - count
                            }
                            break
                        } else if (start < font.start && count >= (font.start - start)){ //star前至end前
                            val offset = font.start - start
                            if (count - offset <= length ){
                                font.start = start
                                font.end = font.end - count
                            }
                        }
                    }
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })


    }

    private fun setBeanInfo(color:Int,start:Int,end:Int,ssb:SpannableStringBuilder){
        val fontBean = FontBean()
        fontBean.start = start
        fontBean.end = end
        fontBean.content = ssb.substring(start,end)
        fontBean.color = color
        list.add(fontBean)
    }

    class FontBean {
        var start:Int = 0
        var end:Int = 0
        var color:Int = -1
        var content:String = ""
    }
}
