package com.example.calculation2.ui.theme.screens.models

import com.example.calculation2.base.UiEvent

sealed class HomeEvent: UiEvent {
    //Изменить тему приложения - светлая / темная;
    data class ChangeTheme(val newValue: Boolean) : HomeEvent()
    //Изменить математическое выражение - добавить число, мат. операцию или скобки;
    data class ChangeExpression(val newValue: ExpressionItem) : HomeEvent()
    //Вычислить математическое выражение;
    data object CalculateExpression : HomeEvent()
    //Удалить последний введенный символ;
    data object RemoveLastSymbol : HomeEvent()
    //Очистить введенное математическое выражение.
    data object ClearExpression : HomeEvent()
}