package com.example.pmacademyandroidMetelovM28Hw22.presentation

import androidx.annotation.ColorInt

sealed class PostUiModel {
    abstract val postId: Int
}

data class StandardPostUiModel(
        override val postId: Int,
        val userId: String,
        val title: String,
        val body: String,
        val hasWarning: Boolean,
        val colors: PostColors
) : PostUiModel()

data class BannedUserPostUiModel(
        override val postId: Int,
        val userId: Int
) : PostUiModel()

data class PostColors(
        @ColorInt val backgroundColor: Int
)
