package com.outcode.newsapp.ui.news

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.outcode.newsapp.data.handler.Resource
import com.outcode.newsapp.data.handler.Resource.Error
import com.outcode.newsapp.data.handler.loggerE
import com.outcode.newsapp.data.response.AllNewsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Ayush Shrestha$ on 2022/11/24$.
 */

@HiltViewModel
class NewsViewModel @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: NewsRepository
) : ViewModel() {


    private val _activeNewsUiState = MutableLiveData<ArticleListUiState>().apply {
        value = ArticleListUiState()
    }
    val activeNewsUiState: LiveData<ArticleListUiState> = _activeNewsUiState

    init {
        loggerE("here ")
        getArticlesByCategory()
    }

    private fun getArticlesByCategory(
        page: Int = 1
    ) {
        viewModelScope.launch {
            val activeState = ArticleListUiState()
            setLoadingState(activeState)
            when (val result = repository.getAllNews(page)) {
                is Resource.Success -> {
                    setSuccessState(activeState, result.data)
                }
                is Error -> {
                    setErrorState(
                        activeState,
                        Resource.Error(result.errorMessage, result.showRetry)
                    )
                }
            }
        }
    }

    private fun setErrorState(activeState: ArticleListUiState, error: Error) {
        _activeNewsUiState.value = activeState.copy(
            isLoading = false,
            list = null,
            error = error
        )
    }

    private fun setSuccessState(activeState: ArticleListUiState, data: AllNewsResponse) {
        _activeNewsUiState.value = activeState.copy(
            isLoading = false,
            list = data.articles,
            error = null
        )
    }

    private fun setLoadingState(activeState: ArticleListUiState) {
        _activeNewsUiState.value = activeState.copy(isLoading = true)
    }
}