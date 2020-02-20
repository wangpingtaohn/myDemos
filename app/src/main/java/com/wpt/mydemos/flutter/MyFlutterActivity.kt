package com.wpt.mydemos.flutter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.activity_native_page.*



class MyFlutterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native_page)

        init()
    }

    private fun init(){
        val params = HashMap<String,String>()
        params.put("test1", "v_test1")
        params.put("test2", "v_test2")
        open_native.setOnClickListener {
            PageRouter.openPageByUrl(this, PageRouter.NATIVE_PAGE_URL,params)
        }
        open_flutter.setOnClickListener {
            PageRouter.openPageByUrl(this, PageRouter.FLUTTER_PAGE_URL,params)

        }
        open_flutter_fragment.setOnClickListener {
            PageRouter.openPageByUrl(this, PageRouter.FLUTTER_FRAGMENT_PAGE_URL,params)
        }
        open_flutter_test.setOnClickListener {
            PageRouter.openPageByUrl(this, PageRouter.FLUTTER_TEST_PAGE_URL,params)
        }
    }
}
