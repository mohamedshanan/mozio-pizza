package com.shannan.moziopizza.feature.album.presentation.screen.orderdetail

import androidx.compose.runtime.Immutable
import com.shannan.moziopizza.base.presentation.viewmodel.BaseAction
import com.shannan.moziopizza.base.presentation.viewmodel.BaseState
import com.shannan.moziopizza.base.presentation.viewmodel.BaseViewModel
import com.shannan.moziopizza.feature.album.presentation.screen.orderdetail.OrderDetailsViewModel.Action
import com.shannan.moziopizza.feature.album.presentation.screen.orderdetail.OrderDetailsViewModel.Action.AlbumLoadFailure
import com.shannan.moziopizza.feature.album.presentation.screen.orderdetail.OrderDetailsViewModel.Action.AlbumLoadSuccess
import com.shannan.moziopizza.feature.album.presentation.screen.orderdetail.OrderDetailsViewModel.UiState
import com.shannan.moziopizza.feature.album.presentation.screen.orderdetail.OrderDetailsViewModel.UiState.Content
import com.shannan.moziopizza.feature.album.presentation.screen.orderdetail.OrderDetailsViewModel.UiState.Loading

internal class OrderDetailsViewModel : BaseViewModel<UiState, Action>(Loading) {
    fun onEnter(args: OrderDetailsFragmentArgs?) {
        val action = args?.let {
            val firstHalfPrice: Float = args.firstFlavorPrice / 2.0f
            val secondHalfPrice: Float = args.secondFlavorPrice / 2.0f
            val total = firstHalfPrice.plus(secondHalfPrice)

            AlbumLoadSuccess(
                args.firstFlavorName,
                firstHalfPrice.toString(),
                args.secondFlavorName,
                secondHalfPrice.toString(),
                total.toString()
            )
        } ?: run {
            AlbumLoadFailure
        }

        sendAction(action)
    }

    internal sealed interface Action : BaseAction<UiState> {
        class AlbumLoadSuccess(
            private val firstFlavorName: String,
            private val firstFlavorPrice: String,
            private val secondFlavorName: String,
            private val secondFlavorPrice: String,
            private val total: String
        ) : Action {
            override fun reduce(state: UiState) = Content(
                firstFlavorName = firstFlavorName,
                firstFlavorPrice = firstFlavorPrice,
                secondFlavorName = secondFlavorName,
                secondFlavorPrice = secondFlavorPrice,
                total = total,
            )
        }

        object AlbumLoadFailure : Action {
            override fun reduce(state: UiState) = UiState.Error
        }
    }

    @Immutable
    internal sealed interface UiState : BaseState {
        data class Content(
            val firstFlavorName: String = "",
            val firstFlavorPrice: String = "",
            val secondFlavorName: String = "",
            val secondFlavorPrice: String = "",
            val total: String = "",
        ) : UiState

        object Loading : UiState
        object Error : UiState
    }
}
