package com.example.pmacademyandroid_metelov_m28_hw22.datasource

import com.example.pmacademyandroid_metelov_m28_hw22.data.StatusUser
import com.example.pmacademyandroid_metelov_m28_hw22.domain.Status
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserStatusLocalDataSource @Inject constructor() {

    private val statusSet: MutableSet<StatusUser> = mutableSetOf()

    private fun setupHardCode() {
        statusSet.add(StatusUser(3, Status.WITH_WARNING))
        statusSet.add(StatusUser(4, Status.WITH_WARNING))
        statusSet.add(StatusUser(7, Status.BANNED))
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