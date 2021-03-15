package com.example.pmacademyandroid_metelov_m28_hw22.data

import com.example.pmacademyandroid_metelov_m28_hw22.datasource.model.AddedFrom
import com.example.pmacademyandroid_metelov_m28_hw22.datasource.model.UserPostData
import com.example.pmacademyandroid_metelov_m28_hw22.datasource.model.UserPostResponse
import com.example.pmacademyandroid_metelov_m28_hw22.tools.orZero
import javax.inject.Inject

class PostResponseToPostDbEntityMapper @Inject constructor() {

    fun map(usersPostsResponseList: List<UserPostResponse>): List<UserPostData> {
        return usersPostsResponseList.map {
            UserPostData(it.userId.orZero(), it.id.orZero(), it.title, it.body, AddedFrom.SERVER)
        }
    }

}