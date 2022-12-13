package com.outcode.newsapp.data.handler

/**
 * A generic class that contains data and status about loading this data.
 */
sealed class Resource<out R>{
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val errorMessage: String, val showRetry: Boolean = true) : Resource<Nothing>()
}
