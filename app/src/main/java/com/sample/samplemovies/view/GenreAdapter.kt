package com.sample.samplemovies.view

import android.content.Context
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
import kotlinx.android.synthetic.main.genre_item.view.*
import kotlinx.android.synthetic.main.now_playing_movie_item.view.*
import kotlin.collections.ArrayList

class GenreAdapter (var genreList: ArrayList<PopularMoviesResponseModel.Genre>, val context: Context) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = GenreViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.genre_item, parent, false)
    )
    override fun getItemCount() = genreList.size
    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(genreList[position],context)
    }
    inner class GenreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvGenre = view.tvGenre

        fun bind(genreModel: PopularMoviesResponseModel.Genre, context: Context) {
            tvGenre.text = genreModel.name
        }
    }
}