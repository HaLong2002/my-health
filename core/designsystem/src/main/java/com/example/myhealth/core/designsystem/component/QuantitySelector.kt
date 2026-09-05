package com.example.myhealth.core.designsystem.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myhealth.core.designsystem.R
import com.example.myhealth.core.designsystem.icon.MyHealthIcons
import com.example.myhealth.core.designsystem.theme.MyHealthTheme

@Composable
fun QuantitySelector(
    modifier: Modifier = Modifier,
    count: Int,
    decreaseItemCount: () -> Unit,
    increaseItemCount: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MHIconButton(
            onClick = decreaseItemCount,
            icon = MyHealthIcons.Remove,
            iconContentDescription = stringResource(R.string.decrease),
            modifier = Modifier.padding(end = 8.dp),
            backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
        )
        Text(
            text = "$count",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleSmall,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(end = 8.dp)
        )
        MHIconButton(
            onClick = increaseItemCount,
            icon = MyHealthIcons.Add,
            iconContentDescription = stringResource(R.string.increase),
            backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
        )
    }
}

@Preview
@Composable
private fun QuantitySelectorPreview() {
    MyHealthTheme {
        QuantitySelector(
            count = 1,
            decreaseItemCount = {},
            increaseItemCount = {}
        )
    }
}