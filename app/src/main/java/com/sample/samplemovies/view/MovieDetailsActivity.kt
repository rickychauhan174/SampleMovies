package com.sample.samplemovies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.sample.samplemovies.R
import com.sample.samplemovies.databinding.ActivityDetailsBinding
import com.sample.samplemovies.databinding.ActivityMainBinding
import com.sample.samplemovies.viewmodel.MovieDetailsViewModel
import com.sample.samplemovies.viewmodel.NowPlayingViewModel

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        mBinding.rvGenre.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        getMovieDetails()
        mBinding.imgDetailsBack.setOnClickListener{
            onBackPressed()
        }
    }

    private fun getMovieDetails() {
        val movieId = intent.getStringExtra("movieId")
        val viewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)
        viewModel.getMovieDetails(movieId)
            .observe(this, Observer { response ->
                if (response != null) {
                    mBinding.tvTitleDetails.text = response.title
                    mBinding.tvDateDuration.text = response.release_date.plus(" - ").plus(response.runtime)
                    mBinding.tvOverviewText.text = response.overview
                    setDefaultImage(response.poster_path, mBinding.imgMovieDetails, R.mipmap.ic_launcher)
                    val postAdapter = GenreAdapter(response.genres!!, this)
                    mBinding.rvGenre.adapter = postAdapter
                }
            })
    }

    fun setDefaultImage(imagePath: String?, imageView: ImageView, placeHolder: Int) {
        val fullUrl = "https://image.tmdb.org/t/p/original".plus(imagePath)
        Glide.with(this).load(fullUrl)
            .apply(
                RequestOptions().centerCrop().error(placeHolder)
                    .fitCenter().placeholder(placeHolder)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
            ).into(imageView)

    }
}
