package com.sample.samplemovies.view

import android.content.Context
import android.content.Intent
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
import kotlin.collections.ArrayList

class NowPlayingMovieAdapter (var nowPlayingMoviesList: ArrayList<PopularMoviesResponseModel.Results>, val context: Context) : RecyclerView.Adapter<NowPlayingMovieAdapter.NowPlayingViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = NowPlayingViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.now_playing_movie_item, parent, false)
    )
    override fun getItemCount() = nowPlayingMoviesList.size
    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        holder.bind(nowPlayingMoviesList[position],context)
    }
    inner class NowPlayingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imgNowPlayingMovie = view.imgNowPlayingMovie
        fun bind(nowMovieModel: PopularMoviesResponseModel.Results, context: Context) {
            setDefaultImage(nowMovieModel.poster_path,imgNowPlayingMovie,R.mipmap.ic_launcher)
            imgNowPlayingMovie.setOnClickListener{
                val intent = Intent(context, MovieDetailsActivity::class.java)
                intent.putExtra("movieId",nowMovieModel.id.toString())
                context.startActivity(intent)
            }
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