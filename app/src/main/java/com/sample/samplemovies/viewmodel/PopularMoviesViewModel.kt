package com.sample.samplemovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.samplemovies.model.PopularMoviesResponseModel
import com.sample.samplemovies.retrofit.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularMoviesViewModel : ViewModel() {
    var popularMoviesLiveData: MutableLiveData<PopularMoviesResponseModel>? = null

    fun getPopularMovies(page: Int): MutableLiveData<PopularMoviesResponseModel> {
        popularMoviesLiveData = MutableLiveData()
        val popularMovieUrl = "popular?api_key=55957fcf3ba81b137f8fc01ac5a31fb5&language=en-US&page=".plus(page)
        val retrofitCall = RetrofitService.retrofitApiRef().popularMoviesList(popularMovieUrl)
        retrofitCall.enqueue(object : Callback<PopularMoviesResponseModel> {

            override fun onResponse(
                call: Call<PopularMoviesResponseModel>,
                response: Response<PopularMoviesResponseModel>
            ) {
                if (response.isSuccessful) {
                    popularMoviesLiveData!!.value = response.body()
                } else {
                    popularMoviesLiveData!!.value = null
                }
            }

            override fun onFailure(call: Call<PopularMoviesResponseModel>, t: Throwable?) {
                popularMoviesLiveData!!.value = null
            }
        })
        return popularMoviesLiveData!!
    }
}