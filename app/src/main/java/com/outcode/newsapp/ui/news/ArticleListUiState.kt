package com.outcode.newsapp.ui.news

import com.outcode.newsapp.data.handler.Resource
import com.outcode.newsapp.data.response.Article


/**
 * Created by Ayush Shrestha$ on 2022/11/25$.
 */
data class ArticleListUiState(
    val isLoading: Boolean = true,
    val list: List<Article>? = emptyList(),
    val error: Resource.Error? = null
)

data class ArticleDetailUiState(
    val isLoading: Boolean = true,
    val list: Article? = null,
    val error: Resource.Error? = null
)

sealed interface BaseViewState<out T> {
    object Loading : BaseViewState<Nothing>
    object Empty : BaseViewState<Nothing>
    data class Data<T>(val value: T) : BaseViewState<T>
    data class Error(val throwable: Throwable) : BaseViewState<Nothing>
}
