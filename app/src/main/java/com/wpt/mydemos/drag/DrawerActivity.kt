package com.wpt.mydemos.drag

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wpt.mydemos.R
import com.wpt.mydemos.dragback.BanDragBack
import kotlinx.android.synthetic.main.activity_drawer.*
import androidx.drawerlayout.widget.DrawerLayout
import android.app.Activity
import android.graphics.Point
import androidx.customview.widget.ViewDragHelper




@BanDragBack(can = true)
class DrawerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)

        btn_drawer.setOnClickListener {
            drawer_root.openDrawer(drawer_layout)
        }

        initView()

        setDrawerLeftEdgeSize(this,drawer_root,0.5f)
    }


    private fun initView(){
        val fragment = DrawerFragment()
        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.drawer_layout,fragment)
        ft.commitAllowingStateLoss()
    }

    /**
     * 解决DrawerLayout只能从边缘滑动
     * @param activity
     * @param drawerLayout
     * @param displayWidthPercentage 滑动的范围0~1
     */
    private fun setDrawerLeftEdgeSize(
        activity: Activity?,
        drawerLayout: DrawerLayout?,
        displayWidthPercentage: Float
    ) {
        if (activity == null || drawerLayout == null) return
        try {
            // 找到 ViewDragHelper 并设置 Accessible 为true
            val rightDraggerField = drawerLayout.javaClass.getDeclaredField("mRightDragger")//Right
            rightDraggerField.isAccessible = true
            val rightDragger = rightDraggerField.get(drawerLayout) as ViewDragHelper

            // 找到 edgeSizeField 并设置 Accessible 为true
            val edgeSizeField = rightDragger.javaClass.getDeclaredField("mEdgeSize")
            edgeSizeField.isAccessible = true
            val edgeSize = edgeSizeField.getInt(rightDragger)

            // 设置新的边缘大小
            val displaySize = Point()
            activity.windowManager.defaultDisplay.getSize(displaySize)
            edgeSizeField.setInt(
                rightDragger,
                Math.max(edgeSize, (displaySize.x * displayWidthPercentage).toInt())
            )

            /*以下代码是解决长按也触发的bug*/
            //获取 Layout 的 ViewDragCallBack 实例“mRightCallback”
            //更改其属性 mPeekRunnable
            val rightCallbackField = drawerLayout.javaClass.getDeclaredField("mRightCallback")
            rightCallbackField.isAccessible = true

            //因为无法直接访问私有内部类，所以该私有内部类实现的接口非常重要，通过多态的方式获取实例
            val leftCallback = rightCallbackField.get(drawerLayout) as ViewDragHelper.Callback

            val peekRunnableField = leftCallback.javaClass.getDeclaredField("mPeekRunnable")
            peekRunnableField.isAccessible = true
            val nullRunnable = Runnable { }
            peekRunnableField.set(leftCallback, nullRunnable)

        } catch (e: NoSuchFieldException) {
        } catch (e: IllegalArgumentException) {
        } catch (e: IllegalAccessException) {
        }

    }
}
