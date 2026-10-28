package com.example.calculation2.ui.theme.screens.home
import android.annotation.SuppressLint
import com.example.calculation2.base.BaseViewModel
import com.example.calculation2.ui.theme.screens.models.ExpressionItem
import com.example.calculation2.ui.theme.screens.models.ExpressionItemType
import com.example.calculation2.ui.theme.screens.models.HomeAction
import com.example.calculation2.ui.theme.screens.models.HomeEvent
import com.example.calculation2.ui.theme.screens.models.HomeViewState
import com.example.calculation2.utils.MathModule
import java.text.ParseException
import kotlin.math.roundToLong


// ViewModel экрана калькулятора.
// Наследуется от BaseViewModel с тремя типами:
//   HomeEvent     — события от UI
//   HomeViewState — состояние экрана
//   HomeAction    — одноразовые действия (в этом классе не используются)
class HomeViewModel : BaseViewModel<HomeEvent, HomeViewState, HomeAction>(
    HomeViewState() // начальное состояние — пустое выражение
){
    // Единственная точка входа для всех событий от UI.
    // Здесь событие разбирается через when и делегируется в приватные методы.
    override fun obtainEvent(event: HomeEvent) {
        when(event){
            // Событие смены темы — пока пустое (заглушка)
            is HomeEvent.ChangeTheme -> {}
            // Событие добавления элемента в выражение
            is HomeEvent.ChangeExpression -> {
                when (event.newValue.type){
                    // цифра/точка
                    is ExpressionItemType.Value -> addValue(viewState, event.newValue)
                    // скобка
                    is ExpressionItemType.Bracket  -> addBracket(viewState, event.newValue)
                // операция
                    is ExpressionItemType.Operation  -> addOperation(viewState, event.newValue)
                    // пустой элемент = очистка
                    is ExpressionItemType.Empty -> clearExpression()
                }
            }
            // Событие "посчитать" (=)
            is HomeEvent.CalculateExpression -> equality(viewState)
            // Событие "очистить всё" (C)
            is HomeEvent.ClearExpression -> clearExpression()
            // Событие "удалить последний символ" (⌫)
            is HomeEvent.RemoveLastSymbol -> removeSymbol(viewState)
        }
    }
    // ───────────────────────── ДОБАВЛЕНИЕ ЦИФРЫ / ТОЧКИ ────────────────────────
    private fun addValue(state: HomeViewState, item: ExpressionItem) {
        // Берём текущие строки выражения из состояния
        val expression = state.displayExpression
        val privateExpression = state.privateExpression
        // Дописываем символ в обе строки
        expression.append(item.value)
        privateExpression.append(item.value)

        // Пытаемся посчитать результат "на лету"
        val result = calculateResult(privateExpression.toString())

        // Обновляем состояние через copy (data class)
        viewState = state.copy(
            displayExpression = expression,
            privateExpression = privateExpression,
            currentExpressionItem = item,
            expressionResult = result
        )
    }
    // ───────────────────────── ДОБАВЛЕНИЕ СКОБКИ ─────────────────────────
    private fun addBracket(state: HomeViewState, item: ExpressionItem){
        val expression = state.displayExpression
        val privateExpressions = state.privateExpression
// Просто дописываем скобку в обе строки
        expression.append(item.value)
        privateExpressions.append(item.value)

        // Пересчитываем результат

        val result = calculateResult(privateExpressions.toString())

        // Обновляем состояние (позиционные аргументы copy)
        viewState = state.copy(
            displayExpression = expression,
            privateExpression = privateExpressions,
            currentExpressionItem = item,
            expressionResult = result
        )

    }

    // ───────────────────────── ДОБАВЛЕНИЕ ОПЕРАЦИИ ─────────────────────────
    private fun addOperation(state: HomeViewState, item: ExpressionItem){
        val expression = state.displayExpression
        val privateExpression = state.privateExpression
// Если предыдущий элемент тоже был операцией — заменяем его на новую.
// Пример: было "5+", пользователь жмёт "×" → получится "5×", а не "5+×".
        if (state.currentExpressionItem.type == ExpressionItemType.Operation){
            expression.deleteCharAt(expression.lastIndex) // удалить последний символ
            privateExpression.deleteCharAt(privateExpression.lastIndex)
        }
// Дописываем новую операцию
        expression.append(item.value)
        privateExpression.append(item.value)
// Обновляем состояние (без пересчёта результата — операция не даёт числа)
        viewState = state.copy(
            displayExpression = expression,
            privateExpression = privateExpression,
            currentExpressionItem = item
        )
    }

    // ───────────────────────── ОЧИСТКА ВЫРАЖЕНИЯ ─────────────────────────
    private fun clearExpression(){
        // Просто создаём новое пустое состояние
        viewState = HomeViewState(
            StringBuilder(),
            StringBuilder(),
            ExpressionItem.None,
            "" // результат пустой
        )
    }

    // ───────────────────────── УДАЛЕНИЕ ПОСЛЕДНЕГО СИМВОЛА ─────────────────────
    private fun removeSymbol(state: HomeViewState){
        if (state.displayExpression.isNotEmpty()){
            // Удаляем только если есть что удалять
            val expression = state.displayExpression
            val privateExpression = state.privateExpression

            // Удаляем последний символ из обеих строк
            expression.deleteCharAt(expression.lastIndex)
            privateExpression.deleteCharAt(privateExpression.lastIndex)

            // Определяем новый "последний элемент":
            // если строка опустела — None, иначе конвертируем последний символ
            val expressionItem =
                if (expression.isEmpty()) ExpressionItem.None else ExpressionItem.convertToExpression(
                    expression.last().toString()
                )

            // Пересчитываем результат
            val result = calculateResult(privateExpression.toString())

            // Обновляем состояние
            viewState = state.copy(
                displayExpression = expression,
                privateExpression = privateExpression,
                currentExpressionItem = expressionItem,
                expressionResult = result
            )
        }
    }
    // ───────────────────────── ВЫЧИСЛЕНИЕ (=) ─────────────────────────
    private fun equality(state: HomeViewState){
        val expression = state.displayExpression
        val privateExpression = state.privateExpression
        // Считаем результат по приватному выражению
        val result = calculateResult(privateExpression.toString())
        // Очищаем обе строки и записываем туда результат,
        // чтобы можно было продолжать вычисления с полученным числом
        expression.clear()
        privateExpression.clear()
        privateExpression.append(result)
        expression.append(result)
        // Обновляем состояние: последний элемент — None,
        // результат — то, что посчитали
        viewState = state.copy(
            displayExpression = expression,
            privateExpression = privateExpression,
            currentExpressionItem = ExpressionItem.None,
            expressionResult = result
        )
    }
    // ───────────────────────── ПОДСЧЁТ РЕЗУЛЬТАТА ─────────────────────────
    private fun calculateResult(expression: String): String{
        return try {
            // Пытаемся посчитать выражение через MathModule
            val mathModule = MathModule(expression)
            val result = mathModule.calculate()
            convertResultToString(result) // превращаем Double в красивую строку
        }catch (exeption: ParseException){
            " "
        } catch (e: ParseException){
            e.localizedMessage ?: "Unknown error"
        }
    }

    @SuppressLint("DefaultLocale")
    //форматирования числа
    private fun convertResultToString(result: Double): String {
        return if ((result - result.toLong()) != 0.0) {
            // Есть дробная часть → показываем с двумя знаками после запятой
            // Пример: 3.14159 → "3.14"
            String.format("%.2f", result)
        } else {
            // усли целое число то показываем без дробной части
            "${result.roundToLong()}"
        }
    }

}