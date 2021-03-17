package com.example.pmacademyandroidMetelovM28Hw22

import com.example.pmacademyandroidMetelovM28Hw22.data.AndroidResourceRepository
import com.example.pmacademyandroidMetelovM28Hw22.datasource.model.AddedFrom
import com.example.pmacademyandroidMetelovM28Hw22.domain.PostStatus
import com.example.pmacademyandroidMetelovM28Hw22.domain.newPost.model.UserPostDomainModel
import com.example.pmacademyandroidMetelovM28Hw22.presentation.BannedUserPostUiModel
import com.example.pmacademyandroidMetelovM28Hw22.presentation.PostUiMapper
import com.example.pmacademyandroidMetelovM28Hw22.presentation.PostUiModel
import com.example.pmacademyandroidMetelovM28Hw22.presentation.StandardPostUiModel
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class DomainToUiMapperTest {

    private val id = 1
    private val userId = 1
    private val title = "title"
    private val body = "body"
    private val normalColor = 1
    private val warningColor = 2
    private val mockResourceRepository = mockk<AndroidResourceRepository> {
        every { getColor(R.color.white) } returns normalColor
        every { getColor(R.color.warning_post_bg) } returns warningColor
    }

    @Test
    fun `map domain post to ui post`() {

        val mapper = PostUiMapper(mockResourceRepository)

        val domainPostsList = listOf(
            UserPostDomainModel(userId, id, title, body, PostStatus.STANDARD, AddedFrom.USER),
            UserPostDomainModel(userId, id, title, body, PostStatus.WITH_WARNING, AddedFrom.USER),
            UserPostDomainModel(userId, id, title, body, PostStatus.BANNED, AddedFrom.USER)
        )

        val actualPostList = mapper.map(domainPostsList)

        val examplePostsList = listOf(
            StandardPostUiModel(id, userId.toString(), title, body, false, normalColor),
            StandardPostUiModel(id, userId.toString(), title, body, true, warningColor),
            BannedUserPostUiModel(id, userId)
        )

        assertEquals(examplePostsList, actualPostList)
    }
}