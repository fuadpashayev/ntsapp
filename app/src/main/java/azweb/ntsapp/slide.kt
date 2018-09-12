package azweb.ntsapp

import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.Transformation

fun View.expand() {
    val v = this
    v.visibility = View.VISIBLE
    v.measure(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
    val targetHeight = v.getMeasuredHeight()
    // Older versions of android (pre API 21) cancel animations for views with a height of 0.
    v.layoutParams.height = 1

    val a = object: Animation() {
        override fun applyTransformation(interpolatedTime:Float, t: Transformation) {
            v.getLayoutParams().height = if (interpolatedTime == 1f)
                WindowManager.LayoutParams.WRAP_CONTENT
            else
                (targetHeight * interpolatedTime*3.1).toInt()
            v.requestLayout()
        }
        override fun willChangeBounds():Boolean {
            return true
        }
    }
    // 1dp/ms
    a.duration = 300
    v.startAnimation(a)
}
fun View.collapse() {
    val v = this
    val initialHeight = v.getMeasuredHeight()
    val a = object:Animation() {
        override fun applyTransformation(interpolatedTime:Float, t:Transformation) {
            if (interpolatedTime == 1f)
            {
                v.setVisibility(View.GONE)
            }
            else
            {
                v.getLayoutParams().height = initialHeight - (initialHeight * interpolatedTime).toInt()
                v.requestLayout()
            }
        }
        override fun willChangeBounds():Boolean {
            return true
        }
    }
    // 1dp/ms
    a.duration = 300
    v.startAnimation(a)
}