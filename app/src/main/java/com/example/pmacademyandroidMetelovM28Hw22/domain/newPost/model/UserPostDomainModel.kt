package com.example.pmacademyandroidMetelovM28Hw22.domain.newPost.model

import com.example.pmacademyandroidMetelovM28Hw22.datasource.model.AddedFrom
import com.example.pmacademyandroidMetelovM28Hw22.domain.PostStatus

data class UserPostDomainModel(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    val postStatus: PostStatus,
    val addedFrom: AddedFrom
)
