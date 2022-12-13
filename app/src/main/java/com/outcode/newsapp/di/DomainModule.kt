package com.outcode.newsapp.di

import android.annotation.SuppressLint
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


/**
 * Created by Ayush Shrestha$ on 2022/12/12$.
 */
@SuppressLint("VisibleForTests")
@Module(
    includes = [
        RepositoryModule::class,

    ]
)
@InstallIn(SingletonComponent::class)
class DomainModule