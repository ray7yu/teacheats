package com.teach.eats

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.widget.ImageView
import com.facebook.shimmer.ShimmerFrameLayout
import java.io.InputStream
import java.net.URL


class Origin(private val bmImage: ImageView, private val shimmerView: ShimmerFrameLayout) : AsyncTask<String?, Void?, Bitmap?>() {
    override fun doInBackground(vararg params: String?): Bitmap? {
        val urldisplay = params[0]
        var mIcon11: Bitmap? = null
        try {
            val `in`: InputStream = URL(urldisplay).openStream()
            mIcon11 = BitmapFactory.decodeStream(`in`)
        } catch (e: Exception) {
            Log.e("Error", e.message)
            e.printStackTrace()
        }
        return mIcon11
    }
    override fun onPostExecute(result: Bitmap?) {
        bmImage.setImageBitmap(result)
        shimmerView.stopShimmer()
    }
}