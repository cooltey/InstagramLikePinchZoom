package org.cooltey.instagramlikepinchzoom

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import com.facebook.drawee.view.SimpleDraweeView
import kotlinx.android.synthetic.main.activity_view_pager.*
import kotlinx.android.synthetic.main.activity_view_pager.mainActivityButton


class ViewPagerActivity : AppCompatActivity() {

    lateinit var imageZoomHelper: ImageZoomHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
        imageZoomHelper = ImageZoomHelper(this)
        viewPager.adapter = ViewPagerAdapter(this)

        mainActivityButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        cardActivityButton.setOnClickListener {
            startActivity(Intent(this, CardActivity::class.java))
        }
    }

    internal class ViewPagerAdapter(var context: Context) : PagerAdapter() {
        private var imagesUrlList = listOf(
            "https://upload.wikimedia.org/wikipedia/commons/thumb/7/70/Sinclair_C5_with_high_vis_mast.jpg/640px-Sinclair_C5_with_high_vis_mast.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/Hydrornis_irena_-_Sri_Phang_Nga.jpg/640px-Hydrornis_irena_-_Sri_Phang_Nga.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/%E5%A4%A7%E6%B8%AF%E5%9F%94%E9%BC%93%E5%A3%BD%E5%AE%AE%E6%AD%A3%E9%9D%A2%E7%85%A7.jpg/640px-%E5%A4%A7%E6%B8%AF%E5%9F%94%E9%BC%93%E5%A3%BD%E5%AE%AE%E6%AD%A3%E9%9D%A2%E7%85%A7.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/2/22/Nvliu66.jpg/640px-Nvliu66.jpg")

        override fun getCount(): Int {
            return imagesUrlList.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val itemView: View = LayoutInflater.from(context).inflate(R.layout.item_card, container, false)
            val imageView: SimpleDraweeView = itemView.findViewById(R.id.imageView)
            container.addView(itemView)
            imageView.setImageURI(imagesUrlList[position])
            ImageZoomHelper.setViewZoomable(imageView)
            return itemView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return imageZoomHelper.onDispatchTouchEvent(ev) || super.dispatchTouchEvent(ev)
    }
}
