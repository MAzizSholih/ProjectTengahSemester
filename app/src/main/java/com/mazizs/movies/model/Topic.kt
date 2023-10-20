package com.mazizs.movies.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    val id: Int,
    @StringRes val name: Int,
    val description: Int,
    val imdbRating: Float,
    @DrawableRes val imageRes: Int
)
