package com.example.pmacademyandroidMetelovM28Hw22.datasource.api

import com.example.pmacademyandroidMetelovM28Hw22.datasource.model.UserPostResponse
import retrofit2.Response
import retrofit2.http.GET

interface PostsReposApi {
    @GET("/posts")
    suspend fun getPostsList(): Response<List<UserPostResponse>>
}
