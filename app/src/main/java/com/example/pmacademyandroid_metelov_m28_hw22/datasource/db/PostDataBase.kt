package com.example.pmacademyandroid_metelov_m28_hw22.datasource.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pmacademyandroid_metelov_m28_hw22.datasource.model.UserPostData

@Database(entities = [UserPostData::class], version = PostDataBase.VERSION)
abstract class PostDataBase : RoomDatabase() {
    companion object {
        const val VERSION = 1
    }

    abstract fun getPostsDao(): PostsDao
}