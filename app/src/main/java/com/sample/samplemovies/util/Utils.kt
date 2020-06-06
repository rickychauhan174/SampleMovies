package com.sample.samplemovies.util

import java.text.SimpleDateFormat
import java.util.*
import kotlin.time.Duration

object Utils {
    fun getReleaseDateFormat(inputDate: String): String {
        val formatterInput = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = formatterInput.parse(inputDate)
        val formatterOutput = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
        val dateOutput = formatterOutput.format(date!!)
        return dateOutput.toString()
    }

    fun getFormattedMovieTime(inputTime: Int): String {
        val hours: Int = inputTime / 60
        val minutes: Int = inputTime % 60
        var formattedTime = "--"
        if(hours > 0 && minutes <= 0){
            formattedTime = hours.toString().plus("h")
        }else if(hours <= 0 && minutes > 0){
            formattedTime = minutes.toString().plus("m")
        }else if(hours > 0 && minutes > 0){
            formattedTime =  hours.toString().plus("h").plus(" ").plus(minutes).plus("m")
        }
        return formattedTime
    }
}
