package com.outcode.newsapp.data.response


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AllNewsResponse(
    @SerializedName("articles")
    var articles: List<Article>,
    @SerializedName("status")
    var status: String,
    @SerializedName("totalResults")
    var totalResults: Int
):Parcelable

@Parcelize
data class Article(
    @SerializedName("author")
    var author: String?,
    @SerializedName("content")
    var content: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("publishedAt")
    var publishedAt: String,
    @SerializedName("source")
    var source: Source,
    @SerializedName("title")
    var title: String,
    @SerializedName("url")
    var url: String,
    @SerializedName("urlToImage")
    var urlToImage: String?
):Parcelable

@Parcelize
data class Source(
    @SerializedName("id")
    var id: String,
    @SerializedName("name")
    var name: String
):Parcelable

