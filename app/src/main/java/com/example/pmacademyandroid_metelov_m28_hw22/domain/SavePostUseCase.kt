package com.example.pmacademyandroid_metelov_m28_hw22.domain

import com.example.pmacademyandroid_metelov_m28_hw22.data.PostsInfoRepository
import com.example.pmacademyandroid_metelov_m28_hw22.datasource.model.AddedFrom
import com.example.pmacademyandroid_metelov_m28_hw22.datasource.model.UserPostData
import com.example.pmacademyandroid_metelov_m28_hw22.domain.model.NewPostModel
import javax.inject.Inject

class SavePostUseCase @Inject constructor(
    private val repository: PostsInfoRepository
) {
    fun savePost(postForSaving: NewPostModel) {
        repository.putNewPost(mapNewPostToDataPostModel(postForSaving))
    }

    private fun mapNewPostToDataPostModel(postForSaving: NewPostModel) =
        UserPostData(
            555,
            repository.getNewPostId(),
            postForSaving.title,
            postForSaving.body,
            AddedFrom.USER
        )

}