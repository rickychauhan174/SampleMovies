package com.sample.samplemovies

import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun getReleaseDateFormat(inputDate: String) : String{
        val formatterInput = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = formatterInput.parse(inputDate)
        val formatterOutput = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
        val dateOutput = formatterOutput.format(date!!)
        return dateOutput.toString()
    }

    fun getFormattedMovieTime(inputTime: Int) : String{
        val hours: Int = inputTime / 60
        val minutes: Int = inputTime % 60
        return hours.toString().plus("h").plus(" ").plus(minutes).plus("m")
    }
}