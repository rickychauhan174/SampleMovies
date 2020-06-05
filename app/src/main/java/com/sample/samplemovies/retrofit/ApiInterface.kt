package com.sample.samplemovies.retrofit

import com.sample.samplemovies.model.PopularMoviesResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {
    @GET
    fun popularMoviesList(@Url popularMoviesUrl: String): Call<PopularMoviesResponseModel>

    @GET
    fun nowPlayingMoviesList(@Url nowPlayingMoviesUrl: String): Call<PopularMoviesResponseModel>
}