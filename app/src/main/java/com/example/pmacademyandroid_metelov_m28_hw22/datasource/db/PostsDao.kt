package com.example.pmacademyandroid_metelov_m28_hw22.datasource.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pmacademyandroid_metelov_m28_hw22.datasource.model.UserPostData
import io.reactivex.Flowable

@Dao
interface PostsDao {

    @Query("SELECT * FROM UserPostData")
    fun getAllUsersFromDB(): Flowable<List<UserPostData>>

    @Query("SELECT MAX(id) FROM UserPostData")
    fun getMaxPostId(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(userPostData: UserPostData)

    @Insert
    fun insertAll(vararg userPostData: UserPostData)
}