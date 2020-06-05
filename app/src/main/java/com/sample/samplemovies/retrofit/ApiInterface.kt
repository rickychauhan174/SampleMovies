package com.sample.samplemovies.retrofit

import com.sample.samplemovies.model.PopularMoviesResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {
    @GET
    fun moviesList(@Url moviesUrl: String): Call<PopularMoviesResponseModel>

    @GET
    fun movieDetails(@Url movieDetailsUrl: String): Call<PopularMoviesResponseModel.Results>
}