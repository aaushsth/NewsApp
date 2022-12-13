package com.outcode.newsapp.ui.news

import com.outcode.newsapp.BuildConfig
import com.outcode.newsapp.data.api.NewsApiService
import com.outcode.newsapp.data.handler.Resource
import com.outcode.newsapp.data.handler.doTryCatch
import com.outcode.newsapp.data.handler.handleResponse
import com.outcode.newsapp.data.handler.loggerE
import com.outcode.newsapp.data.response.AllNewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Ayush Shrestha$ on 2022/11/23$.
 */
interface NewsRepository {
    suspend fun getAllNews(page: Int): Resource<AllNewsResponse>
}

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val apiService: NewsApiService,
) : NewsRepository {
    override suspend fun getAllNews(page: Int): Resource<AllNewsResponse> {
        return withContext(Dispatchers.Default) {
            doTryCatch {
                apiService.getAllNews(
                    query = "everything",
                    page = 1,
                    pageSize = 100,
                    apiKey = BuildConfig.API_KEY
                ).handleResponse()

            }
        }
    }
}