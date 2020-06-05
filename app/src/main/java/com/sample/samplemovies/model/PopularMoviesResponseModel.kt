package com.sample.samplemovies.model

class PopularMoviesResponseModel {
    val page: Int? = 0
    val total_results: Int? = 0
    val total_pages: String? = null
    val results: ArrayList<Results>? = null

    class Results {
        val popularity: Float? = 0f
        val vote_count: Int? = 0
        val video: Boolean? = false
        val poster_path: String? = null
        val id: Int? = 0
        val adult: Boolean? = false
        val backdrop_path: String? = null
        val original_language: String? = null
        val original_title: String? = null
        val genre_ids: Array<Int>? = null
        val title: String? = null
        val vote_average: Float? = 0f
        val overview: String? = null
        val release_date: String? = null
    }
}