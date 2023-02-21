package com.shannan.moziopizza.feature.flavors.presentation.screen.pizzalist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shannan.moziopizza.base.presentation.activity.BaseFragment
import com.shannan.moziopizza.base.presentation.compose.composable.DataNotFoundAnim
import com.shannan.moziopizza.base.presentation.compose.composable.ProgressIndicator
import com.shannan.moziopizza.feature.flavors.R
import com.shannan.moziopizza.feature.flavors.data.datasource.api.model.Flavor
import org.koin.androidx.navigation.koinNavGraphViewModel

class PizzaFlavorsFragment : BaseFragment() {

    private val model: FlavorListViewModel by koinNavGraphViewModel(R.id.albumNavGraph)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        model.onEnter()
        return ComposeView(requireContext()).apply {
            setContent {
                MenuScreen(model)
            }
        }
    }
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
private fun MenuScreen(viewModel: FlavorListViewModel) {
    val uiState: FlavorListViewModel.UiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()

    uiState.let {
        when (it) {
            FlavorListViewModel.UiState.Error -> DataNotFoundAnim()
            FlavorListViewModel.UiState.Loading -> ProgressIndicator()
            is FlavorListViewModel.UiState.Content -> {

                Column(modifier = Modifier.padding(12.dp)) {
                    Spacer(
                        modifier = Modifier
                            .background(Color.Transparent)
                            .padding(48.dp)
                    )
                    Spinner(
                        it.flavors,
                        it.flavors[0],
                        "Select first pizza"
                    ) {
                        viewModel.setFirstFlavor(it)
                    }

                    Spacer(
                        modifier = Modifier
                            .background(Color.Transparent)
                            .padding(24.dp)
                    )

                    Spinner(
                        it.flavors,
                        it.flavors[0],
                        "Select second pizza"
                    ) {
                        viewModel.setSecondFlavor(it)
                    }

                    Spacer(
                        modifier = Modifier
                            .background(Color.Transparent)
                            .padding(24.dp)
                    )

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        content = { Text("Complete the order") },
                        onClick = {
                            viewModel.completeOrderClicked()
                        })
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Spinner(
    list: List<Flavor>,
    preselected: Flavor,
    label: String,
    onSelectionChanged: (selection: Flavor) -> Unit
) {

    var selected by remember { mutableStateOf(preselected) }
    var expanded by remember { mutableStateOf(false) } // initial value

    Box {
        Column {
            OutlinedTextField(
                value = (selected.name),
                onValueChange = {},
                label = { Text(text = label) },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                trailingIcon = { Icon(Icons.Outlined.ArrowDropDown, null) },
                readOnly = true
            )
            DropdownMenu(
                modifier = Modifier.fillMaxWidth(),
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                list.forEach { entry ->

                    DropdownMenuItem(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            selected = entry
                            expanded = false
                            onSelectionChanged.invoke(entry)
                        },
                        text = {
                            Text(
                                text = (entry.name),
                                modifier = Modifier.wrapContentWidth().align(Alignment.Start)
                            )
                        }
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Transparent)
                .padding(10.dp)
                .clickable(
                    onClick = { expanded = !expanded }
                )
        )
    }
}
