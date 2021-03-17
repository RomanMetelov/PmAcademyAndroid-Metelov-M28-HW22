package com.example.pmacademyandroidMetelovM28Hw22.domain.newPost

enum class NewPostErrorType {
    BODY_LENGTH_MIN_ERROR,
    BODY_LENGTH_MAX_ERROR,
    TITLE_LENGTH_MIN_ERROR,
    TITLE_LENGTH_MAX_ERROR,
    FORBIDDEN_WORDS_ERROR
}

sealed class VerificationStatus<out T> {
    object Normal : VerificationStatus<Nothing>()
    data class Error<T>(val errors: T) : VerificationStatus<T>()
}
