package com.sample.samplemovies.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

object ImageUtils {
    fun setMovieImage(
        imagePath: String?,
        imageView: ImageView,
        placeHolder: Int,
        context: Context
    ) {
        val fullUrl = "https://image.tmdb.org/t/p/original".plus(imagePath)
        Glide.with(context).load(fullUrl)
            .apply(
                RequestOptions().centerCrop().error(placeHolder)
                    .fitCenter().placeholder(placeHolder)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
            ).into(imageView)
    }
}
