package com.example.pmacademyandroid_metelov_m28_hw22.domain

import com.example.pmacademyandroid_metelov_m28_hw22.data.PostsInfoRepository
import com.example.pmacademyandroid_metelov_m28_hw22.datasource.model.AddedFrom
import com.example.pmacademyandroid_metelov_m28_hw22.domain.model.UserPostDomainModel
import javax.inject.Inject

class GetAllPostsUseCase @Inject constructor(
        private val postRepository: PostsInfoRepository,
) {
    fun invoke(): List<UserPostDomainModel> {

        val postsGroupedByAddedFrom =
            postRepository.getPostsFromLocalStorage().groupBy { it.addedFrom }.withDefault { emptyList() }

        var listFromServer = postsGroupedByAddedFrom.getValue(AddedFrom.SERVER)
        listFromServer = listFromServer.sortedBy { it.id }

        var listFromUser = postsGroupedByAddedFrom.getValue(AddedFrom.USER)
        listFromUser = listFromUser.sortedByDescending { it.id }

        return listFromUser + listFromServer
    }
}