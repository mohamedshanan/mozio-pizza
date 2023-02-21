package com.shannan.moziopizza.feature.flavors.domain.usecase

import com.shannan.moziopizza.base.domain.result.Result
import com.shannan.moziopizza.feature.flavors.data.repository.FlavorsRepositoryImpl
import com.shannan.moziopizza.feature.flavors.domain.DomainFixtures
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class GetPizzaFlavorsUseCaseTest {

    private val mockAlbumRepository: FlavorsRepositoryImpl = mockk()

    private val cut = GetFlavorListUseCase(mockAlbumRepository)

    @Test
    fun `return list of flavors`() {
        // given
        val flavors = listOf(DomainFixtures.getFlavors(), DomainFixtures.getFlavors())
        coEvery { mockAlbumRepository.getFlavors() } returns Result.Success(flavors)

        // when
        val actual = runBlocking { cut() }

        // then
        actual shouldBeEqualTo Result.Success(flavors)
    }

    @Test
    fun `WHEN onEnter is called with no value then the default query search term is null`() = runBlocking {
        // given
        val flavors = listOf(DomainFixtures.getFlavors(), DomainFixtures.getFlavors())
        coEvery { mockAlbumRepository.getFlavors() } returns Result.Success(flavors)

        cut()

        coVerify { mockAlbumRepository.getFlavors() }
    }

    @Test
    fun `return error when repository throws an exception`() {
        // given
        val resultFailure = mockk<Result.Failure>()
        coEvery { mockAlbumRepository.getFlavors() } returns resultFailure

        // when
        val actual = runBlocking { cut() }

        // then
        actual shouldBeEqualTo resultFailure
    }
}
