package com.example.calculation2.ui.theme.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.calculation2.ui.theme.screens.models.HomeEvent
import com.example.calculation2.ui.theme.screens.views.HomeViewInit

@Composable
fun HomeScreen(viewModel: HomeViewModel) {

    val viewState = viewModel.viewStates().collectAsState()

    with(viewState.value) {
        HomeViewInit(
            viewState = this,
            onChangeTheme = {
                viewModel.obtainEvent(HomeEvent.ChangeTheme(it))
            },
            onChangeExpression = {
                viewModel.obtainEvent(HomeEvent.ChangeExpression(it))
            },
            onCalculateExpression = {
                viewModel.obtainEvent(HomeEvent.CalculateExpression)
            },
            onClearExpression = {
                viewModel.obtainEvent(HomeEvent.ClearExpression)
            },
            onRemoveLastSymbol = {
                viewModel.obtainEvent(HomeEvent.RemoveLastSymbol)
            }
        )
    }
}