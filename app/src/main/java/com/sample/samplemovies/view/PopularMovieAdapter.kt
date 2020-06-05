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
import kotlinx.android.synthetic.main.popular_movie_item.view.*
import kotlin.collections.ArrayList

class PopularMovieAdapter (var movieList: ArrayList<PopularMoviesResponseModel.Results>, val context: Context) : RecyclerView.Adapter<PopularMovieAdapter.PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = PostViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.popular_movie_item, parent, false)
    )
    override fun getItemCount() = movieList.size
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(movieList[position],context)
    }
    inner class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle = view.tvTitle
        private val tvDate = view.tvDate
        private val pbRating = view.pbRating
        private val tvProgress = view.tvProgress
        private val imgPopularMovie = view.imgPopularMovie
        fun bind(popularMovieModel: PopularMoviesResponseModel.Results, context: Context) {
            tvTitle.text = popularMovieModel.title
            tvDate.text = popularMovieModel.release_date
            val rating = (popularMovieModel.vote_average!! * 10).toInt()
            if(rating < 50){
                pbRating.progressDrawable.setTint(Color.YELLOW)
            }else{
                pbRating.progressDrawable.setTint(Color.GREEN)
            }
            pbRating.progress = rating
            tvProgress.text = rating.toString()
            setDefaultImage(popularMovieModel.poster_path,imgPopularMovie,R.mipmap.ic_launcher)
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