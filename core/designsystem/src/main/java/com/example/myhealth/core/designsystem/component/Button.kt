package com.example.myhealth.core.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.myhealth.core.designsystem.R
import com.example.myhealth.core.designsystem.icon.MyHealthIcons
import com.example.myhealth.core.designsystem.theme.MyHealthTheme

@Composable
fun MHButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        colors = colors,
        content = content,
    )
}

@Composable
fun MHOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors:  ButtonColors = ButtonDefaults.outlinedButtonColors(),
    content: @Composable RowScope.() -> Unit,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        content = content,
    )
}

@Composable
fun MHElevatedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors:  ButtonColors = ButtonDefaults.elevatedButtonColors(),
    content: @Composable RowScope.() -> Unit,
) {
    ElevatedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        content = content,
    )
}

@Composable
fun MHFilledIconButton(
    onClick: () -> Unit,
    icon: ImageVector,
    iconDescription: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: IconButtonColors = IconButtonDefaults.filledIconButtonColors(),
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = colors,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = iconDescription,
        )
    }
}


@Composable
fun MHIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector,
    iconContentDescription: String,
    backgroundColor: Color = Color.Transparent,
) {
    Surface(
        modifier = modifier
            .clickable(onClick = onClick)
            .clip(CircleShape),
        color = backgroundColor,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = iconContentDescription,
        )
    }
}

@Composable
fun MHDismissButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    MHElevatedButton(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.error,
            contentColor = MaterialTheme.colorScheme.onError
        )
    ) {
        Icon(
            imageVector = MyHealthIcons.Delete,
            contentDescription = stringResource(id = R.string.delete),
        )
    }
}

@Preview
@Composable
fun MHButtonPreview() {
    MyHealthTheme {
        MHButton(
            onClick = {},
            content = { Text(text = "Test button") }
        )
    }
}

@Preview
@Composable
fun MHSmallIconButtonPreview() {
    MyHealthTheme {
        MHIconButton(
            onClick = {},
            icon = MyHealthIcons.Add,
            iconContentDescription = "Increase item count"
        )
    }
}

@Preview
@Composable
fun MHOutlinedButtonPreview() {
    MyHealthTheme {
        MHOutlinedButton(
            onClick = {},
            content = { Text(text = "Test button") }
        )
    }
}

@Preview
@Composable
fun MHElevatedButtonPreview() {
    MyHealthTheme {
        MHElevatedButton(
            onClick = {},
            content = { Text(text = "Test button") }
        )
    }
}

@Preview
@Composable
fun MHFilledIconButtonPreview() {
    MyHealthTheme {
        MHFilledIconButton(
            onClick = {},
            icon = MyHealthIcons.Add,
            iconDescription = "",
        )
    }
}

@Preview
@Composable
fun MHDismissButtonPreview() {
    MyHealthTheme {
        MHDismissButton(
            onClick = {},
        )
    }
}