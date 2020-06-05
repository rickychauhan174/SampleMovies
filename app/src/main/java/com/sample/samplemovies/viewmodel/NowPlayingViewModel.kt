package com.sample.samplemovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.samplemovies.model.PopularMoviesResponseModel
import com.sample.samplemovies.retrofit.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NowPlayingViewModel : ViewModel() {
    var nowPlayingLiveData: MutableLiveData<PopularMoviesResponseModel>? = null

    fun getNowPlayingMovies(): MutableLiveData<PopularMoviesResponseModel> {
        nowPlayingLiveData = MutableLiveData()
        val nowPlayingMovieUrl = "now_playing?language=en-US&page=undefined&api_key=55957fcf3ba81b137f8fc01ac5a31fb5"
        val retrofitCall = RetrofitService.retrofitApiRef().nowPlayingMoviesList(nowPlayingMovieUrl)
        retrofitCall.enqueue(object : Callback<PopularMoviesResponseModel> {

            override fun onResponse(
                call: Call<PopularMoviesResponseModel>,
                response: Response<PopularMoviesResponseModel>
            ) {
                if (response.isSuccessful) {
                    nowPlayingLiveData!!.value = response.body()
                } else {
                    nowPlayingLiveData!!.value = null
                }
            }

            override fun onFailure(call: Call<PopularMoviesResponseModel>, t: Throwable?) {
                nowPlayingLiveData!!.value = null
            }
        })
        return nowPlayingLiveData!!
    }
}