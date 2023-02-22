package com.shannan.moziopizza.feature.flavors.presentation.screen.pizzalist

import androidx.lifecycle.SavedStateHandle
import com.shannan.moziopizza.base.domain.result.Result
import com.shannan.moziopizza.base.presentation.nav.NavManager
import com.shannan.moziopizza.feature.flavors.data.datasource.api.model.Flavor
import com.shannan.moziopizza.feature.flavors.domain.usecase.GetFlavorListUseCase
import com.shannan.moziopizza.library.testutils.CoroutinesTestDispatcherExtension
import com.shannan.moziopizza.library.testutils.InstantTaskExecutorExtension
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(InstantTaskExecutorExtension::class, CoroutinesTestDispatcherExtension::class)
class FlavorsListViewModelTest {

    private val mockGetAlbumListUseCase: GetFlavorListUseCase = mockk()

    private val mockNavManager: NavManager = mockk(relaxed = true)

    private val savedStateHandle: SavedStateHandle = mockk(relaxed = true)

    private val cut = FlavorListViewModel(
        savedStateHandle,
        mockNavManager,
        mockGetAlbumListUseCase,
    )

    @Test
    fun `onEnter emits state loading`() = runTest {
        // given
        coEvery { mockGetAlbumListUseCase.invoke() } returns Result.Success(emptyList())

        // then
        advanceUntilIdle()

        cut.uiStateFlow.value shouldBeEqualTo FlavorListViewModel.UiState.Loading
    }

    @Test
    fun `onEnter emits state success`() = runTest {
        // given
        val firstFlavorName = "Pepperoni"
        val firstFlavorPrice = 15.0f
        val secondFlavorName = "Super cheese"
        val secondFlavorPrice = 11.0f
        val flavors = listOf(Flavor(firstFlavorName, firstFlavorPrice), Flavor(secondFlavorName, secondFlavorPrice))
        cut.onEnter()
        coEvery { mockGetAlbumListUseCase.invoke() } returns Result.Success(flavors)

        // then
        advanceUntilIdle()

        cut.uiStateFlow.value shouldBeEqualTo FlavorListViewModel.UiState.Content(
            flavors = flavors,
        )
    }

    @Test
    fun `onAlbumClick navigate to album detail`() {
        // given
        val firstFlavorName = "Pepperoni"
        val firstFlavorPrice = 15.0f
        val secondFlavorName = "Super cheese"
        val secondFlavorPrice = 11.0f

        val navDirections = PizzaFlavorsFragmentDirections.actionAlbumListToAlbumDetail(
            firstFlavorName,
            firstFlavorPrice,
            secondFlavorName,
            secondFlavorPrice
        )

        // when
        cut.setFirstFlavor(Flavor(firstFlavorName, firstFlavorPrice))
        cut.setSecondFlavor(Flavor(secondFlavorName, secondFlavorPrice))
        cut.completeOrderClicked()

        // then
        coVerify { mockNavManager.navigate(navDirections) }
    }
}
