package com.example.rxjavaandrxandroid.di

import com.example.rxjavaandrxandroid.data.nasa.NasaApi
import retrofit2.Retrofit.Builder


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version ApiModule, v 0.1 Wed 6/14/2023 4:42 PM by Houwen Lie
 */
object ApiModule {

    fun provideNasaApi(builder: Builder): NasaApi {
        return builder.baseUrl("TODO -> Nasa Base URL")
            .build()
            .create(NasaApi::class.java)
    }
}