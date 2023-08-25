package com.example.rxjavaandrxandroid.api

import com.example.rxjavaandrxandroid.models.Post
import io.reactivex.Single
import retrofit2.http.GET


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version TypicodeApi, v 0.1 Thu 7/27/2023 3:52 PM by Houwen Lie
 */
interface TypicodeApi {

    @GET("/posts/1")
    fun getPostDetail(): Single<Post>
}