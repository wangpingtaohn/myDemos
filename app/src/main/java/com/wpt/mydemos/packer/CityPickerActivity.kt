package com.wpt.mydemos.packer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.multilevel.treelist.Node
import com.wpt.mydemos.R
import com.zaaach.citypicker.CityPicker
import kotlinx.android.synthetic.main.activity_city_picker.*

class CityPickerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_picker)

        btnShowCity.setOnClickListener {
            CityPicker.from(this)
                .show()
        }

        btnShowTree.setOnClickListener {
            startActivity(Intent(this,TreeListActivity::class.java))
            showTreeList()
        }
    }

    private fun showTreeList(){
        val list = mutableListOf<TreeBean>()
        for (i in 0..9){
            val node = TreeBean(i,-1,"第一层${i}个")
//            node.isExpand = true
            val subList = mutableListOf<TreeBean>()
            list.add(node)
            for (j in 10..19){
                val subNode = TreeBean(j,i,"第二层${j}个")
//                subList.add(subNode);
                list.add(subNode)
                for (k in 20..29){
                    val sub2Node = TreeBean(k,j,"第三层${k}个")
//                subList.add(subNode);
                    list.add(sub2Node)
                }
            }
//            node.children = subList
//            list.add(node)
        }
        val treeAdapter = MyTreeAdapter(rvTree,this, list as List<Node<Any, Any>>?,1,R.drawable.ic_love01,R.drawable.ic_love01)
        with(rvTree){
            layoutManager = LinearLayoutManager(this@CityPickerActivity,LinearLayoutManager.VERTICAL,false)
            adapter = treeAdapter

        }
    }
}