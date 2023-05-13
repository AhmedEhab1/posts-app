package com.stc.ahmedehabtask.utilities

import android.content.Context
import android.util.Log
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.stc.ahmedehabtask.R
import okhttp3.RequestBody
import retrofit2.http.Part
import java.io.File

object ImageHelper {
    fun loadImage(context: Context, url: String?, holder: Int, imageView: ImageView) {
        imageView.clipToOutline = true
        if (url != null && url != "") {
            try {
                val drawable = CircularProgressDrawable(context)
                drawable.setColorSchemeColors(
                    R.color.primary,
                    R.color.black,
                    R.color.primary
                )
                drawable.setCenterRadius(30f)
                drawable.setStrokeWidth(5f)
                drawable.start()
                Glide.with(context)
                    .load(url)
                    .placeholder(drawable)
                    .error(holder)
                    .into(imageView)
            } catch (e: Exception) {
                Log.d("glide_load_image", "loadeImage: $e")
            }
        } else {
            imageView.setImageResource(holder)
        }
    }


}