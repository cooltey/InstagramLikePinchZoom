package org.cooltey.instagramlikepinchzoom

import android.app.Dialog
import android.graphics.Matrix
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.Window
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.drawable.ProgressBarDrawable
import com.facebook.drawee.drawable.ScalingUtils
import com.facebook.drawee.generic.GenericDraweeHierarchy
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder
import com.facebook.drawee.interfaces.DraweeController
import com.facebook.samples.zoomable.ZoomableController
import com.facebook.samples.zoomable.ZoomableDraweeView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

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

//        imageView.zoomableController.setListener(object: ZoomableController.Listener {
//            override fun onTransformEnd(transform: Matrix?) {
//                Toast.makeText(this@MainActivity, "onTransformEnd", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onTransformChanged(transform: Matrix?) {
//                Toast.makeText(this@MainActivity, "onTransformChanged", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onTransformBegin(transform: Matrix?) {
//                Toast.makeText(this@MainActivity, "onTransformBegin", Toast.LENGTH_SHORT).show()
//            }
//
//        })
//
//        imageView.setTapListener( object : SimpleOnGestureListener() {
//
//            override fun onDown(e: MotionEvent): Boolean {
//                Toast.makeText(this@MainActivity, "onDown", Toast.LENGTH_SHORT).show()
//
//                imageView.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
//                imageView.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
//                imageView.requestLayout()
//                return super.onDown(e)
//            }
//        })
    }

    private fun showDialog(hierarchy: GenericDraweeHierarchy, draweeController: DraweeController) {
        val view = ZoomableDraweeView(this)
        val dialog =  Dialog(this, android.R.style.Theme_Translucent_NoTitleBar)
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(view)
        view.controller = draweeController
        view.hierarchy = hierarchy
        dialog.show()
    }
}
