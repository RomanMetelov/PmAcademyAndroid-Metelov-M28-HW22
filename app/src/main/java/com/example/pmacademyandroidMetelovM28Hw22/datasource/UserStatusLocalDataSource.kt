package com.example.pmacademyandroidMetelovM28Hw22.datasource

import com.example.pmacademyandroidMetelovM28Hw22.datasource.model.StatusUser
import com.example.pmacademyandroidMetelovM28Hw22.domain.PostStatus
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserStatusLocalDataSource @Inject constructor() {

    private val statusSet: MutableSet<StatusUser> = mutableSetOf()

    @Suppress("MagicNumber")
    private fun setupHardCode() {
        statusSet.add(StatusUser(3, PostStatus.WITH_WARNING))
        statusSet.add(StatusUser(4, PostStatus.WITH_WARNING))
        statusSet.add(StatusUser(5, PostStatus.BANNED))
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
