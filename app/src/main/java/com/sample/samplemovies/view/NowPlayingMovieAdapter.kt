package com.sample.samplemovies.view

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.sample.samplemovies.R
import com.sample.samplemovies.model.PopularMoviesResponseModel
import kotlinx.android.synthetic.main.now_playing_movie_item.view.*
import kotlinx.android.synthetic.main.popular_movie_item.view.*
import kotlin.collections.ArrayList

class NowPlayingMovieAdapter (var nowPlayingMoviesList: ArrayList<PopularMoviesResponseModel.Results>, val context: Context) : RecyclerView.Adapter<NowPlayingMovieAdapter.PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = PostViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.now_playing_movie_item, parent, false)
    )
    override fun getItemCount() = nowPlayingMoviesList.size
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(nowPlayingMoviesList[position],context)
    }
    inner class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imgNowPlayingMovie = view.imgNowPlayingMovie
        fun bind(popularMovieModel: PopularMoviesResponseModel.Results, context: Context) {
            setDefaultImage(popularMovieModel.poster_path,imgNowPlayingMovie,R.mipmap.ic_launcher)
        }
    }

    fun setDefaultImage(imagePath: String?, imageView: ImageView, placeHolder: Int) {
        val fullUrl = "https://image.tmdb.org/t/p/original".plus(imagePath)
        Glide.with(context).load(fullUrl)
            .apply(
                RequestOptions().centerCrop().error(placeHolder)
                    .fitCenter().placeholder(placeHolder)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
            ).into(imageView)

    }
}