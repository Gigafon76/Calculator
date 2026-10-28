package com.example.calculation2.ui.theme.components


import android.R.attr.shape
import android.graphics.Color
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculation2.ui.theme.Calculation2Theme
import com.example.calculation2.utils.innerShadow


@Composable
fun JetRoundedButton(
    modifier: Modifier = Modifier,
    text: String,
    buttonColors: JetRoundedButtonColors,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(buttonColors.containerColor(), CircleShape)
            .innerShadow(
                shape = CircleShape, color = buttonColors.shadowContainerColor(),
                blur = 4.dp,
                offsetY = 4.dp, offsetX = 0.dp, spread = 0.dp
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = buttonColors.contentColor()
        )
    }
}

@Composable
fun JetRoundedButton(
    modifier: Modifier = Modifier,
    text: AnnotatedString,
    buttonColors: JetRoundedButtonColors,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(buttonColors.containerColor(), CircleShape)
            .innerShadow(
                shape = CircleShape, color = buttonColors.shadowContainerColor(),
                blur = 4.dp,
                offsetY = 4.dp, offsetX = 0.dp, spread = 0.dp
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = buttonColors.contentColor()
        )
    }
}

@Composable
fun JetRoundedButton(
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int,
    buttonColors: JetRoundedButtonColors,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(buttonColors.containerColor(), CircleShape)
            .innerShadow(
                shape = CircleShape, color = buttonColors.shadowContainerColor(),
                blur = 4.dp,
                offsetY = 4.dp, offsetX = 0.dp, spread = 0.dp
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(iconId),
            contentDescription = null,
            tint = buttonColors.contentColor()
        )
    }
}

object JetRoundedButtonDefaults {

    @Composable
    fun numberButtonColors(
        containerColor: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.primary,
        contentColor: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.onPrimary,
        shadowContainerColor: androidx.compose.ui.graphics.Color = androidx.compose.ui.graphics.Color.Black.copy(alpha = 0.6f),
    ): JetRoundedButtonColors = JetRoundedButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        shadowContainerColor = shadowContainerColor
    )

    @Composable
    fun operationButtonColors(
        containerColor: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.secondary,
        contentColor: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.tertiary,
        shadowContainerColor: androidx.compose.ui.graphics.Color = androidx.compose.ui.graphics.Color.Black.copy(alpha = 0.6f),
    ): JetRoundedButtonColors = JetRoundedButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        shadowContainerColor = shadowContainerColor
    )
    @Composable
    fun equalsButtonDefaults(
        containerColor: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.tertiary,
        contentColor: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.onPrimary,
        shadowContainerColor: androidx.compose.ui.graphics.Color = androidx.compose.ui.graphics.Color.Black.copy(alpha = 0.6f),
    ): JetRoundedButtonColors = JetRoundedButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        shadowContainerColor = shadowContainerColor
    )
}

@Immutable
class JetRoundedButtonColors internal constructor(
    private val containerColor: androidx.compose.ui.graphics.Color,
    private val contentColor: androidx.compose.ui.graphics.Color,
    private val shadowContainerColor: androidx.compose.ui.graphics.Color
) {
    @Composable
    internal fun containerColor(): androidx.compose.ui.graphics.Color {
        return containerColor
    }

    @Composable
    internal fun contentColor(): androidx.compose.ui.graphics.Color {
        return contentColor
    }

    @Composable
    internal fun shadowContainerColor(): androidx.compose.ui.graphics.Color {
        return shadowContainerColor
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is JetRoundedButtonColors) return false

        if (containerColor != other.containerColor) return false
        if (contentColor != other.contentColor) return false
        if (shadowContainerColor != other.shadowContainerColor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = containerColor.hashCode()
        result = 31 * result + contentColor.hashCode()
        result = 31 * result + shadowContainerColor.hashCode()
        return result
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF)
@Composable
private fun ShowPreview() {
    Calculation2Theme {
        JetRoundedButton(
            modifier = Modifier
                .width(64.dp)
                .height(64.dp), text = "+",
            JetRoundedButtonDefaults.numberButtonColors()
        ) {

        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF)
@Composable
private fun ShowPreview2() {
    Calculation2Theme {
        JetRoundedButton(
            modifier = Modifier
                .width(64.dp)
                .height(64.dp), text = "+",
            JetRoundedButtonDefaults.operationButtonColors()
        ) {

        }
    }
}