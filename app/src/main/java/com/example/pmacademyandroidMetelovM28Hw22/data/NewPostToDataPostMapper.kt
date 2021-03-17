package com.example.pmacademyandroidMetelovM28Hw22.data

import javax.inject.Inject
import com.example.pmacademyandroidMetelovM28Hw22.datasource.model.AddedFrom
import com.example.pmacademyandroidMetelovM28Hw22.datasource.model.UserPostData
import com.example.pmacademyandroidMetelovM28Hw22.domain.newPost.model.NewPostModel

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
