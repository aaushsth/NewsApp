package com.outcode.newsapp.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.outcode.newsapp.BuildConfig
import com.outcode.newsapp.data.api.NewsApiService
import com.outcode.newsapp.data.createChuckInterceptor
import com.outcode.newsapp.data.createHttpLoggingInterceptor
import com.outcode.newsapp.data.createOkHttpClient
import com.outcode.newsapp.data.createRetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton


 const val BASE_URL = BuildConfig.BASE_URL

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return createHttpLoggingInterceptor(isDev = BuildConfig.DEBUG)
    }

    @Provides
    @Singleton
    fun provideChuckInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        return createChuckInterceptor(context)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        chuckerInterceptor: ChuckerInterceptor,
    ): OkHttpClient {
        return createOkHttpClient(
            isCache = true,
            interceptors = arrayOf(
                httpLoggingInterceptor,
                chuckerInterceptor,
            ),
            context = context
        )
    }

    @Provides
    @Singleton
    fun provideNewsService(
        okHttpClient: OkHttpClient,
    ) = createRetrofitClient<NewsApiService>(
        okHttpClient = okHttpClient,
    )
}