package com.sample.samplemovies.view

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.samplemovies.R
import com.sample.samplemovies.databinding.ActivityMainBinding
import com.sample.samplemovies.model.PopularMoviesResponseModel
import com.sample.samplemovies.viewmodel.NowPlayingViewModel
import com.sample.samplemovies.viewmodel.PopularMoviesViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private var popularLayoutManager: LinearLayoutManager? = null
    private var currentPage = 1
    private var totalPageCount: Int? = 0
    private var popularMoviesList: ArrayList<PopularMoviesResponseModel.Results>? = null
    private var popularMoviesAdapter: PopularMovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        mBinding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        popularLayoutManager = LinearLayoutManager(this)
        mBinding.rvPopularMovies.layoutManager = popularLayoutManager
        mBinding.rvNowPlaying.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        getNowPlayingMovies()
        getPopularMovies()
    }

    private fun getPopularMovies() {
        mBinding.progressBar.visibility = View.VISIBLE
        val viewModel = ViewModelProvider(this).get(PopularMoviesViewModel::class.java)
        viewModel.getPopularMovies(currentPage)
            .observe(this, Observer { response ->
                mBinding.progressBar.visibility = View.GONE
                if (response != null) {
                    totalPageCount = response.total_pages
                    popularMoviesList = response.results!!
                    popularMoviesAdapter =
                        PopularMovieAdapter(popularMoviesList!!, this@MainActivity)
                    mBinding.rvPopularMovies.adapter = popularMoviesAdapter
                    setPagination()
                }
            })
    }

    private fun getNowPlayingMovies() {
        val viewModel = ViewModelProvider(this).get(NowPlayingViewModel::class.java)
        viewModel.getNowPlayingMovies()
            .observe(this, Observer { response ->
                if (response != null) {
                    val postAdapter = NowPlayingMovieAdapter(response.results!!, this@MainActivity)
                    mBinding.rvNowPlaying.adapter = postAdapter
                }
            })
    }

    private fun setPagination() {
        mBinding.rvPopularMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = popularLayoutManager!!.childCount
                val totalItemCount = popularLayoutManager!!.itemCount
                val firstVisibleItemPosition = popularLayoutManager!!.findFirstVisibleItemPosition()
                if ((currentPage <= totalPageCount!!) && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
                    currentPage++
                    loadMoreItems()
                }
            }
        })
    }

    private fun loadMoreItems() {

        val viewModel = ViewModelProvider(this).get(PopularMoviesViewModel::class.java)
        viewModel.getPopularMovies(currentPage)
            .observe(this, Observer { response ->
                if (response != null) {
                    popularMoviesList!!.addAll(response.results!!)
                    popularMoviesAdapter!!.notifyDataSetChanged()
                }
            })
    }
}

