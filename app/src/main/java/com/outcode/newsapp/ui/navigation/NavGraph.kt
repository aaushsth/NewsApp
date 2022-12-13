package com.outcode.newsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.outcode.newsapp.ui.HomeScreen
import com.outcode.newsapp.ui.news.DetailedNewsScreen
import com.outcode.newsapp.ui.news.NewsViewModel


/**
 * Created by Ayush Shrestha$ on 2022/12/13$.
 */
@Composable
fun SetNavGraph(navController: NavHostController,viewModel: NewsViewModel) {
    NavHost(navController = navController, startDestination = MainScreens.HomeScreen.route){
        composable(route = MainScreens.HomeScreen.route){
            HomeScreen(viewModel,navController)
        }
        composable(route = MainScreens.DetailScreen.route){
            DetailedNewsScreen(navController,viewModel)

        }
    }
}
