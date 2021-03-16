package com.example.pmacademyandroid_metelov_m28_hw22.datasource

import com.example.pmacademyandroid_metelov_m28_hw22.datasource.model.StatusUser
import com.example.pmacademyandroid_metelov_m28_hw22.domain.PostStatus
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserStatusLocalDataSource @Inject constructor() {

    private val statusSet: MutableSet<StatusUser> = mutableSetOf()

    private fun setupHardCode() {
        statusSet.add(StatusUser(3, PostStatus.WITH_WARNING))
        statusSet.add(StatusUser(4, PostStatus.WITH_WARNING))
        statusSet.add(StatusUser(7, PostStatus.BANNED))
    }

    fun addStatusUser(statusUser: StatusUser) = statusSet.add(statusUser)

    fun removeStatusUser(statusUser: StatusUser) = statusSet.remove(statusUser)

    fun getSetOfStatusUser(): Set<StatusUser> {
        return statusSet
    }

    init {
        setupHardCode()
    }
}