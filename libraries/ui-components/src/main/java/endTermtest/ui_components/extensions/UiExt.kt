package kz.astanamotorstest.ui_components.extensions

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kz.astanamotorstest.ui_components.R
import kz.astanamotorstest.ui_components.utils.EndlessRecyclerOnScrollListener
import java.text.SimpleDateFormat
import java.util.*

fun RecyclerView.onLoadMore(block: () -> Unit) {
    this.clearOnScrollListeners()
    this.addOnScrollListener(object : EndlessRecyclerOnScrollListener() {
        override fun onLoadMore() {
            block.invoke()
        }
    })
}

fun ImageView.loadImage(url: String?) {
    url?.let {
        val circularProgressDrawable = CircularProgressDrawable(context).apply {
            strokeWidth = 5f
            centerRadius = 30f
            start()
        }
        Glide.with(this)
            .load(it)
            .placeholder(circularProgressDrawable)
            .error(R.drawable.ic_error_placeholder)
            .into(this)
    }
}

fun TextView.setReleaseDt(dt: String) {
    val format = SimpleDateFormat("yyyy-MM-dd")
    val oldDate: Date = format.parse(dt)
    val newFormat = SimpleDateFormat("EEE, dd MMM yyyy")
    val newDate: String = format.format(oldDate)
    text = newDate

}

fun Fragment.showSnackBarWithoutButton(layoutView: View, stringId: Int) {
    context?.getText(stringId)?.let {
        Snackbar.make(layoutView, it, Snackbar.LENGTH_SHORT)
            .setTextColor(Color.WHITE)
            .show()
    }
}