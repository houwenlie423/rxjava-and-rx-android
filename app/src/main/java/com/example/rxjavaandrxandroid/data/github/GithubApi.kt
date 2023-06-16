package com.example.rxjavaandrxandroid.data.github

import android.database.Observable
import com.example.rxjavaandrxandroid.data.github.model.GithubEventDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path


/**
 * @author Houwen Lie (houwenlie98@gmail.com)
 * @version GithubApi, v 0.1 Wed 6/14/2023 4:40 PM by Houwen Lie
 */
interface GithubApi {

    @GET("repos/ReactiveX/{repo}/events")
    fun fetchEvents(
        @Path("repo") repo: String
    ): Observable<List<GithubEventDto>>
}