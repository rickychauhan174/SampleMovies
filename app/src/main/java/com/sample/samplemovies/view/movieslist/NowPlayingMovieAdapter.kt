package com.sample.samplemovies.view.movieslist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.samplemovies.R
import com.sample.samplemovies.model.PopularMoviesResponseModel
import com.sample.samplemovies.util.ImageUtils
import com.sample.samplemovies.view.details.MovieDetailsActivity
import kotlinx.android.synthetic.main.now_playing_movie_item.view.*

class NowPlayingMovieAdapter(
    var nowPlayingMoviesList: ArrayList<PopularMoviesResponseModel.Results>,
    val context: Context
) : RecyclerView.Adapter<NowPlayingMovieAdapter.NowPlayingViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = NowPlayingViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.now_playing_movie_item, parent, false)
    )

    override fun getItemCount() = nowPlayingMoviesList.size
    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        holder.bind(nowPlayingMoviesList[position], context)
    }

    inner class NowPlayingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imgNowPlayingMovie = view.imgNowPlayingMovie
        fun bind(nowMovieModel: PopularMoviesResponseModel.Results, context: Context) {
            ImageUtils.setMovieImage(
                nowMovieModel.poster_path,
                imgNowPlayingMovie,
                R.mipmap.ic_launcher,
                context
            )
            imgNowPlayingMovie.setOnClickListener {
                val intent = Intent(context, MovieDetailsActivity::class.java)
                intent.putExtra("movieId", nowMovieModel.id.toString())
                context.startActivity(intent)
            }
        }
    }
}
