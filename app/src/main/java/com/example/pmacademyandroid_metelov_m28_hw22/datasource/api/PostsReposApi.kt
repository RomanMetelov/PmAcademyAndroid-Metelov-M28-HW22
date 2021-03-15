package com.example.pmacademyandroid_metelov_m28_hw22.datasource.api

import com.example.pmacademyandroid_metelov_m28_hw22.datasource.model.UserPostResponse
import io.reactivex.Single
import retrofit2.http.GET

interface PostsReposApi {
    @GET("/posts")
    fun getPostsList(): Single<List<UserPostResponse>>
}