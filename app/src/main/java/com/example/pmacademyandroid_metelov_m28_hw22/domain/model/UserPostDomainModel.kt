package com.example.pmacademyandroid_metelov_m28_hw22.domain.model

import com.example.pmacademyandroid_metelov_m28_hw22.datasource.model.AddedFrom
import com.example.pmacademyandroid_metelov_m28_hw22.domain.Status

data class UserPostDomainModel(
        val userId: Int,
        val id: Int,
        val title: String,
        val body: String,
        val status: Status,
        val addedFrom: AddedFrom
)