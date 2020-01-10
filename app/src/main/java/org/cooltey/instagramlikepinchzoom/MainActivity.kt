package org.cooltey.instagramlikepinchzoom

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.facebook.common.logging.FLog
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.drawable.ScalingUtils
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.imagepipeline.listener.RequestListener
import com.facebook.imagepipeline.listener.RequestLoggingListener
import com.facebook.imagepipeline.nativecode.ImagePipelineNativeLoader
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var imageZoomHelper: ImageZoomHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val requestListeners: MutableSet<RequestListener> = HashSet()
        requestListeners.add(RequestLoggingListener())
        val config = ImagePipelineConfig.newBuilder(this) // other setters
            .setRequestListeners(requestListeners)
            .build()

        Fresco.initialize(this, config)
        FLog.setMinimumLoggingLevel(FLog.VERBOSE)
        ImagePipelineNativeLoader.load()
        setContentView(R.layout.activity_main)

        val uri: Uri =
            Uri.parse("https://upload.wikimedia.org/wikipedia/commons/thumb/0/08/" +
                    "Physicist_Stephen_Hawking_in_Zero_Gravity_NASA.jpg/640px-Physicist_Stephen_Hawking_in_Zero_Gravity_NASA.jpg")

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

        cardActivityButton.setOnClickListener {
            startActivity(Intent(this, CardActivity::class.java))
        }

        viewPagerActivityButton.setOnClickListener {
            startActivity(Intent(this, ViewPagerActivity::class.java))
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return imageZoomHelper.onDispatchTouchEvent(ev) || super.dispatchTouchEvent(ev)
    }
}
