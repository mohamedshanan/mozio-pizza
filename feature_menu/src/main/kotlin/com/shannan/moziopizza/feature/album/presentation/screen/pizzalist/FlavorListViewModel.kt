package com.shannan.moziopizza.feature.album.presentation.screen.pizzalist

import androidx.compose.runtime.Immutable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.shannan.moziopizza.base.domain.result.Result
import com.shannan.moziopizza.base.presentation.nav.NavManager
import com.shannan.moziopizza.base.presentation.viewmodel.BaseAction
import com.shannan.moziopizza.base.presentation.viewmodel.BaseState
import com.shannan.moziopizza.base.presentation.viewmodel.BaseViewModel
import com.shannan.moziopizza.feature.album.data.datasource.api.model.Flavor
import com.shannan.moziopizza.feature.album.domain.usecase.GetFlavorListUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class FlavorListViewModel(
    private val state: SavedStateHandle,
    private val navManager: NavManager,
    private val getFlavorListUseCase: GetFlavorListUseCase,
) : BaseViewModel<FlavorListViewModel.UiState, FlavorListViewModel.Action>(UiState.Loading) {

    init {
        getFlavorsList()
    }

    private var job: Job? = null
    private lateinit var firstFlavor: Flavor
    private lateinit var secondFlavor: Flavor

    private fun getFlavorsList() {
        if (job != null) {
            job?.cancel()
            job = null
        }

        job = viewModelScope.launch {
            getFlavorListUseCase().also { result ->
                val action = when (result) {
                    is Result.Success -> {
                        if (result.value.isEmpty()) {
                            Action.FlavorsListLoadFailure
                        } else {
                            firstFlavor = result.value[0]
                            secondFlavor = result.value[0]
                            Action.FlavorListLoadSuccess(result.value)
                        }
                    }
                    is Result.Failure -> {
                        Action.FlavorsListLoadFailure
                    }
                }
                sendAction(action)
            }
        }
    }

    fun completeOrderClicked() {
        val navDirections =
            PizzaFlavorsFragmentDirections.actionAlbumListToAlbumDetail(firstFlavor.name, firstFlavor.price, secondFlavor.name, secondFlavor.price)

        navManager.navigate(navDirections)
    }

    fun setFirstFlavor(flavor: Flavor) {
        firstFlavor = flavor
    }

    fun setSecondFlavor(flavor: Flavor) {
        secondFlavor = flavor
    }

    internal sealed interface Action : BaseAction<UiState> {
        class FlavorListLoadSuccess(private val flavors: List<Flavor>) : Action {
            override fun reduce(state: UiState) = UiState.Content(flavors)
        }

        class OrderDetailsSuccess(private val flavors: List<Flavor>) : Action {
            override fun reduce(state: UiState) = UiState.Content(flavors)
        }

        object FlavorsListLoadFailure : Action {
            override fun reduce(state: UiState) = UiState.Error
        }
    }

    @Immutable
    internal sealed interface UiState : BaseState {
        data class Content(val flavors: List<Flavor>) : UiState
        object Loading : UiState
        object Error : UiState
    }
}
