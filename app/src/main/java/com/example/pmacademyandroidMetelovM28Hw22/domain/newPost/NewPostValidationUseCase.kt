package com.example.pmacademyandroidMetelovM28Hw22.domain.newPost

import com.example.pmacademyandroidMetelovM28Hw22.data.PostsInfoRepository
import com.example.pmacademyandroidMetelovM28Hw22.domain.newPost.model.NewPostModel
import javax.inject.Inject

class NewPostValidationUseCase @Inject constructor(
    private val postsInfoRepository: PostsInfoRepository,
    private val newPostValidation: NewPostVerification
) {

    suspend operator fun invoke(postForSaving: NewPostModel): VerificationStatus<Set<NewPostErrorType>> {
        val verificationStatus = newPostValidation.verify(postForSaving)

        if (verificationStatus == VerificationStatus.Normal) {
            postsInfoRepository.saveNewPostFromUser(postForSaving)
        }

        return verificationStatus
    }
}
