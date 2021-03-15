package com.example.pmacademyandroid_metelov_m28_hw22.data

import com.example.pmacademyandroid_metelov_m28_hw22.datasource.api.PostsReposApi
import com.example.pmacademyandroid_metelov_m28_hw22.datasource.db.PostsDao
import com.example.pmacademyandroid_metelov_m28_hw22.domain.model.NewPostModel
import com.example.pmacademyandroid_metelov_m28_hw22.domain.model.UserPostDomainModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsInfoRepository @Inject constructor(
    private val infoApiService: PostsReposApi,
    private val postsCacheDataSource: PostsDao,
    private val toDbMapper: PostResponseToPostDbEntityMapper,
    private val domainUserPostMapper: DomainUserPostMapper,
    private val mapNewPostToDataPostModel: NewPostToDataPostMapper
) {

    fun getPostsFromLocalStorage(): Flowable<List<UserPostDomainModel>> {
        return postsCacheDataSource.getAllUsersFromDB().map(domainUserPostMapper::map)
    }

    fun updateLocalStorage(): Completable {
        return Completable.create { emitter ->
            infoApiService.getPostsList()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                    { listUserPostResponse ->
                        toDbMapper.map(listUserPostResponse).forEach {
                            postsCacheDataSource.insertPost(it)
                        }
                        emitter.onComplete()
                    },
                    {
                        emitter.onError(it)
                    }
                )
        }
    }


    private fun getNewPostId(): Int {
        return postsCacheDataSource.getMaxPostId() + 1
    }

    fun saveNewPostFromUser(postForSaving: NewPostModel) {
        postsCacheDataSource.insertPost(
            mapNewPostToDataPostModel.map(
                postForSaving,
                getNewPostId()
            )
        )
    }
}

