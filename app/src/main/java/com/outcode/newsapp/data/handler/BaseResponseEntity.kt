package com.outcode.newsapp.data.handler

import com.google.gson.annotations.SerializedName

open class BaseResponseEntity(
    @SerializedName("message")
    open var message: String? = null,
    @SerializedName("detail")
    open var detail: String? = null,
    @SerializedName("error_code")
    var errorCode: String? = null,
    @SerializedName("is_hidden")
    var isHidden: Boolean = false,
)

open class BaseErrorEntity(
    @SerializedName("message")
    open var message: String? = null,
    @SerializedName("detail")
    open var detail: String? = null,
    @SerializedName("non_field_errors")
    val nonFieldErrors: List<String>? = null,
    @SerializedName("nick_name")
    val nickName: List<String>? = null,
    @SerializedName("error")
    val errors: String?,
    @SerializedName("status")
    var status: Int,
    @SerializedName("data")
    var data: List<Any>? = null

    /*,@SerializedName("email")
    var email: List<String> = emptyList(),
    @SerializedName("registration_id")
    var registrationId: List<String> = emptyList(),
    @SerializedName("code")
    val code: String? = null*/
)

data class Errors(
    @SerializedName("errors")
    val errors: Any?
)