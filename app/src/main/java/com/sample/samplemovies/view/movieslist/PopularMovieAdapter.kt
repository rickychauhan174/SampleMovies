package com.sample.samplemovies.view.movieslist

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.samplemovies.R
import com.sample.samplemovies.model.PopularMoviesResponseModel
import com.sample.samplemovies.util.ImageUtils
import com.sample.samplemovies.util.Utils
import com.sample.samplemovies.view.details.MovieDetailsActivity
import kotlinx.android.synthetic.main.popular_movie_item.view.*

class PopularMovieAdapter(
    var movieList: ArrayList<PopularMoviesResponseModel.Results>,
    val context: Context
) : RecyclerView.Adapter<PopularMovieAdapter.PopularViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = PopularViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.popular_movie_item, parent, false)
    )

    override fun getItemCount() = movieList.size
    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.bind(movieList[position], context)
    }

    inner class PopularViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle = view.tvTitle
        private val tvDate = view.tvDate
        private val pbRating = view.pbRating
        private val tvProgress = view.tvProgress
        private val imgPopularMovie = view.imgPopularMovie
        private val popularItemView = view.popularItemView
        fun bind(popularMovieModel: PopularMoviesResponseModel.Results, context: Context) {
            tvTitle.text = popularMovieModel.title
            tvDate.text = Utils.getReleaseDateFormat(popularMovieModel.release_date!!)
            val rating = (popularMovieModel.vote_average!! * 10).toInt()
            if (rating < 50) {
                pbRating.progressDrawable.setTint(context.resources.getColor(R.color.yellow, null))
            } else {
                pbRating.progressDrawable.setTint(Color.GREEN)
            }
            pbRating.progress = rating
            tvProgress.text = rating.toString()
            ImageUtils.setMovieImage(
                popularMovieModel.poster_path,
                imgPopularMovie,
                R.mipmap.ic_launcher,
                context
            )
            popularItemView.setOnClickListener {
                val intent = Intent(context, MovieDetailsActivity::class.java)
                intent.putExtra("movieId", popularMovieModel.id.toString())
                context.startActivity(intent)
            }
        }
    }
}
