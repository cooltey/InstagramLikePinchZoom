package org.cooltey.instagramlikepinchzoom

import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.drawable.ProgressBarDrawable
import com.facebook.drawee.drawable.ScalingUtils
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var imageZoomHelper: ImageZoomHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        setContentView(R.layout.activity_main)

        val uri: Uri =
            Uri.parse("https://upload.wikimedia.org/wikipedia/commons/thumb/0/08/" +
                    "Physicist_Stephen_Hawking_in_Zero_Gravity_NASA.jpg/2880px-Physicist_Stephen_Hawking_in_Zero_Gravity_NASA.jpg")

        // setup image controller and hierarchy
        val controller = Fresco.newDraweeControllerBuilder().setUri(uri).build()
        val hierarchy = GenericDraweeHierarchyBuilder(resources)
            .setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER)
            .setProgressBarImage(ProgressBarDrawable())
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
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return imageZoomHelper.onDispatchTouchEvent(ev) || super.dispatchTouchEvent(ev)
    }
}
