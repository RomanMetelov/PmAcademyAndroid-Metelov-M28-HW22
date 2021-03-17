package com.example.pmacademyandroidMetelovM28Hw22.domain.feed

import com.example.pmacademyandroidMetelovM28Hw22.data.PostsInfoRepository
import com.example.pmacademyandroidMetelovM28Hw22.datasource.model.AddedFrom
import com.example.pmacademyandroidMetelovM28Hw22.di.IoDispatcher
import com.example.pmacademyandroidMetelovM28Hw22.domain.newPost.model.UserPostDomainModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllSortedPostsUseCase @Inject constructor(
    private val postRepository: PostsInfoRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    operator fun invoke(): Flow<List<UserPostDomainModel>> =
        postRepository.getPostsFromLocalStorage().map(::sort).flowOn(ioDispatcher)

    private fun sort(startList: List<UserPostDomainModel>): List<UserPostDomainModel> {
        val postsGroupedByAddedFrom = startList.groupBy { it.addedFrom }.withDefault { emptyList() }

        var listFromServer = postsGroupedByAddedFrom.getValue(AddedFrom.SERVER)
        listFromServer = listFromServer.sortedBy { it.id }

        var listFromUser = postsGroupedByAddedFrom.getValue(AddedFrom.USER)
        listFromUser = listFromUser.sortedByDescending { it.id }

        return listFromUser + listFromServer
    }
}
