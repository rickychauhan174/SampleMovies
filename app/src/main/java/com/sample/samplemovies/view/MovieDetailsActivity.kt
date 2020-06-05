package com.sample.samplemovies.view

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.samplemovies.R
import com.sample.samplemovies.databinding.ActivityDetailsBinding
import com.sample.samplemovies.util.ImageUtils
import com.sample.samplemovies.util.Utils
import com.sample.samplemovies.viewmodel.MovieDetailsViewModel

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        mBinding.rvGenre.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        getMovieDetails()
        mBinding.imgDetailsBack.setOnClickListener {
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
                    mBinding.tvDateDuration.text =
                        Utils.getReleaseDateFormat(response.release_date!!).plus(" - ").plus(
                            Utils.getFormattedMovieTime(response.runtime!!)
                        )
                    mBinding.tvOverviewText.text = response.overview
                    ImageUtils.setMovieImage(
                        response.poster_path,
                        mBinding.imgMovieDetails,
                        R.mipmap.ic_launcher,
                        this
                    )
                    val postAdapter = GenreAdapter(response.genres!!, this)
                    mBinding.rvGenre.adapter = postAdapter
                }
            })
    }
}

