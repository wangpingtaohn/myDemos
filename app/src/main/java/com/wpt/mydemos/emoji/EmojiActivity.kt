package com.wpt.mydemos.emoji

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_emoji_layout.*
import android.R
import android.text.style.ImageSpan
import android.text.SpannableString




class EmojiActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.wpt.mydemos.R.layout.activity_emoji_layout)

        //输入的内容转为Unicode编码后
        val str = "\\ud83d\\ude1a\\u5192\\u54af\\u51d1\\u697c\\u4e0b\\u4e86\\u5973\\u5b69\\ud83d\\ude1c\\ud83d\\ude1a"

        edittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                val str = edittext.getText().toString()
                //将输入的内容转Unicode
                val unicode = string2Unicode(str)
                //再将Unicode转为String，直接设置给TextView
                textview.setText(unicode2String(unicode))
//                textview.setText(unicode)
            }
        })

    }

    fun unicode2String(unicode: String): String {
        Log.e("str==", "111111111" + "\\\\u")
        val string = StringBuffer()

        val hex = unicode.split("\\\\u".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        for (i in 1 until hex.size) {
            Log.e("str==", "22222222" + "\\\\u")
            // 转换出每一个代码点
            val data = Integer.parseInt(hex[i], 16)

            // 追加成string
            string.append(data.toChar())
        }

        return string.toString()
    }


    fun string2Unicode(string: String): String {

        val unicode = StringBuffer()

        for (i in 0 until string.length) {

            // 取出每一个字符
            val c = string[i]
            if (c.toInt() < 256)
            //ASC11表中的字符码值不够4位,补00
            {
                unicode.append("\\u00")
            } else {
                unicode.append("\\u")
            }
            // 转换为unicode
            unicode.append(Integer.toHexString(c.toInt()))
        }

        return unicode.toString()
    }

}
