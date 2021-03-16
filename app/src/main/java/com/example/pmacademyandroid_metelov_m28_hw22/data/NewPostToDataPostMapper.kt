package com.example.pmacademyandroid_metelov_m28_hw22.data

import javax.inject.Inject
import com.example.pmacademyandroid_metelov_m28_hw22.datasource.model.AddedFrom
import com.example.pmacademyandroid_metelov_m28_hw22.datasource.model.UserPostData
import com.example.pmacademyandroid_metelov_m28_hw22.domain.newPost.model.NewPostModel

class NewPostToDataPostMapper @Inject constructor() {

    fun map(postForSaving: NewPostModel, id: Int): UserPostData {
        return UserPostData(
            0,
            id,
            postForSaving.title,
            postForSaving.body,
            AddedFrom.USER
        )
    }

}