package com.outcode.newsapp.di

import android.annotation.SuppressLint
import com.outcode.newsapp.data.api.NewsApiService
import com.outcode.newsapp.ui.news.NewsRepository
import com.outcode.newsapp.ui.news.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Created by Ayush Shrestha$ on 2022/11/24$.
 */



@SuppressLint("VisibleForTests")
@Module
@InstallIn(SingletonComponent::class)
 class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
        apiService :NewsApiService
    ): NewsRepository = NewsRepositoryImpl(apiService)

}