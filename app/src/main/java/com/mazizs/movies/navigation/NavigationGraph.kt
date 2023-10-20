package com.mazizs.movies.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mazizs.movies.DetailMovie
import com.mazizs.movies.HomeScreen

//Fungsi komponen Composable dalam Jetpack Compose di bawah ini merupakan fungsi NavigationGraph yaitu untuk menghubungkan berbagai tampilan
@Composable
fun NavigationGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(
            route = Screen.Home.route,
        ) {
            HomeScreen(
                navigationToDetail = { movie ->
                    navController.navigate(Screen.Detail.createRoute(movie))
                }
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("movieData") { type = NavType.IntType }),
        ) {
            val id = it.arguments?.getInt("movieData") ?: -1
            DetailMovie(
                movieData = id,
                navigateBack = {
                    navController.navigateUp()
                },
            )
        }
    }
}