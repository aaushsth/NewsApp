package com.outcode.newsapp.data.handler

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response


suspend fun <T> Response<T>.handleResponse(doActionOnSuccess: suspend (body: T) -> Unit = {}): Resource<T> {
    loggerE("Error:${this.raw()}")

    return if (isSuccessful) {
        if (body() != null) {
            doActionOnSuccess.invoke(body()!!)
            Resource.Success(body()!!)
        } else {
            Resource.Error(message())
        }
    } else if (code() in listOf(401)) {
        //App.instance.forceLogout()
        Resource.Error(message())
    } else {
        loggerE("Error:${this.raw()}")

        val genericErrorMessage = "Error encountered. Please try again later."

        val errorBody = errorBody()?.string()

        return try {
            //Parse: {"message":{"Incorrect otp"}}
            //Parse: {"detail":{"Incorrect otp"}}
            //Parse: {"errors":["Incorrect otp","Error 2"]}
            //Parse: {"errors":["Incorrect otp","Error 2"]}
            val errorModel = Gson().fromJson(errorBody, BaseErrorEntity::class.java)
            Resource.Error(
                errorModel.message ?: errorModel.detail?: errorModel.errors
                ?: errorModel.nonFieldErrors?.get(0)?: errorModel.nickName?.get(0) ?: throw Exception()
            )

        } catch (e: Exception) {
            //Parse: {password:This field may not be blank.}
            val type = object : TypeToken<Map<String, String>>() {}.type

            val data: Map<String, String> = Gson().fromJson(errorBody, type)

            return if (!data.isNullOrEmpty()) {
                val firstEntry = data.entries.iterator().next()
                val errorMessage = firstEntry.value
                if (errorMessage.isNullOrEmpty()) {
                    Resource.Error(message())
                } else {
                    //Display first message from the message list in given key
                    Resource.Error(errorMessage)
                }
            } else {
                Resource.Error(message())
            }
        } catch (e: Exception) {
            Log.e("ResponseHandler", "Second Exception")
            //Parse: ["User with this email already exists"]*/
            val stringArrayType = object : TypeToken<List<String>>() {}.type
            val arrayMessages: List<String> = Gson().fromJson(errorBody, stringArrayType)
            return if (!arrayMessages.isNullOrEmpty()) {
                Resource.Error(arrayMessages[0])
            } else {
                Resource.Error(message())
            }
        } catch (e: Exception) {
            Log.e("ResponseHandler", "third Exception")
            //Parse: {"password":["This field may not be blank."]}
            val type = object : TypeToken<Map<String, List<String>>>() {}.type
            val data: Map<String, List<String>> = Gson().fromJson(errorBody, type)
            return if (!data.isNullOrEmpty()) {
                val firstEntry = data.entries.iterator().next()
                val errorMessage = firstEntry.value
                if (errorMessage.isNullOrEmpty()) {
                    Resource.Error(message())
                } else {
                    //Display first message from the message list in given key
                    Resource.Error(errorMessage.first())
                }
            } else {
                Resource.Error(message())
            }
        } catch (e: Exception) {
            Resource.Error(genericErrorMessage)
        }
    }
}

inline fun <T> doTryCatch(task: () -> Resource<T>): Resource<T> {
    return try {
        task.invoke()
    } catch (e: Exception) {
        Resource.Error(e.generateMessage())
    }
}

inline fun tryIgnoreCatch(task: () -> Unit) {
    try {
        task.invoke()
    } catch (e: Exception) {
    }
}

/*
{
    "errors": [
    { "key1":"value1" },
    { "key1":"value1" },
    { "key1":"value1" }
    ]
}*/
