package com.wpt.mydemos.flutter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.activity_native_page.*



class FlutterDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native_page)

        init()
    }

    private fun init(){
        val params = HashMap<String,String>()
        params["token"] = "6b484ae609284374bb91ad5c1b38c480"
        params["env"] = "debug"
        params["version"] = "2.13.4"
        open_native.setOnClickListener {
//            PageRouter.openPageByUrl(this, PageRouter.NATIVE_PAGE_URL,params)
        }
        open_flutter.setOnClickListener {
//            PageRouter.openPageByUrl(this, PageRouter.FLUTTER_PAGE_URL,params)

        }
        open_flutter_fragment.setOnClickListener {
//            PageRouter.openPageByUrl(this, PageRouter.FLUTTER_FRAGMENT_PAGE_URL,params)
        }
        open_flutter_test.setOnClickListener {
//            PageRouter.openPageByUrl(this, PageRouter.FLUTTER_MY_WALLET,params)
        }
    }
}
