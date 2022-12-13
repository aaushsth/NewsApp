package com.outcode.newsapp.data.handler

import org.json.JSONObject
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Exception.generateMessage(): String {
    val internetNotAvailableErrorMessage ="internetNotAvailableErrorMessage"
    val cannotConnectErrorMessage = "cannotConnectErrorMessage"
    val requestTimeOutErrorMessage = "Server request time out. Please try again later."
    val genericErrorMessage = "Error encountered. Please try again later."
    return try {
        when (this) {
            is UnknownHostException -> internetNotAvailableErrorMessage
            is java.net.ConnectException -> cannotConnectErrorMessage
            is SocketTimeoutException -> requestTimeOutErrorMessage
            is HttpException ->
                try {
                    val responseBody = response()?.errorBody()
                    val jsonObject = JSONObject(responseBody?.string()!!)
                    if (jsonObject.has("message")) {
                        jsonObject.optString("message")
                    } else {
                        genericErrorMessage
                    }
                } catch (e: Exception) {
                    genericErrorMessage
                }
            else -> genericErrorMessage
        }
    } catch (e: Exception) {
        genericErrorMessage
    }
}
