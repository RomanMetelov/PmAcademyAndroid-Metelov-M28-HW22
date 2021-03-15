package com.example.pmacademyandroid_metelov_m28_hw22.datasource.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.pmacademyandroid_metelov_m28_hw22.datasource.db.Converter

@TypeConverters(Converter::class)
@Entity
data class UserPostData(
    val userId: Int,
    @PrimaryKey
    val id: Int,
    val title: String,
    val body: String,
    val addedFrom: AddedFrom
)

enum class AddedFrom{
    USER,
    SERVER
}