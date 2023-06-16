package com.example.rxjavaandrxandroid.data.github.model


import com.google.gson.annotations.SerializedName

data class GithubEventDto(
    val actor: Actor,
    @SerializedName("created_at")  val createdAt: String,
    val id: String,
    @SerializedName("public") val isPublic: Boolean,
    val repo: Repo,
    val type: String
)