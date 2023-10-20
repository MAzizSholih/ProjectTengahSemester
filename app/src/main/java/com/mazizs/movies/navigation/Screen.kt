package com.mazizs.movies.navigation

//Untuk untuk mengatur navigasi aplikasi
sealed class Screen(
    val route: String
) {
    object Home : Screen(route = "home")
    object Detail : Screen(route = "home/{movieData}") {
        fun createRoute(movieData: Int) = "home/$movieData" //Untuk membuat rute dengan memasukkan ID film yang sesuai.
    }
}