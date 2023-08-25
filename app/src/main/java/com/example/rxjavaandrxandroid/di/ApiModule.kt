package com.example.rxjavaandrxandroid.di

import com.example.rxjavaandrxandroid.api.TypicodeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version ApiModule, v 0.1 Wed 6/14/2023 4:42 PM by Houwen Lie
 */
@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideTypicodeApi(): TypicodeApi {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(TypicodeApi::class.java)
    }
}