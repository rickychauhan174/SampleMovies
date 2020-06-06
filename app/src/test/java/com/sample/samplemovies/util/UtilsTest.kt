package com.sample.samplemovies.util

import org.junit.Test

import org.junit.Assert.*

class UtilsTest {

    @Test
    fun getReleaseDateFormat() {
        val releaseDate = Utils.getReleaseDateFormat("2020-06-06")
        assertEquals("June 06, 2020", releaseDate)
    }

    @Test
    fun getReleaseDateFormatTwoDigitDate() {
        val releaseDate = Utils.getReleaseDateFormat("2020-12-16")
        assertEquals("December 16, 2020", releaseDate)
    }

    @Test
    fun getFormattedMovieTime() {
        val duration = Utils.getFormattedMovieTime(65)
        assertEquals("1h 5m", duration)
    }

    @Test
    fun getFormattedMovieTimeOnlyHour() {
        val duration = Utils.getFormattedMovieTime(60)
        assertEquals("1h", duration)
    }

    @Test
    fun getFormattedMovieTimeOnlyMinute() {
        val duration = Utils.getFormattedMovieTime(50)
        assertEquals("50m", duration)
    }
}