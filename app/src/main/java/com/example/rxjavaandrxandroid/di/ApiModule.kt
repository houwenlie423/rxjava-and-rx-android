package com.example.rxjavaandrxandroid.di

import com.example.rxjavaandrxandroid.data.github.GithubApi
import com.example.rxjavaandrxandroid.data.nasa.NasaApi
import com.example.rxjavaandrxandroid.shared.UrlUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import javax.inject.Singleton


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version ApiModule, v 0.1 Wed 6/14/2023 4:42 PM by Houwen Lie
 */
@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideGithubApi(builder: Retrofit.Builder): GithubApi {
        return builder.baseUrl(UrlUtil.GITHUB_API_BASE_URL)
            .build()
            .create(GithubApi::class.java)
    }

    fun provideNasaApi(builder: Builder): NasaApi {
        return builder.baseUrl("TODO -> Nasa Base URL")
            .build()
            .create(NasaApi::class.java)
    }
}