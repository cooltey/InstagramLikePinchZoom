package org.cooltey.instagramlikepinchzoom

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.drawable.ScalingUtils
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder
import kotlinx.android.synthetic.main.activity_card.*
import kotlinx.android.synthetic.main.activity_main.imageView
import kotlinx.android.synthetic.main.activity_main.viewPagerActivityButton


class CardActivity : AppCompatActivity() {

    private lateinit var imageZoomHelper: ImageZoomHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        val uri: Uri =
            Uri.parse("https://upload.wikimedia.org/wikipedia/commons/f/f9/Mandarin_duck_in_Central_Park_%2830282%29_-_cropped.jpg")

        // setup image controller and hierarchy
        val controller = Fresco.newDraweeControllerBuilder().setUri(uri).build()
        val hierarchy = GenericDraweeHierarchyBuilder(resources)
            .setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER)
            .build()


        imageView.controller = controller
        imageView.hierarchy = hierarchy

        imageZoomHelper = ImageZoomHelper(this)
        ImageZoomHelper.setViewZoomable(imageView)
        imageZoomHelper.addOnZoomListener(object: ImageZoomHelper.OnZoomListener {
            override fun onImageZoomStarted(view: View?) {
            }

            override fun onImageZoomEnded(view: View?) {
            }
        })

        mainActivityButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        viewPagerActivityButton.setOnClickListener {
            startActivity(Intent(this, ViewPagerActivity::class.java))
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return imageZoomHelper.onDispatchTouchEvent(ev) || super.dispatchTouchEvent(ev)
    }
}
