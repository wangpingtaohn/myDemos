package com.wpt.mydemos.leecode

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import com.wpt.mydemos.R
import com.wpt.mydemos.widget.BaseActivity
import kotlinx.android.synthetic.main.activity_lee_code.*

class LeeCodeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lee_code)

        initView()
    }

    private fun initView(){
        btn_leecode.setOnClickListener {
            val content = et_leecode.text.toString()
            if (!TextUtils.isEmpty(content)){
                permutation(content)
                tv_leecode.text = contentText
            }
        }
    }


    private fun permutation(str: String?){
        if (TextUtils.isEmpty(str)){
            return
        }
        permutation(str!!.toCharArray(),0)
    }

    var contentText = StringBuffer()
    fun permutation(chars: CharArray,begin: Int) {
        if (begin == chars.size - 1){
            Log.d("===wpt===",chars.toString())
            contentText.append(chars.toString())
                .append(",")
        } else {
            for (i in chars.indices){
                var temp = chars[i]
                chars[i] = chars[begin]
                chars[begin] = temp
                permutation(chars, begin+1)
                temp = chars[i]
                chars[i] = chars[begin]
                chars[begin] = temp
            }
        }

    }
}
