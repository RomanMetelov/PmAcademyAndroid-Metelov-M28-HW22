package com.example.pmacademyandroid_metelov_m28_hw22.domain

import com.example.pmacademyandroid_metelov_m28_hw22.data.PostsInfoRepository
import com.example.pmacademyandroid_metelov_m28_hw22.datasource.model.AddedFrom
import com.example.pmacademyandroid_metelov_m28_hw22.domain.model.UserPostDomainModel
import io.reactivex.Flowable
import javax.inject.Inject

class GetAllPostsUseCase @Inject constructor(
    private val postRepository: PostsInfoRepository
) {

    fun invoke(): Flowable<List<UserPostDomainModel>> =
        postRepository.getPostsFromLocalStorage().map(::sort)

    private fun sort(startList: List<UserPostDomainModel>): List<UserPostDomainModel> {
        val postsGroupedByAddedFrom = startList.groupBy { it.addedFrom }.withDefault { emptyList() }

        var listFromServer = postsGroupedByAddedFrom.getValue(AddedFrom.SERVER)
        listFromServer = listFromServer.sortedBy { it.id }

        var listFromUser = postsGroupedByAddedFrom.getValue(AddedFrom.USER)
        listFromUser = listFromUser.sortedByDescending { it.id }

        return listFromUser + listFromServer
    }
}