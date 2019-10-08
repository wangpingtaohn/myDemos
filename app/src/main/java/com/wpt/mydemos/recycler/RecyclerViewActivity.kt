package com.wpt.mydemos.recycler

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity(),MyAdapter.LongClickListener {

    override fun onLongClick() {
//        root_view.setBackgroundColor(Color.parseColor("#1A000000"))
        root_view.visibility = View.VISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        var adapter =  MyAdapter(this)
        adapter.setLongClickListener(this)

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = adapter
    }
}
