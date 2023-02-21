package com.shannan.moziopizza.feature.flavors.data.repository

import com.shannan.moziopizza.base.data.retrofit.ApiResult
import com.shannan.moziopizza.base.domain.result.Result
import com.shannan.moziopizza.feature.flavors.data.DataFixtures
import com.shannan.moziopizza.feature.flavors.data.datasource.api.model.Flavor
import com.shannan.moziopizza.feature.flavors.data.datasource.api.service.FlavorsRetrofitService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.net.UnknownHostException

class FlavorsRepositoryImplTest {

    private val mockService: FlavorsRetrofitService = mockk()

    private val cut = FlavorsRepositoryImpl(mockService)

    @Test
    fun `getPizzaFlavors handles api success and returns Flavors`() {
        // given
        val flavors = DataFixtures.getFlavorApiModel()

        coEvery { mockService.getPizzaFlavors() } returns ApiResult.Success(
            DataFixtures.ApiResponse.getFlavors(),
        )

        // when
        val actual = runBlocking { cut.getFlavors() }

        // then
        actual shouldBeEqualTo Result.Success(flavors)
    }

    @Test
    fun `searchAlbum handles api error `() {
        // given
        val phrase = "phrase"

        coEvery { mockService.getPizzaFlavors() } returns mockk<ApiResult.Error<List<Flavor>>>()

        // when
        val actual = runBlocking { cut.getFlavors() }

        // then
        actual shouldBeEqualTo Result.Failure()
    }

    @Test
    fun `getAlbumInfo handles api success and returns Album`() {
        // given
        val flavor = DataFixtures.getFlavorApiModel()

        coEvery {
            mockService.getPizzaFlavors()
        } returns ApiResult.Success(
            listOf(flavor),
        )

        // when
        val actual = runBlocking { cut.getFlavors() }

        // then
        actual shouldBeEqualTo Result.Success(flavor)
    }

    @Test
    fun `getAlbumInfo handles api error`() {
        // given
        coEvery {
            mockService.getPizzaFlavors()
        } returns mockk<ApiResult.Error<List<Flavor>>>()

        // when
        val actual = runBlocking { cut.getFlavors() }

        // then
        actual shouldBeEqualTo Result.Failure()
    }
}
