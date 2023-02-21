package com.shannan.moziopizza.feature.album.presentation.screen.orderdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.fragment.navArgs
import com.shannan.moziopizza.base.presentation.activity.BaseFragment
import com.shannan.moziopizza.base.presentation.compose.composable.DataNotFoundAnim
import com.shannan.moziopizza.base.presentation.compose.composable.ProgressIndicator
import com.shannan.moziopizza.feature.album.R
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.navigation.koinNavGraphViewModel

internal class OrderDetailsFragment : BaseFragment() {
    private val args: OrderDetailsFragmentArgs by navArgs()
    private val model: OrderDetailsViewModel by koinNavGraphViewModel(R.id.albumNavGraph)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        model.onEnter(args)

        return ComposeView(requireContext()).apply {
            setContent {
                OrderDetailsScreen(uiStateFlow = model.uiStateFlow)
            }
        }
    }
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
private fun OrderDetailsScreen(uiStateFlow: StateFlow<OrderDetailsViewModel.UiState>) {
    val uiState: OrderDetailsViewModel.UiState by uiStateFlow.collectAsStateWithLifecycle()

    uiState.let {
        when (it) {
            OrderDetailsViewModel.UiState.Error -> DataNotFoundAnim()
            OrderDetailsViewModel.UiState.Loading -> ProgressIndicator()
            is OrderDetailsViewModel.UiState.Content -> {

                Column(
                    Modifier.fillMaxWidth().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "Order Details"
                    )

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Black)
                            .padding(1.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(it.firstFlavorName)
                        Spacer(Modifier.weight(1f))
                        Text(it.firstFlavorPrice)
                    }

                    Spacer(
                        modifier = Modifier
                            .background(Color.Transparent)
                            .padding(2.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(it.secondFlavorName)
                        Spacer(Modifier.weight(1f))
                        Text(it.secondFlavorPrice)
                    }


                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Black)
                            .padding(1.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text("Total")
                        Spacer(Modifier.weight(1f))
                        Text(it.total)
                    }

                }
            }
        }
    }
}