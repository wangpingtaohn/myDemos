package com.wpt.mydemos.recycler.rcv1

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.idlefish.flutterboost.containers.BoostFlutterActivity
import com.jaeger.library.StatusBarUtil
import com.wpt.mydemos.R
import com.wpt.mydemos.StatusBarUtils
import kotlinx.android.synthetic.main.activity_recycler_view.*

/**
 * 处理RecyclerView的数据源发生变化后，不立即执行notifyDataSetChanged的现象
 */
class RecyclerViewActivity : BoostFlutterActivity(), MyAdapter.LongClickListener {

    private var list = mutableListOf<String>()

    private var imgList = mutableListOf<ItemBean>()

    private var adapter : MyAdapter? = null

    private var adapter2 : MyAdapter2? = null


    override fun onLongClick() {
//        root_view.setBackgroundColor(Color.parseColor("#1A000000"))
        root_view.visibility = View.VISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
//        }

        //给contentView上背景色 不上色会导致无法看到侧滑
//        val contentView = window.decorView
//        contentView.setBackgroundColor(Color.parseColor("#ffffff"))
//        contentView.fitsSystemWindows = true

        StatusBarUtils.setStatusBarTransparent(this, R.color.blue)

//        window.statusBarColor = Color.TRANSPARENT


        initView()
    }

    private fun initView(){
        for (i in 0..99) {
            list.add(i.toString())

        }
        for (i in 0..71) {
            var bean = ItemBean()
            bean.resId = R.drawable.bg_btn_yellow_corner6
            imgList.add(bean)
        }
//        adapter2 =  MyAdapter2(this,list)
        adapter = MyAdapter(this, imgList)
        adapter?.setLongClickListener(this)


        recyclerView.layoutManager = GridLayoutManager(this, 2)

        if (adapter != null){
            recyclerView.adapter = adapter
        } else {
            recyclerView.adapter = adapter2
        }

        btn1.setOnClickListener {
            if (adapter2 != null){
//                list.clear()
                list.removeAt(0)
            }
        }
        notify.setOnClickListener {
            if (adapter != null){
                adapter!!.notifyItemChanged(0)
            }
        }

        btn2.setOnClickListener {
            if (adapter2 != null){
                list.add("btn2")
                adapter2?.notifyDataSetChanged()
            }
        }

        delete.setOnClickListener {
            adapter?.setIsEdit(false)
            var temp = mutableListOf<ItemBean>()
            for (item in imgList){
                if (item.isDelete){
                    temp.add(item)
                }
            }
            if (temp.size > 0){
                imgList.removeAll(temp)
            }
            adapter?.notifyDataSetChanged()
        }
        select_all.setOnClickListener {
            for (item in imgList){
                item.isDelete = true
            }
            adapter?.notifyDataSetChanged()
        }
    }

    class MyLinearLayoutManager(context: Context?) : LinearLayoutManager(context) {

        override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
            try {
                super.onLayoutChildren(recycler, state)
            } catch (e:IndexOutOfBoundsException){
                Log.e("",e.message)
            }
        }

        override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
            try {
                return super.scrollVerticallyBy(dy, recycler, state)
            } catch (e:IndexOutOfBoundsException){
                Log.e("",e.message)
                return 0
            }
        }

    }
}
