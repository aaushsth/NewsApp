package com.outcode.newsapp.ui.navigation

sealed class MainScreens(val route:String){
    object  HomeScreen : MainScreens("main_screen")
    object  DetailScreen : MainScreens("detail_screen")

    fun withArgs(vararg  args :String):String{
        return  buildString {
            append(route)
            args.forEach {arg->
                append("/$arg") }
        }
    }
}