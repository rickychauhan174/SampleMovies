package com.sample.samplemovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.samplemovies.model.PopularMoviesResponseModel
import com.sample.samplemovies.retrofit.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsViewModel : ViewModel() {
    var detailsLiveData: MutableLiveData<PopularMoviesResponseModel.Results>? = null

    fun getMovieDetails(movieId: String): MutableLiveData<PopularMoviesResponseModel.Results> {
        detailsLiveData = MutableLiveData()
        val nowPlayingMovieUrl = movieId.plus("?api_key=55957fcf3ba81b137f8fc01ac5a31fb5")
        val retrofitCall = RetrofitService.retrofitApiRef().movieDetails(nowPlayingMovieUrl)
        retrofitCall.enqueue(object : Callback<PopularMoviesResponseModel.Results> {

            override fun onResponse(
                call: Call<PopularMoviesResponseModel.Results>,
                response: Response<PopularMoviesResponseModel.Results>
            ) {
                if (response.isSuccessful) {
                    detailsLiveData!!.value = response.body()
                } else {
                    detailsLiveData!!.value = null
                }
            }

            override fun onFailure(call: Call<PopularMoviesResponseModel.Results>, t: Throwable?) {
                detailsLiveData!!.value = null
            }
        })
        return detailsLiveData!!
    }
}