package com.shannan.moziopizza.feature.album.domain.usecase

import com.shannan.moziopizza.base.domain.result.Result
import com.shannan.moziopizza.feature.album.data.repository.FlavorsRepositoryImpl
import com.shannan.moziopizza.feature.album.domain.DomainFixtures
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class GetAlbumListUseCaseTest {

    private val mockAlbumRepository: FlavorsRepositoryImpl = mockk()

    private val cut = GetAlbumListUseCase(mockAlbumRepository)

    @Test
    fun `return list of albums`() {
        // given
        val albums = listOf(DomainFixtures.getAlbum(), DomainFixtures.getAlbum())
        coEvery { mockAlbumRepository.searchAlbum(any()) } returns Result.Success(albums)

        // when
        val actual = runBlocking { cut(null) }

        // then
        actual shouldBeEqualTo Result.Success(albums)
    }

    @Test
    fun `WHEN onEnter is called with no value then the default query search term is null`() = runBlocking {
        // given
        val albums = listOf(DomainFixtures.getAlbum(), DomainFixtures.getAlbum())
        coEvery { mockAlbumRepository.searchAlbum(any()) } returns Result.Success(albums)

        cut(null)

        coVerify { mockAlbumRepository.searchAlbum(null) }
    }

    @Test
    fun `filter albums with default image`() {
        // given
        val albumWithImage = DomainFixtures.getAlbum()
        val albumWithoutImage = DomainFixtures.getAlbum(images = listOf())
        val albums = listOf(albumWithImage, albumWithoutImage)
        coEvery { mockAlbumRepository.searchAlbum(any()) } returns Result.Success(albums)

        // when
        val actual = runBlocking { cut(null) }

        // then
        actual shouldBeEqualTo Result.Success(listOf(albumWithImage))
    }

    @Test
    fun `return error when repository throws an exception`() {
        // given
        val resultFailure = mockk<Result.Failure>()
        coEvery { mockAlbumRepository.searchAlbum(any()) } returns resultFailure

        // when
        val actual = runBlocking { cut(null) }

        // then
        actual shouldBeEqualTo resultFailure
    }
}