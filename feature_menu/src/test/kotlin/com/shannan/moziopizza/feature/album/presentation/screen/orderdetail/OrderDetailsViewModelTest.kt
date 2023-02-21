package com.shannan.moziopizza.feature.album.presentation.screen.orderdetail

import com.shannan.moziopizza.feature.album.presentation.screen.orderdetail.OrderDetailsViewModel.UiState
import com.shannan.moziopizza.library.testutils.CoroutinesTestDispatcherExtension
import com.shannan.moziopizza.library.testutils.InstantTaskExecutorExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(InstantTaskExecutorExtension::class, CoroutinesTestDispatcherExtension::class)
class OrderDetailsViewModelTest {



    private val cut = OrderDetailsViewModel()

    @Test
    @Disabled("mockk can't correctly mock this function https://github.com/mockk/mockk/issues/957")
    fun `onEnter order details is not found`() = runTest {
        // given
        val firstFlavorName = "Pepperoni"
        val firstFlavorPrice = 15.0f
        val secondFlavorName = "Super cheese"
        val secondFlavorPrice = 11.0f

        val mockAlbumDetailFragmentArgs = OrderDetailsFragmentArgs(firstFlavorName, firstFlavorPrice, secondFlavorName, secondFlavorPrice)

        // when
        cut.onEnter(mockAlbumDetailFragmentArgs)

        // then
        cut.uiStateFlow.value shouldBeEqualTo UiState.Error
    }
}
