package com.example.calculation2.ui.theme.screens.models

import com.example.calculation2.base.UiAction

sealed class HomeAction: UiAction {
    data class ShowAlertDialog(val description: String) : HomeAction()
}