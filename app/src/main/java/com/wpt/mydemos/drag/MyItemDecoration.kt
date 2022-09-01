package com.wpt.mydemos.drag

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.text.TextUtils
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.wpt.mydemos.R
import kotlin.math.max

class MyItemDecoration(context: Context, private val listener: GroupListener) :
    RecyclerView.ItemDecoration() {

    private val mHeight = 120

    private val mPaint = Paint()

    private val mTextPaint = Paint()

    init {
        mPaint.color = ContextCompat.getColor(context, R.color.colorGray)
        mTextPaint.color = ContextCompat.getColor(context, R.color.color_333)
        mTextPaint.textSize = 52f
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        /*val left = parent.left
        val right = parent.right
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val childView = parent.getChildAt(i)
            val bottom = childView.top
            val top = bottom - mHeight
            c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)
        }*/
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val itemCount = state.itemCount
        val childCount = parent.childCount
        val left = parent.left - parent.paddingLeft
        val right = parent.right - parent.paddingRight
        var preGroupName: String? = null //上一个组名
        var curGroupName: String? = null //当前组名
        for (i in 0 until childCount) {
            val childView = parent.getChildAt(i)
            val pos = parent.getChildAdapterPosition(childView)
            preGroupName = curGroupName
            curGroupName = listener.getGroupName(pos)
            if (curGroupName == null || TextUtils.equals(preGroupName, curGroupName)) {
                continue
            }
            val viewBottom = childView.bottom
            var top = max(mHeight, childView.top)//top 决定当前顶部第一个悬浮Group的位置
            if (pos + 1 < itemCount) {
                val nextGroupName = listener.getGroupName(pos + 1)
                if (curGroupName != nextGroupName && viewBottom < top) {
                    top = viewBottom
                }
            }
            //根据top绘制group
            c.drawRect(
                left.toFloat(),
                (top - mHeight).toFloat(),
                right.toFloat(),
                top.toFloat(),
                mPaint
            )
            val fm: Paint.FontMetrics = mTextPaint.fontMetrics
            //文字竖直居中显示
            val baseLine: Float = top - (mHeight - (fm.bottom - fm.top)) / 2 - fm.bottom
            c.drawText(curGroupName, left.toFloat(), baseLine, mTextPaint);
        }

    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val pos = parent.getChildAdapterPosition(view)
        /*if (pos != 0){
            outRect.top = mHeight
        }*/
        listener.getGroupName(pos) ?: return
        //只有是同一组的第一个才显示悬浮栏
        if (pos == 0 || isFirstInGroup(pos)) {
            outRect.top = mHeight;
        }

    }

    //判断是不是组中的第一个位置
    //根据前一个组名，判断当前是否为新的组
    private fun isFirstInGroup(pos: Int): Boolean {
        return if (pos == 0) {
            true
        } else {
            val prevGroupId: String? = listener.getGroupName(pos - 1)
            val groupId: String? = listener.getGroupName(pos)
            !TextUtils.equals(prevGroupId, groupId)
        }
    }

    interface GroupListener {
        fun getGroupName(position: Int): String?
    }
}