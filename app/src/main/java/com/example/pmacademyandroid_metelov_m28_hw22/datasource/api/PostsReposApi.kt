package com.example.pmacademyandroid_metelov_m28_hw22.datasource.api

import com.example.pmacademyandroid_metelov_m28_hw22.datasource.model.UserPostResponse
import retrofit2.Response
import retrofit2.http.GET

interface PostsReposApi {
    @GET("/posts")
    suspend fun getPostsList(): Response<List<UserPostResponse>>
}