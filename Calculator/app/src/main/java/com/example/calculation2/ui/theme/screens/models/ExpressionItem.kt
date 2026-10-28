package com.example.calculation2.ui.theme.screens.models

sealed class ExpressionItem (val type: ExpressionItemType, val value: String){
    // Математические операции
    data object OperationMul: ExpressionItem(ExpressionItemType.Operation, "*")
    data object OperationDiv: ExpressionItem(ExpressionItemType.Operation, "/")
    data object OperationPlus: ExpressionItem(ExpressionItemType.Operation, "+")
    data object OperationMinus: ExpressionItem(ExpressionItemType.Operation, "-")
    data object OperationSqrt: ExpressionItem(ExpressionItemType.Operation, "√")
    data object OperationSqr: ExpressionItem(ExpressionItemType.Operation, "^")
    data object OperationPercent: ExpressionItem(ExpressionItemType.Operation, "%")

    // Круглые скобки

    data object LeftBracket: ExpressionItem(ExpressionItemType.Bracket, "(")
    data object RightBracket: ExpressionItem(ExpressionItemType.Bracket, ")")

    // Числа от 0 до 9, а также "."

    data object Value0: ExpressionItem(ExpressionItemType.Value, "0")
    data object Value1: ExpressionItem(ExpressionItemType.Value, "1")
    data object Value2: ExpressionItem(ExpressionItemType.Value, "2")
    data object Value3: ExpressionItem(ExpressionItemType.Value, "3")
    data object Value4: ExpressionItem(ExpressionItemType.Value, "4")
    data object Value5: ExpressionItem(ExpressionItemType.Value, "5")
    data object Value6: ExpressionItem(ExpressionItemType.Value, "6")
    data object Value7: ExpressionItem(ExpressionItemType.Value, "7")
    data object Value8: ExpressionItem(ExpressionItemType.Value, "8")
    data object Value9: ExpressionItem(ExpressionItemType.Value, "9")
    data object ValuePoint: ExpressionItem(ExpressionItemType.Value, ".")

    // Используется при инициализации мат. выражения

    data object None: ExpressionItem(ExpressionItemType.Empty, "")

    companion object{
        fun convertToExpression(value: String): ExpressionItem{
            return when(value){
                OperationMul.value -> OperationMul
                OperationDiv.value -> OperationDiv
                OperationPlus.value -> OperationPlus
                OperationMinus.value -> OperationMinus
                OperationSqrt.value -> OperationSqrt
                OperationSqr.value -> OperationSqr
                OperationPercent.value -> OperationPercent
                LeftBracket.value -> LeftBracket
                RightBracket.value -> RightBracket
                None.value -> None
                Value0.value -> Value0
                Value1.value -> Value1
                Value2.value -> Value2
                Value3.value -> Value3
                Value4.value -> Value4
                Value5.value -> Value5
                Value6.value -> Value6
                Value7.value -> Value7
                Value8.value -> Value8
                Value9.value -> Value9
                ValuePoint.value -> ValuePoint
                else -> throw Exception("Not found Expression with value")
            }
        }
    }
}
sealed class ExpressionItemType{
    data object Operation: ExpressionItemType()
    data object Bracket: ExpressionItemType()
    data object Value: ExpressionItemType()
    data object Empty: ExpressionItemType()
}