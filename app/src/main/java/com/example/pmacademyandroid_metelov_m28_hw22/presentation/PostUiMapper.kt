package com.example.pmacademyandroid_metelov_m28_hw22.presentation

import com.example.pmacademyandroid_metelov_m28_hw22.R
import com.example.pmacademyandroid_metelov_m28_hw22.data.AndroidResourceRepository
import com.example.pmacademyandroid_metelov_m28_hw22.domain.model.UserPostDomainModel
import com.example.pmacademyandroid_metelov_m28_hw22.domain.Status
import javax.inject.Inject

class PostUiMapper @Inject constructor(private val resourceRepository: AndroidResourceRepository) {
    fun map(domainListModel: List<UserPostDomainModel>): List<PostUiModel> {
        return domainListModel.let(this::getPostUiModels)
    }

    private fun getPostUiModels(userPostDomainModel: List<UserPostDomainModel>): List<PostUiModel> {
        return userPostDomainModel.map {
            when (it.status) {
                Status.STANDARD -> {
                    getStandardPostUiModel(it)
                }
                Status.WITH_WARNING -> {
                    getStandardPostUiModel(it)
                }
                Status.BANNED -> {
                    getUserPostUiModelBanned(it)
                }
            }
        }
    }

    private fun getStandardPostUiModel(userPostDomainModel: UserPostDomainModel): StandardPostUiModel {
        val (backgroundColor, hasWarning) = when (userPostDomainModel.status) {
            Status.WITH_WARNING -> Pair(
                PostColors(resourceRepository.getColor(R.color.banned_post_bg)),
                true
            )
            else -> Pair(
                PostColors(resourceRepository.getColor(R.color.white)),
                false
            )
        }

        return StandardPostUiModel(
            postId = userPostDomainModel.id,
            userId = userPostDomainModel.userId.toString(),
            title = userPostDomainModel.title,
            body = userPostDomainModel.body,
            hasWarning = hasWarning,
            colors = backgroundColor
        )
    }

    private fun getUserPostUiModelBanned(userPostDomainModel: UserPostDomainModel): BannedUserPostUiModel {
        return BannedUserPostUiModel(
            postId = userPostDomainModel.id,
            userId = userPostDomainModel.userId
        )
    }

}