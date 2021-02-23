package com.wpt.mydemos.wasabeef

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import com.wpt.mydemos.R
import kotlinx.android.synthetic.main.activity_wasabeef.*
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.wpt.mydemos.dragback.BanDragBack
import kotlinx.android.synthetic.main.activity_wasabeef.tv_ver

@BanDragBack(can = true,name = "WasabeefActivity")
class WasabeefActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        SwipeBackHelper.onCreate(this)
        setContentView(R.layout.activity_wasabeef)

        tv_ver.movementMethod = ScrollingMovementMethod.getInstance()

        change_btn.setOnClickListener {
            /*Glide.with(applicationContext)
//                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1602564621545&di=d7fd11d1caa7472e37c2e778a32cca3b&imgtype=0&src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fblog%2F202008%2F29%2F20200829202141_10cd2.thumb.1000_0.jpg")
                .load(R.drawable.wass_bg)
                .apply(RequestOptions.bitmapTransform(BlurTransformation(applicationContext, 0)))
                .transition(DrawableTransitionOptions.withCrossFade())//淡入淡出
                .into(iv)*/

            Glide.with(applicationContext).load(R.drawable.wass_bg).transition(
                DrawableTransitionOptions.withCrossFade()
            )
                .apply(RequestOptions.bitmapTransform(BlurTransformation(applicationContext, 13, 3)))
                .into(object :
                    SimpleTarget<Drawable>() {
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable>?
                    ) {
                        ll_root.background = resource
                    }
                })
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
//        SwipeBackHelper.onPostCreate(this)
    }

    override fun onDestroy() {
        super.onDestroy()
//        SwipeBackHelper.onDestroy(this)
    }


    private fun getViewToBitmap(view: View): Bitmap {
        var bitmap: Bitmap? = null
        view.buildDrawingCache()
        bitmap = view.drawingCache
        if (bitmap != null) {
            return bitmap
        }

        bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap!!)
        if (Build.VERSION.SDK_INT >= 11) {
            view.measure(
                View.MeasureSpec.makeMeasureSpec(view.width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(view.height, View.MeasureSpec.EXACTLY)
            )
            view.layout(
                view.x.toInt(),
                view.y.toInt(),
                view.x.toInt() + view.measuredWidth,
                view.y.toInt() + view.measuredHeight
            )
        } else {
            view.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            )
            view.layout(0, 0, view.measuredWidth, view.measuredHeight)
        }
        view.draw(canvas)
        return bitmap
    }
}
