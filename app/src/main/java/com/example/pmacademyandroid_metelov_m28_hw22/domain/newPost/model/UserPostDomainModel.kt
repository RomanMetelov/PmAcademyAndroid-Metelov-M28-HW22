package com.example.pmacademyandroid_metelov_m28_hw22.domain.newPost.model

import com.example.pmacademyandroid_metelov_m28_hw22.datasource.model.AddedFrom
import com.example.pmacademyandroid_metelov_m28_hw22.domain.PostStatus

data class UserPostDomainModel(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    val postStatus: PostStatus,
    val addedFrom: AddedFrom
)