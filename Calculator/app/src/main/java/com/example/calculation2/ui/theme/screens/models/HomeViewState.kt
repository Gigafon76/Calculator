package com.example.calculation2.ui.theme.screens.models

import com.example.calculation2.base.UiState

data class HomeViewState (
    val displayExpression: StringBuilder = StringBuilder(),// хранит текущее мат. выражение
    val privateExpression: StringBuilder = StringBuilder(),// хранит все предыдущие результаты + текущее мат. выражение
    val currentExpressionItem: ExpressionItem = ExpressionItem.None,// используется для предотвращения бесконечной последовательности мат.операций
    val expressionResult: String = "",// хранит результат текущего мат. выражения
    val isDarkTheme: Boolean = false
    ): UiState