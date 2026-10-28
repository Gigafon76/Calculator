package com.example.calculation2.ui.theme.components


import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculation2.R
import com.example.calculation2.ui.theme.Calculation2Theme

@Composable
fun JetSwitchButton(
    modifier: Modifier = Modifier,
    isChecked: Boolean = false,
    onValueChange: (Boolean) -> Unit
) {
    val iconId = if (isChecked)
        R.drawable.ic_day
    else
        R.drawable.ic_moon

    Row(
        modifier = modifier
            .wrapContentWidth()
            .background(
                MaterialTheme.colorScheme.secondary,
                RoundedCornerShape(topStart = 16.dp, bottomEnd = 16.dp)
            )
            .clip(
                RoundedCornerShape(topStart = 16.dp, bottomEnd = 16.dp)
            )
            .clickable(onClick = {
                onValueChange.invoke(!isChecked)
            }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .size(48.dp, 24.dp)
                .background(MaterialTheme.colorScheme.onSecondary, RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(14.dp)
                    .background(MaterialTheme.colorScheme.secondary, CircleShape)
            )
        }

        Icon(
            modifier = Modifier.padding(horizontal = 8.dp),
            imageVector = ImageVector.vectorResource(id = iconId), contentDescription = "",
            tint = MaterialTheme.colorScheme.onSecondary
        )
    }
}

// Предпросмотр UI для светлой темы
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
// Предпросмотр UI для темной темы
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ShowPreview2() {
    Calculation2Theme {
        Row {
            JetSwitchButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp), isChecked = true, {}
            )
        }
    }
}