package com.sample.samplemovies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.samplemovies.R
import com.sample.samplemovies.databinding.ActivityMainBinding
import com.sample.samplemovies.viewmodel.NowPlayingViewModel
import com.sample.samplemovies.viewmodel.PopularMoviesViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        mBinding.rvPopularMovies.layoutManager = LinearLayoutManager(this)
        mBinding.rvNowPlaying.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        getNowPlayingMovies()
        getPopularMovies()
    }

    private fun getPopularMovies() {
        mBinding.progressBar.visibility = View.VISIBLE
        val viewModel = ViewModelProvider(this).get(PopularMoviesViewModel::class.java)
        viewModel.getPopularMovies(1)
            .observe(this, Observer { response ->
                mBinding.progressBar.visibility = View.GONE
                if (response != null) {
                    val postAdapter = PopularMovieAdapter(response.results!!, this@MainActivity)
                    mBinding.rvPopularMovies.adapter = postAdapter
                }
            })
    }

    private fun getNowPlayingMovies() {
        val viewModel = ViewModelProvider(this).get(NowPlayingViewModel::class.java)
        viewModel.getNowPlayingMovies()
            .observe(this, Observer { response ->
                mBinding.progressBar.visibility = View.GONE
                if (response != null) {
                    val postAdapter = NowPlayingMovieAdapter(response.results!!, this@MainActivity)
                    mBinding.rvNowPlaying.adapter = postAdapter
                }
            })
    }
}
