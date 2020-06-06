package com.sample.samplemovies.view.details

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.samplemovies.R
import com.sample.samplemovies.model.PopularMoviesResponseModel
import kotlinx.android.synthetic.main.genre_item.view.*

class GenreAdapter(
    var genreList: ArrayList<PopularMoviesResponseModel.Genre>,
    val context: Context
) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = GenreViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.genre_item, parent, false)
    )

    override fun getItemCount() = genreList.size
    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(genreList[position], context)
    }

    inner class GenreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvGenre = view.tvGenre

        fun bind(genreModel: PopularMoviesResponseModel.Genre, context: Context) {
            tvGenre.text = genreModel.name
        }
    }
}
