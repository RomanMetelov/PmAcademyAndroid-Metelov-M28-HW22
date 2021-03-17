package com.example.pmacademyandroidMetelovM28Hw22.data

import com.example.pmacademyandroidMetelovM28Hw22.datasource.UserStatusLocalDataSource
import com.example.pmacademyandroidMetelovM28Hw22.datasource.model.UserPostData
import com.example.pmacademyandroidMetelovM28Hw22.domain.newPost.model.UserPostDomainModel
import com.example.pmacademyandroidMetelovM28Hw22.domain.PostStatus
import javax.inject.Inject

class DomainUserPostMapper @Inject constructor(private val statusList: UserStatusLocalDataSource) {

    fun map(
        postData: List<UserPostData>,
    ): List<UserPostDomainModel> {
        return postData.let {
            it.map { userPostResponse ->
                val userStatusById = getStatusFromStatusSet(userPostResponse.userId)
                getUserPostDomainModel(userPostResponse, userStatusById)
            }
        }
    }

    private fun getStatusFromStatusSet(userId: Int): PostStatus {
        return statusList.getSetOfStatusUser().find { it.idUser == userId }?.postStatus
            ?: PostStatus.STANDARD
    }

    private fun getUserPostDomainModel(
        userPostData: UserPostData,
        postStatus: PostStatus
    ): UserPostDomainModel {
        return UserPostDomainModel(
            userId = userPostData.userId,
            id = userPostData.id,
            title = userPostData.title,
            body = userPostData.body,
            postStatus = postStatus,
            addedFrom = userPostData.addedFrom
        )
    }
}
