package azweb.ntsapp

import android.app.Activity
import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.image_pager_layout.view.*

class imagePagerAdapter(val context: Context,val images:ArrayList<String>):PagerAdapter(){

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.image_pager_layout,null)
        val imgHolder = view.imagePagerImage

        Glide.with(context)
                .load(images[position])
                .thumbnail(Glide.with(context).load(R.mipmap.image))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(imgHolder)
        val viewPager = container
        viewPager.addView(view,0)
        return view

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val viewPager = container
        val view = `object` as View
        viewPager.removeView(view)
    }

}