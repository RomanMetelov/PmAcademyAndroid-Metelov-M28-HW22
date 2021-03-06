package com.example.pmacademyandroidMetelovM28Hw22.data

import com.example.pmacademyandroidMetelovM28Hw22.datasource.api.PostsReposApi
import com.example.pmacademyandroidMetelovM28Hw22.datasource.db.PostsDao
import com.example.pmacademyandroidMetelovM28Hw22.datasource.model.UserPostData
import com.example.pmacademyandroidMetelovM28Hw22.di.IoDispatcher
import com.example.pmacademyandroidMetelovM28Hw22.domain.newPost.model.NewPostModel
import com.example.pmacademyandroidMetelovM28Hw22.domain.newPost.model.UserPostDomainModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsInfoRepository @Inject constructor(
    private val infoApiService: PostsReposApi,
    private val postsCacheDataSource: PostsDao,
    private val toDbMapper: PostResponseToPostDbEntityMapper,
    private val domainUserPostMapper: DomainUserPostMapper,
    private val mapNewPostToDataPostModel: NewPostToDataPostMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    fun getPostsFromLocalStorage(): Flow<List<UserPostDomainModel>> {
        return postsCacheDataSource.getAllUsersFromDB().map(domainUserPostMapper::map).flowOn(ioDispatcher)
    }

    suspend fun updateLocalStorage() = withContext(ioDispatcher) {
        val response = infoApiService.getPostsList()
        if (response.isSuccessful) {
            response.body()?.let {
                val listToBd: List<UserPostData> = toDbMapper.map(it)
                postsCacheDataSource.insertListPosts(listToBd)
            }
        } else {
            throw IOException(response.errorBody().toString())
        }
    }

    private suspend fun getNewPostId(): Int {
        return withContext(ioDispatcher) {
            postsCacheDataSource.getMaxPostId() + 1
        }
    }

    suspend fun saveNewPostFromUser(postForSaving: NewPostModel) = withContext(ioDispatcher) {
        postsCacheDataSource.insertPost(
            mapNewPostToDataPostModel.map(
                postForSaving,
                getNewPostId()
            )
        )
    }
}
